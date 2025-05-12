package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import common.Message;

public class GoClient extends Thread {

    Socket socket;
    InputStream input;
    OutputStream output;
    ClientUI ui;

    public GoClient(String ip, int port, ClientUI ui) throws IOException {
        this.socket = new Socket(ip, port);
        this.input = socket.getInputStream();
        this.output = socket.getOutputStream();
        this.ui = ui;
    }

    public void SendMessage(String msg) throws IOException {
        byte[] data = msg.getBytes();
        output.write(data.length);
        output.write(data);
    }

    public void Disconnect() throws IOException {
        String dmsg = Message.GenerateMsg(Message.Type.DISCONNECT, "");
        SendMessage(dmsg);
        this.socket.close();
    }

    public void Listen() {
        this.start();
    }

    @Override
    public void run() {
        try {
            while (!this.socket.isClosed()) {
                int size = this.input.read();
                if (size == -1) {
                    break;
                }
                byte[] buffer = new byte[size];
                this.input.read(buffer);
                String received = new String(buffer).trim();
                MsgParser(received);
            }
        } catch (IOException e) {
            ui.DisplayMessage("[Sistem] Bağlantı koptu.");
        }
    }

    public void MsgParser(String msg) {
        try {
            String[] tokens = msg.split("#");
            Message.Type mt = Message.Type.valueOf(tokens[0].trim());

            switch (mt) {
                case PLAYER_LEFT:
                    int result = JOptionPane.showConfirmDialog(null,
                            "Rakip oyuncu oyundan ayrıldı. Siz de çıkmak istiyor musunuz?",
                            "Oyuncu Ayrıldı",
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            Disconnect();
                        } catch (IOException ignored) {
                        }
                        System.exit(0);
                    } else {
                        ui.DisplayMessage("Yeni oyuncu bekleniyor...");
                    }
                    break;

                case RESTART:
                    ui.RestartGame();
                    break;

                case CLIENTIDS:
                    String[] datas = tokens[1].split(",");
                    // Burada ya counter koy, ya da mesaja bak ona göre artırsın
                    ClientUI.lst_clientIds_model.removeAllElements();
                    for (String data : datas) {
                        ClientUI.lst_clientIds_model.addElement(data);
                    }

                    if (datas.length == 2 && !ui.isOpponentConnected()) {
                        ui.setOpponentConnected(true);
                        ui.DisplayMessage("Rakip oyuncu bağlandı.");
                    }

                    break;

                case MSGFROMCLIENT:

                    String[] coords = tokens[1].split(",");
                    int row = Integer.parseInt(coords[0]);
                    int col = Integer.parseInt(coords[1]);

                    ui.boardState.placeStone(row, col); // rakip taşı koy
                    ui.gameBoard.repaint();
                    ui.updateScoreboard();
                    /// tahtayı yenile
                    break;

                case PASS:
                    ui.DisplayMessage("[Rakip] Pas geçti. Sıra sizde.");
                    ui.boardState.setCurrentPlayer(ui.boardState.getMyColor());

                    break;

                case GAME_OVER:
                    ui.HandleGameOver();  // skor GUI içinde hesaplanır
                    break;
                case YOUR_ID:
                    int id = Integer.parseInt(tokens[1].trim());
                    ui.setPlayerId(id);
                    break;
                case OPPONENT_NAME:
                    String opponent = tokens[1].trim();
                    ui.setOpponentName(opponent);
                    ui.updateScoreboard();
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Hata (MsgParser): " + e.getMessage());
        }
    }
}


/*package client;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class GoClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ClientUI ui;

    public GoClient(String serverIp, int port, ClientUI ui) {
        this.ui = ui;
        try {
            socket = new Socket(serverIp, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            listen();
        } catch (IOException e) {
            e.printStackTrace();
            ui.displayMessage("Bağlantı kurulamadı: " + e.getMessage());
        }
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    public void disconnect() {
        try {
            sendMessage("DISCONNECT#");
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Bağlantı kapatılırken hata: " + e.getMessage());
        }
    }

    private void listen() {
        Thread listener = new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null) {
                    if (msg.startsWith("PLAYER_LEFT#")) {
                        handleOpponentDisconnect();
                    } else if (msg.startsWith("RESTART#")) {
                        ui.restartGame();
                    } else {
                        ui.receiveMessage(msg);
                    }
                }
            } catch (IOException e) {
                ui.displayMessage("Sunucu bağlantısı kesildi.");
            }
        });
        listener.start();
    }

    private void handleOpponentDisconnect() {
        SwingUtilities.invokeLater(() -> {
            int result = JOptionPane.showConfirmDialog(null,
                    "Rakip oyuncu oyundan ayrıldı.\nSiz de çıkmak istiyor musunuz?",
                    "Oyuncu Ayrıldı",
                    JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                disconnect();
                System.exit(0);
            } else {
                ui.displayMessage("Yeni oyuncu bekleniyor...");
            }
        });
    }
}
 */
