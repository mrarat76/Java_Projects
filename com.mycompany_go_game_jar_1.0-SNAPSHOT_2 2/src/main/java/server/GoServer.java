package server;

import common.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import utils.BoardState;

public class GoServer {

    private int port;
    private final ArrayList<PlayerHandler> players = new ArrayList<>();
    private final GameLogic logic = new GameLogic();
    private ServerSocket serverSocket;
    private GoServerUI serverUI;
    private volatile boolean isRunning = false;

    public void start(int port) {
        this.port = port;
        new Thread(this::runServer).start();
    }

    private void runServer() {
        try {
            serverUI = new GoServerUI(this);
            serverUI.log("Go sunucusu başlatılıyor...");

            serverSocket = new ServerSocket(port);
            isRunning = true;

            serverUI.log("[SERVER] Port: " + port);
            System.out.println("[SERVER] Go server başlatıldı. Port: " + port);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    shutdown();
                } catch (IOException e) {
                    serverUI.log("[SERVER] Shutdown sırasında hata: " + e.getMessage());
                }
            }));

            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = reader.readLine();

                if (line != null && line.startsWith("CHECK_CONNECTION")) {
                    serverUI.log("[SERVER] Durum kontrol bağlantısı alındı.");
                    socket.close();
                    continue;
                }

                cleanDisconnected();
                updateClientCountInUI();

                if (players.size() < 2) {
                    PlayerHandler handler = new PlayerHandler(socket, players.size(), this);
                    players.add(handler);
                    handler.start();
                    updateClientCountInUI();

                    serverUI.log("[SERVER] Oyuncu bağlandı. ID: " + handler.getPlayerId());

                    if (players.size() == 2) {
                        logic.reset();
                        BoardState newBoard = new BoardState(19);
                        logic.setBoardState(newBoard);
                        broadcast(Message.GenerateMsg(Message.Type.RESTART, ""), -1);
                        serverUI.log("[SERVER] Oyun başlatıldı.");
                        updateScoresInUI();
                        updatePlayerListInUI();
                    }

                } else {
                    socket.close();
                    serverUI.log("[SERVER] Fazla istemci bağlantısı reddedildi.");
                }
            }

        } catch (IOException e) {
            serverUI.log("[SERVER] Hata: " + e.getMessage());
        }
    }

    public void broadcast(String msg, int senderId) {
        for (PlayerHandler player : players) {
            if (!player.isDisconnected() && player.getPlayerId() != senderId) {
                try {
                    player.send(msg);
                } catch (IOException e) {
                    serverUI.log("[SERVER] Yayın hatası: " + e.getMessage());
                }
            }
        }
    }

    public void cleanDisconnected() {
        players.removeIf(PlayerHandler::isDisconnected);
    }

    public int getConnectedPlayerCount() {
        return (int) players.stream().filter(p -> !p.isDisconnected()).count();
    }

    public void checkAllDisconnectedAndPrompt() {
        if (players.stream().allMatch(PlayerHandler::isDisconnected) && players.size() == 2) {
            SwingUtilities.invokeLater(() -> {
                int choice = JOptionPane.showConfirmDialog(null,
                        "Tüm oyuncular oyundan ayrıldı. Sunucu kapatılsın mı?",
                        "Sunucu Kapatılsın mı?",
                        JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    try {
                        shutdown();
                        //System.exit(0);
                    } catch (IOException e) {
                        serverUI.log("[SERVER] Shutdown hatası: " + e.getMessage());
                    }
                } else {
                    int restart = JOptionPane.showConfirmDialog(null,
                            "Yeni oyun başlatılsın mı?",
                            "Yeni Oyun?",
                            JOptionPane.YES_NO_OPTION);

                    if (restart == JOptionPane.YES_OPTION) {
                        logic.reset();
                        players.clear();
                        updateClientCountInUI();
                        serverUI.log("[SERVER] Yeni oyun başlatılıyor. Yeni istemciler bekleniyor...");
                    }
                }
            });
        }
    }

    public void kickPlayer(int id) throws IOException {
        for (PlayerHandler p : players) {
            if (p.getPlayerId() == id && !p.isDisconnected()) {
                p.send(Message.GenerateMsg(Message.Type.GAME_OVER, "Sunucudan atıldınız."));
                p.getSocket().close();
                p.setDisconnected(true);
                break;
            }
        }
        cleanDisconnected();
        updateClientCountInUI();
    }

    public void updateScoresInUI() {
        int score1 = logic.getScore(0);
        int score2 = logic.getScore(1);
        serverUI.updateScore(score1, score2);
    }

    public void updatePlayerListInUI() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (PlayerHandler p : players) {
            if (!p.isDisconnected()) {
                ids.add(p.getPlayerId());
            }
        }
        serverUI.updatePlayerList(ids);
    }

    public void updateClientCountInUI() {
        serverUI.updateClientCount();
    }

    public void shutdown() throws IOException {
        serverUI.log("[SERVER] Sunucu kapatılıyor...");
        isRunning = false;

        for (PlayerHandler player : players) {
            if (!player.isDisconnected()) {
                try {
                    player.send(Message.GenerateMsg(Message.Type.GAME_OVER, "Sunucu kapatıldı."));
                    player.getSocket().close();
                } catch (IOException ignored) {}
            }
        }

        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
        }

        serverUI.log("[SERVER] Port kapatıldı. Çıkış yapıldı.");
    }

    public boolean isRunning() {
        return isRunning;
    }

    public GameLogic getLogic() {
        return logic;
    }

    public GoServerUI getUI() {
        return serverUI;
    }

    public ArrayList<PlayerHandler> getPlayers() {
        return players;
    }
}



/*package server;

import common.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import server.PlayerHandler;
import utils.BoardState;

public class GoServer {

    public static int PORT = 12345;
    private static ArrayList<PlayerHandler> players = new ArrayList<>();
    public static GameLogic logic = new GameLogic();
    private static ServerSocket serverSocket;
    public static GoServerUI serverUI;
    private static volatile boolean isRunning = false;

    public static void start(int port) {
        PORT = port;
        new Thread(() -> runServer()).start();
    }

    private static void runServer() {
        try {
            serverUI = new GoServerUI();
            serverUI.log("Go sunucusu başlatılıyor...");

            serverSocket = new ServerSocket(PORT);
            isRunning = true;

            serverUI.log("[SERVER] Port: " + PORT);
            System.out.println("[SERVER] Go server başlatıldı. Port: " + PORT);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    shutdown();
                } catch (IOException e) {
                    serverUI.log("[SERVER] Shutdown sırasında hata: " + e.getMessage());
                    System.out.println("[SERVER] Shutdown sırasında hata: " + e.getMessage());
                }
            }));

            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = reader.readLine();

                if (line != null && line.startsWith("CHECK_CONNECTION")) {
                    serverUI.log("[SERVER] Durum kontrol bağlantısı alındı.");
                    System.out.println("[SERVER] Durum kontrol bağlantısı alındı.");
                    socket.close();
                    continue;
                }

                cleanDisconnected();
                serverUI.updateClientCount();

                if (players.size() < 2) {
                    PlayerHandler handler = new PlayerHandler(socket, players.size());
                    players.add(handler);
                    handler.start();
                    serverUI.updateClientCount();

                    serverUI.log("[SERVER] Oyuncu bağlandı. ID: " + handler.getPlayerId());
                    System.out.println("[SERVER] Oyuncu bağlandı. ID: " + handler.getPlayerId());

                    if (players.size() == 2) {
                        logic.reset();
                        BoardState newBoard = new BoardState(19);
                        logic.setBoardState(newBoard);
                        broadcast(Message.GenerateMsg(Message.Type.RESTART, ""), -1);
                        serverUI.log("[SERVER] Oyun başlatıldı.");
                        System.out.println("[SERVER] Oyun başlatıldı.");
                    }

                } else {
                    socket.close();
                    serverUI.log("[SERVER] Fazla istemci bağlantısı reddedildi.");
                    System.out.println("[SERVER] Fazla istemci bağlantısı reddedildi.");
                }
            }

        } catch (IOException e) {
            serverUI.log("[SERVER] Hata: " + e.getMessage());
            System.out.println("[SERVER] Hata: " + e.getMessage());
        }
    }

    public static void broadcast(String msg, int senderId) {
        for (PlayerHandler player : players) {
            if (!player.isDisconnected() && player.getPlayerId() != senderId) {
                try {
                    player.send(msg);
                } catch (IOException e) {
                    serverUI.log("[SERVER] Yayın hatası: " + e.getMessage());
                    System.out.println("[SERVER] Yayın hatası: " + e.getMessage());
                }
            }
        }
    }

    public static void cleanDisconnected() {
        players.removeIf(PlayerHandler::isDisconnected);
    }

    public static int getConnectedPlayerCount() {
        return (int) players.stream().filter(p -> !p.isDisconnected()).count();
    }

    public static void checkAllDisconnectedAndPrompt() {
        if (players.stream().allMatch(PlayerHandler::isDisconnected) && players.size() == 2) {
            SwingUtilities.invokeLater(() -> {
                int choice = JOptionPane.showConfirmDialog(null,
                        "Tüm oyuncular oyundan ayrıldı. Sunucu kapatılsın mı?",
                        "Sunucu Kapatılsın mı?",
                        JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    try {
                        shutdown();
                        System.exit(0);
                    } catch (IOException e) {
                        serverUI.log("[SERVER] Shutdown hatası: " + e.getMessage());
                        System.out.println("[SERVER] Shutdown hatası: " + e.getMessage());
                    }
                } else {
                    int restart = JOptionPane.showConfirmDialog(null,
                            "Yeni oyun başlatılsın mı?",
                            "Yeni Oyun?",
                            JOptionPane.YES_NO_OPTION);

                    if (restart == JOptionPane.YES_OPTION) {
                        logic.reset();
                        players.clear();
                        serverUI.updateClientCount();

                        serverUI.log("[SERVER] Yeni oyun başlatılıyor. Yeni istemciler bekleniyor...");
                        System.out.println("[SERVER] Yeni oyun başlatılıyor. Yeni istemciler bekleniyor...");
                    }
                }
            });
        }
    }

    public static void kickPlayer(int id) throws IOException {
        for (PlayerHandler p : players) {
            if (p.getPlayerId() == id && !p.isDisconnected()) {
                p.send(Message.GenerateMsg(Message.Type.GAME_OVER, "Sunucudan atıldınız."));
                p.getSocket().close();
                p.setDisconnected(true);
                break;
            }
        }
        cleanDisconnected();
    }

    public static void updateScoresInUI() {
        int score1 = logic.getScore(0);
        int score2 = logic.getScore(1);
        serverUI.updateScore(score1, score2);
    }

    public static void updatePlayerListInUI() {
        java.util.List<Integer> ids = new ArrayList<>();
        for (PlayerHandler p : players) {
            if (!p.isDisconnected()) {
                ids.add(p.getPlayerId());
            }
        }
        serverUI.updatePlayerList(ids);
    }

    public static void shutdown() throws IOException {
        serverUI.log("[SERVER] Sunucu kapatılıyor...");
        System.out.println("[SERVER] Sunucu kapatılıyor...");
        isRunning = false;

        for (PlayerHandler player : players) {
            if (!player.isDisconnected()) {
                try {
                    player.send(Message.GenerateMsg(Message.Type.GAME_OVER, "Sunucu kapatıldı."));
                    player.getSocket().close();
                } catch (IOException ignored) {
                }
            }
        }

        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
        }

        serverUI.log("[SERVER] Port kapatıldı. Çıkış yapıldı.");
        System.out.println("[SERVER] Port kapatıldı. Çıkış yapıldı.");
    }

    public static java.util.List<PlayerHandler> getPlayers() {
        return players;
    }

    public static boolean isRunning() {
        return isRunning;
    }
}*/





/*package server;

import common.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import utils.BoardState;

public class GoServer {

    public static final int PORT = 12345;
    private static ArrayList<PlayerHandler> players = new ArrayList<>();
    public static GameLogic logic = new GameLogic();
    private static ServerSocket serverSocket;
    public static GoServerUI serverUI;
    private static volatile boolean isRunning = false;

    public static void main(String[] args) {
        try {
            serverUI = new GoServerUI();
            serverUI.log("Go sunucusu başlatılıyor...");

            serverSocket = new ServerSocket(PORT);
            isRunning = true;  // ← burada sunucu aktif

            serverUI.log("[SERVER] Port: " + PORT);
            System.out.println("[SERVER] Go server başlatıldı. Port: " + PORT);

            // JVM kapanınca socket'i otomatik kapatmak için shutdown hook
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    shutdown();
                } catch (IOException e) {
                    serverUI.log("[SERVER] Shutdown sırasında hata: " + e.getMessage());
                    System.out.println("[SERVER] Shutdown sırasında hata: " + e.getMessage());
                }
            }));

            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = reader.readLine();

                if (line != null && line.startsWith("CHECK_CONNECTION")) {
                    serverUI.log("[SERVER] Durum kontrol bağlantısı alındı.");
                    System.out.println("[SERVER] Durum kontrol bağlantısı alındı.");
                    socket.close();  // Bu bağlantı sadece kontrol amaçlı
                    continue;        // Oyuncu değil, bir sonraki bağlantıyı bekle
                }
                /*  socket.setSoTimeout(100); // 100ms içinde veri gelmezse sayma
                InputStream in = socket.getInputStream();
                if (in.available() == 0) {
                    socket.close();  // Sunucuyu test eden bağlantı olabilir
                    continue;
                }*/

// Eğer gelen bağlantı veri göndermeden kapandıysa oyuncu sayma!
                /* socket.setSoTimeout(100); // 100 ms içinde veri gelmezse at
                InputStream in = socket.getInputStream();
                if (in.available() == 0) {
                    socket.close();  // Sunucuyu test eden bağlantı olabilir, oyuncu değil
                    continue;
                }
                cleanDisconnected();
                serverUI.updateClientCount();  // ← Bağlantısı kesilenleri temizledikten sonra

                if (players.size() < 2) {
                    PlayerHandler handler = new PlayerHandler(socket, players.size());
                    players.add(handler);
                    handler.start();
                    serverUI.updateClientCount();  // ← Oyuncu eklenince güncelle

                    serverUI.log("[SERVER] Oyuncu bağlandı. ID: " + handler.getPlayerId());
                    System.out.println("[SERVER] Oyuncu bağlandı. ID: " + handler.getPlayerId());

                    if (players.size() == 2) {
                        logic.reset();
                        BoardState newBoard = new BoardState(19);  // 🟡 yeni tahta
                        logic.setBoardState(newBoard);             // 🟢 GameLogic'e bildir
                        broadcast(Message.GenerateMsg(Message.Type.RESTART, ""), -1);
                        serverUI.log("[SERVER] Oyun başlatıldı.");
                        System.out.println("[SERVER] Oyun başlatıldı.");
                    }

                } else {
                    socket.close();
                    serverUI.log("[SERVER] Fazla istemci bağlantısı reddedildi.");
                    System.out.println("[SERVER] Fazla istemci bağlantısı reddedildi.");
                }
            }

        } catch (IOException e) {
            serverUI.log("[SERVER] Hata: " + e.getMessage());
            System.out.println("[SERVER] Hata: " + e.getMessage());
        }
    }

    public static void broadcast(String msg, int senderId) {
        for (PlayerHandler player : players) {
            if (!player.isDisconnected() && player.getPlayerId() != senderId) {
                try {
                    player.send(msg);
                } catch (IOException e) {
                    serverUI.log("[SERVER] Yayın hatası: " + e.getMessage());
                    System.out.println("[SERVER] Yayın hatası: " + e.getMessage());
                }
            }
        }
    }

    public static void cleanDisconnected() {
        players.removeIf(PlayerHandler::isDisconnected);
    }

    public static int getConnectedPlayerCount() {
        return (int) players.stream().filter(p -> !p.isDisconnected()).count();
    }

    public static void checkAllDisconnectedAndPrompt() {
        if (players.stream().allMatch(PlayerHandler::isDisconnected) && players.size() == 2) {
            SwingUtilities.invokeLater(() -> {
                int choice = JOptionPane.showConfirmDialog(null,
                        "Tüm oyuncular oyundan ayrıldı. Sunucu kapatılsın mı?",
                        "Sunucu Kapatılsın mı?",
                        JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    try {
                        shutdown();
                        System.exit(0);
                    } catch (IOException e) {
                        serverUI.log("[SERVER] Shutdown hatası: " + e.getMessage());
                        System.out.println("[SERVER] Shutdown hatası: " + e.getMessage());
                    }
                } else {
                    int restart = JOptionPane.showConfirmDialog(null,
                            "Yeni oyun başlatılsın mı?",
                            "Yeni Oyun?",
                            JOptionPane.YES_NO_OPTION);

                    if (restart == JOptionPane.YES_OPTION) {
                        logic.reset();
                        players.clear();
                        serverUI.updateClientCount();  // ← Bağlantısı kesilenleri temizledikten sonra

                        serverUI.log("[SERVER] Yeni oyun başlatılıyor. Yeni istemciler bekleniyor...");
                        System.out.println("[SERVER] Yeni oyun başlatılıyor. Yeni istemciler bekleniyor...");
                    }
                }
            });
        }
    }

    public static void kickPlayer(int id) throws IOException {
        for (PlayerHandler p : players) {
            if (p.getPlayerId() == id && !p.isDisconnected()) {
                p.send(Message.GenerateMsg(Message.Type.GAME_OVER, "Sunucudan atıldınız."));
                p.getSocket().close();
                p.setDisconnected(true);
                break;
            }
        }
        cleanDisconnected();
    }

    public static void updateScoresInUI() {
        int score1 = logic.getScore(0);
        int score2 = logic.getScore(1);
        serverUI.updateScore(score1, score2);
    }

    public static void updatePlayerListInUI() {
        java.util.List<Integer> ids = new ArrayList<>();
        for (PlayerHandler p : players) {
            if (!p.isDisconnected()) {
                ids.add(p.getPlayerId());
            }
        }
        serverUI.updatePlayerList(ids);
    }

    public static void shutdown() throws IOException {
        serverUI.log("[SERVER] Sunucu kapatılıyor...");
        System.out.println("[SERVER] Sunucu kapatılıyor...");
        isRunning = false;  // ← sunucu kapanıyor

        for (PlayerHandler player : players) {
            if (!player.isDisconnected()) {
                try {
                    player.send(Message.GenerateMsg(Message.Type.GAME_OVER, "Sunucu kapatıldı."));
                    player.getSocket().close();
                } catch (IOException ignored) {
                }
            }
        }

        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
        }

        serverUI.log("[SERVER] Port kapatıldı. Çıkış yapıldı.");
        System.out.println("[SERVER] Port kapatıldı. Çıkış yapıldı.");
    }
    public static java.util.List<PlayerHandler> getPlayers() {
    return players;
}


    public static boolean isRunning() {
        return isRunning;
    }
}*/


/*package server;

import common.Message;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class GoServer {

    public static final int PORT = 12345;
    private static ArrayList<PlayerHandler> players = new ArrayList<>();
    public static GameLogic logic = new GameLogic();
    private static ServerSocket serverSocket;
    public static GoServerUI serverUI;

    public static void main(String[] args) {
        try {
            serverUI = new GoServerUI(); // 👈 EKLE
            serverUI.log("Go sunucusu başlatılıyor...");

            serverSocket = new ServerSocket(PORT);
            System.out.println("[SERVER] Go server başlatıldı. Port: " + PORT);

            // JVM kapanınca socket'i otomatik kapatmak için shutdown hook
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    shutdown();
                } catch (IOException e) {
                    System.out.println("[SERVER] Shutdown sırasında hata: " + e.getMessage());
                }
            }));

            while (true) {
                Socket socket = serverSocket.accept();
                cleanDisconnected();

                if (players.size() < 2) {
                    PlayerHandler handler = new PlayerHandler(socket, players.size());
                    players.add(handler);
                    handler.start();
                    System.out.println("[SERVER] Oyuncu bağlandı. ID: " + handler.getPlayerId());

                    if (players.size() == 2) {
                        logic.reset();
                        broadcast(Message.GenerateMsg(Message.Type.RESTART, ""), -1);
                        System.out.println("[SERVER] Oyun başlatıldı.");
                    }
                } else {
                    socket.close();
                    System.out.println("[SERVER] Fazla istemci bağlantısı reddedildi.");
                }
            }

        } catch (IOException e) {
            System.out.println("[SERVER] Hata: " + e.getMessage());
        }
    }

    public static void broadcast(String msg, int senderId) {
        for (PlayerHandler player : players) {
            if (!player.isDisconnected() && player.getPlayerId() != senderId) {
                try {
                    player.send(msg);
                } catch (IOException e) {
                    System.out.println("[SERVER] Yayın hatası: " + e.getMessage());
                }
            }
        }
    }

    public static void cleanDisconnected() {
        players.removeIf(PlayerHandler::isDisconnected);
    }

    public static void checkAllDisconnectedAndPrompt() {
        if (players.stream().allMatch(PlayerHandler::isDisconnected)) {
            SwingUtilities.invokeLater(() -> {
                int choice = JOptionPane.showConfirmDialog(null,
                        "Tüm oyuncular oyundan ayrıldı. Sunucu kapatılsın mı?",
                        "Sunucu Kapatılsın mı?",
                        JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    try {
                        shutdown();
                        System.exit(0);
                    } catch (IOException e) {
                        System.out.println("Shutdown hatası: " + e.getMessage());
                    }
                } else {
                    int restart = JOptionPane.showConfirmDialog(null,
                            "Yeni oyun başlatılsın mı?",
                            "Yeni Oyun?",
                            JOptionPane.YES_NO_OPTION);

                    if (restart == JOptionPane.YES_OPTION) {
                        logic.reset();
                        players.clear(); // önceki clientlar zaten kapalı
                        System.out.println("[SERVER] Yeni oyun başlatılıyor. Yeni istemciler bekleniyor...");
                    }
                }
            });
        }
    }

    public static void shutdown() throws IOException {
        System.out.println("[SERVER] Sunucu kapatılıyor...");

        for (PlayerHandler player : players) {
            if (!player.isDisconnected()) {
                try {
                    player.send(Message.GenerateMsg(Message.Type.GAME_OVER, "Sunucu kapatıldı."));
                    player.getSocket().close();
                } catch (IOException ignored) {
                }
            }
        }

        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
        }

        System.out.println("[SERVER] Port kapatıldı. Çıkış yapıldı.");
    }
}
 */
