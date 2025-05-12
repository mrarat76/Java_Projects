package server;

import common.Message;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerHandler extends Thread {

    private final int playerId;
    private final Socket socket;
    private final InputStream input;
    private final OutputStream output;
    private final GoServer server;
    private boolean disconnected = false;
    private String username = "Bilinmiyor";

    public PlayerHandler(Socket socket, int playerId, GoServer server) throws IOException {
        this.socket = socket;
        this.playerId = playerId;
        this.server = server;
        this.input = socket.getInputStream();
        this.output = socket.getOutputStream();
    }

    public int getPlayerId() {
        return playerId;
    }

    public boolean isDisconnected() {
        return disconnected;
    }

    public void setDisconnected(boolean value) {
        this.disconnected = value;
    }

    public Socket getSocket() {
        return socket;
    }

    public void send(String msg) throws IOException {
        byte[] data = msg.getBytes();
        output.write(data.length);
        output.write(data);
        output.flush();
    }

    @Override
    public void run() {
        try {
            send(Message.GenerateMsg(Message.Type.YOUR_ID, String.valueOf(playerId)));
            System.out.println("[SERVER] Oyuncuya ID gönderildi: " + playerId);

            server.updatePlayerListInUI();
            server.updateScoresInUI();

            while (!socket.isClosed()) {
                int size = input.read();
                if (size == -1) break;

                byte[] buffer = new byte[size];
                int readTotal = 0;
                while (readTotal < size) {
                    int bytesRead = input.read(buffer, readTotal, size - readTotal);
                    if (bytesRead == -1) break;
                    readTotal += bytesRead;
                }

                String received = new String(buffer).trim();
                MsgParser(received);
            }

        } catch (IOException ex) {
            Logger.getLogger(PlayerHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            disconnected = true;
            try {
                socket.close();
            } catch (IOException ignored) {}

            server.broadcast(Message.GenerateMsg(Message.Type.PLAYER_LEFT, ""), playerId);
            server.checkAllDisconnectedAndPrompt();
            server.updatePlayerListInUI();
            System.out.println("[SERVER] Oyuncu " + playerId + " ayrıldı.");
        }
    }

    public void MsgParser(String msg) throws IOException {
        String[] tokens = msg.split("#");
        Message.Type type = Message.Type.valueOf(tokens[0].trim());

        switch (type) {
            case DISCONNECT:
                socket.close();
                break;

            case MSGFROMCLIENT:
                if (!server.getLogic().isValidMove(playerId)) {
                    server.getUI().log("[SERVER] Oyuncu " + playerId + " sırasını beklemeli.");
                    return;
                }
                server.getLogic().switchTurn();
                server.broadcast(Message.GenerateMsg(Message.Type.MSGFROMCLIENT, tokens[1]), playerId);
                break;

            case PASS:
                if (!server.getLogic().isValidMove(playerId)) {
                    System.out.println("[SERVER] Oyuncu " + playerId + " sırasını beklemeli (PASS)." );
                    return;
                }
                boolean shouldEnd = server.getLogic().handlePass(playerId);
                if (shouldEnd) {
                    server.broadcast(Message.GenerateMsg(Message.Type.GAME_OVER, "Her iki oyuncu pas geçti."), -1);
                } else {
                    server.broadcast(Message.GenerateMsg(Message.Type.PASS, "Oyuncu " + playerId + " pas geçti."), playerId);
                }
                break;

            case GAME_OVER:
                server.broadcast(Message.GenerateMsg(Message.Type.GAME_OVER, "Oyuncu " + playerId + " oyunu bitirdi."), -1);
                break;

            case USERNAME:
                this.username = tokens[1].trim();
                for (PlayerHandler player : server.getPlayers()) {
                    if (player != this && !player.isDisconnected()) {
                        player.send(Message.GenerateMsg(Message.Type.OPPONENT_NAME, this.username));
                        this.send(Message.GenerateMsg(Message.Type.OPPONENT_NAME, player.username));
                    }
                }
                server.updatePlayerListInUI();
                break;

            default:
                System.out.println("[SERVER] Bilinmeyen mesaj tipi: " + type);
                break;
        }
    }
}




/*package server;
ÇALIŞAN BU ÇALIŞMASSA BUNU KULLAN



import common.Message;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerHandler extends Thread {

    private int playerId;
    private Socket socket;
    private InputStream input;
    private OutputStream output;
    private boolean disconnected = false;
    public static GoServerUI serverUI;
    private String username = "Bilinmiyor";  // varsayılan

    public PlayerHandler(Socket socket, int playerId) throws IOException {
        this.socket = socket;
        this.playerId = playerId;
        this.input = socket.getInputStream();
        this.output = socket.getOutputStream();
    }

    public int getPlayerId() {
        return playerId;
    }

    public boolean isDisconnected() {
        return disconnected;
    }

    public void setDisconnected(boolean value) {
        this.disconnected = value;
    }

    public Socket getSocket() {
        return socket;
    }

    public void send(String msg) throws IOException {
        byte[] data = msg.getBytes();
        output.write(data.length);
        output.write(data);
        output.flush();
    }

    @Override
    public void run() {
        try {
            // Oyuncuya ID gönder
            String idMsg = Message.GenerateMsg(Message.Type.YOUR_ID, String.valueOf(playerId));
            send(idMsg);

            System.out.println("[SERVER] Oyuncuya ID gönderildi: " + playerId);

            // Bağlantı sonrası UI güncelle
            GoServer.updatePlayerListInUI();
            GoServer.updateScoresInUI();

            while (!socket.isClosed()) {
                int size = input.read();
                if (size == -1) {
                    break;
                }

                byte[] buffer = new byte[size];
                int readTotal = 0;
                while (readTotal < size) {
                    int bytesRead = input.read(buffer, readTotal, size - readTotal);
                    if (bytesRead == -1) {
                        break;
                    }
                    readTotal += bytesRead;
                }

                String received = new String(buffer).trim();
                MsgParser(received);
            }

        } catch (IOException ex) {
            Logger.getLogger(PlayerHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            disconnected = true;
            try {
                socket.close();
            } catch (IOException ignored) {
            }

            GoServer.broadcast(Message.GenerateMsg(Message.Type.PLAYER_LEFT, ""), playerId);
            GoServer.checkAllDisconnectedAndPrompt();
            GoServer.updatePlayerListInUI();

            System.out.println("[SERVER] Oyuncu " + playerId + " ayrıldı.");
        }
    }

    public void MsgParser(String msg) throws IOException {
        String[] tokens = msg.split("#");
        Message.Type type = Message.Type.valueOf(tokens[0].trim());

        switch (type) {
            case DISCONNECT:
                socket.close();
                break;

            case MSGFROMCLIENT:
                if (!GoServer.logic.isValidMove(playerId)) {
                    // System.out.println("[SERVER] Oyuncu " + playerId + " sırasını beklemeli.");
                    serverUI.log("[SERVER] Oyuncu " + playerId + " sırasını beklemeli.");
                    return;
                }
                GoServer.logic.switchTurn();
                GoServer.broadcast(Message.GenerateMsg(Message.Type.MSGFROMCLIENT, tokens[1]), playerId);
                break;

            case PASS:
                if (!GoServer.logic.isValidMove(playerId)) {
                    System.out.println("[SERVER] Oyuncu " + playerId + " sırasını beklemeli (PASS).");
                    return;
                }

                boolean shouldEnd = GoServer.logic.handlePass(playerId);
                if (shouldEnd) {
                    GoServer.broadcast(Message.GenerateMsg(Message.Type.GAME_OVER, "Her iki oyuncu pas geçti."), -1);
                    System.out.println("[SERVER] Oyun sona erdi (iki PASS).");
                } else {
                    GoServer.broadcast(Message.GenerateMsg(Message.Type.PASS, "Oyuncu " + playerId + " pas geçti."), playerId);
                }
                break;

            case GAME_OVER:
                GoServer.broadcast(Message.GenerateMsg(Message.Type.GAME_OVER, "Oyuncu " + playerId + " oyunu bitirdi."), -1);
                break;
            case USERNAME:
                this.username = tokens[1].trim();

                // 🎯 Karşı tarafa rakip ismini gönder
                for (PlayerHandler player : GoServer.getPlayers()) {
                    if (player != this && !player.isDisconnected()) {
                        player.send(Message.GenerateMsg(Message.Type.OPPONENT_NAME, this.username));
                        this.send(Message.GenerateMsg(Message.Type.OPPONENT_NAME, player.username)); // <-- BU SATIR ÖNEMLİ
                    }
                }

                GoServer.updatePlayerListInUI();
                break;

            /* case USERNAME:
                this.username = tokens[1].trim();  // kendi kullanıcı adını sakla
                System.out.println("[SERVER] Oyuncu " + playerId + " kullanıcı adı: " + this.username);

                // Rakip oyuncuya bu adı gönder
                GoServer.broadcast(
                        Message.GenerateMsg(Message.Type.OPPONENT_NAME, this.username),
                        this.playerId // gönderici hariç herkese
                );
                break;
            default:
                System.out.println("[SERVER] Bilinmeyen mesaj tipi: " + type);
                break;
        }
    }
}*/

/*package server;

import common.Message;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerHandler extends Thread {

    private int playerId;
    private Socket socket;
    private InputStream input;
    private OutputStream output;
    private boolean disconnected = false;

    public PlayerHandler(Socket socket, int playerId) throws IOException {
        this.socket = socket;
        this.playerId = playerId;
        this.input = socket.getInputStream();
        this.output = socket.getOutputStream();
    }

    public int getPlayerId() {
        return playerId;
    }

    public boolean isDisconnected() {
        return disconnected;
    }
    public void setDisconnected(boolean value) {
    this.disconnected = value;
}

    public Socket getSocket() {
        return socket;
    }

    public void send(String msg) throws IOException {
        byte[] data = msg.getBytes();
        output.write(data.length);
        output.write(data);
    }

    @Override
    public void run() {
        try {
            // Oyuncuya kendi ID’sini bildir
            String idMsg = Message.GenerateMsg(Message.Type.YOUR_ID, String.valueOf(playerId));
            send(idMsg);

            // Mesaj dinleme
            while (!socket.isClosed()) {
                int size = input.read();
                if (size == -1) {
                    break;
                }

                byte[] buffer = new byte[size];
                input.read(buffer);
                String received = new String(buffer).trim();
                MsgParser(received);
            }
        } catch (IOException ex) {
            Logger.getLogger(PlayerHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            disconnected = true;
            try {
                socket.close();
            } catch (IOException ignored) {
            }

            GoServer.broadcast(Message.GenerateMsg(Message.Type.PLAYER_LEFT, ""), playerId);
            GoServer.checkAllDisconnectedAndPrompt();

            System.out.println("[SERVER] Oyuncu " + playerId + " ayrıldı.");
        }
    }

    public void MsgParser(String msg) throws IOException {
        String[] tokens = msg.split("#");
        Message.Type type = Message.Type.valueOf(tokens[0].trim());

        switch (type) {
            case DISCONNECT:
                socket.close();
                break;

            case MSGFROMCLIENT:
                if (!GoServer.logic.isValidMove(playerId)) {
                    System.out.println("[SERVER] Oyuncu " + playerId + " sırasını beklemeli.");
                    return;
                }
                GoServer.logic.switchTurn();
                GoServer.broadcast(Message.GenerateMsg(Message.Type.MSGFROMCLIENT, tokens[1]), playerId);
                break;

            case PASS:
                if (!GoServer.logic.isValidMove(playerId)) {
                    System.out.println("[SERVER] Oyuncu " + playerId + " sırasını beklemeli (PASS).");
                    return;
                }

                boolean shouldEnd = GoServer.logic.handlePass(playerId);
                if (shouldEnd) {
                    GoServer.broadcast(Message.GenerateMsg(Message.Type.GAME_OVER, "Her iki oyuncu pas geçti."), -1);
                    System.out.println("[SERVER] Oyun sona erdi (iki PASS).");
                } else {
                    GoServer.broadcast(Message.GenerateMsg(Message.Type.PASS, "Oyuncu " + playerId + " pas geçti."), playerId);
                }
                break;

            case GAME_OVER:
                GoServer.broadcast(Message.GenerateMsg(Message.Type.GAME_OVER, "Oyuncu " + playerId + " oyunu bitirdi."), -1);
                break;

            default:
                System.out.println("[SERVER] Bilinmeyen mesaj tipi: " + type);
                break;
        }
    }
}
 */
