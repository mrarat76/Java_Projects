package client;

import common.Message;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class StartUI extends JFrame {

    private JLabel lblServerStatus;
    private JLabel lblClientCount;
    private JLabel lblPort;

    public StartUI() {
        setTitle("Go Başlatıcı");
        setSize(400, 300);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("GO OYUNU", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton btnMultiplayer = new JButton("Rastgele Eşleşme");
        JButton btnCreateRoom = new JButton("Özel Oda Oluştur");
        JButton btnJoinRoom = new JButton("Odaya Katıl");

        btnMultiplayer.addActionListener(e -> {
            try (Socket socket = new Socket("127.0.0.1", 9090); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                out.println("JOIN_RANDOM");
                String response = in.readLine();

                if (response.startsWith("ROOM_FOUND#") || response.startsWith("ROOM_CREATED#")) {
                    int port = Integer.parseInt(response.split("#")[1]);
                    new ClientUI("127.0.0.1", port);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Sunucu geçersiz yanıt verdi.", "Hata", JOptionPane.ERROR_MESSAGE);
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Sunucuya bağlanılamadı.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCreateRoom.addActionListener(e -> {
            String roomCode = JOptionPane.showInputDialog(this, "Oda kodu girin:");
            if (roomCode != null && !roomCode.trim().isEmpty()) {
                try (Socket socket = new Socket("127.0.0.1", 9090); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    out.println("CREATE_ROOM#" + roomCode);
                    String response = in.readLine();
                    if (response.startsWith("ROOM_CREATED#")) {
                        int port = Integer.parseInt(response.split("#")[1]);
                        new ClientUI("127.0.0.1", port);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, response, "Hata", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Bağlantı hatası", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnJoinRoom.addActionListener(e -> {
            String roomCode = JOptionPane.showInputDialog(this, "Katılmak istediğiniz oda kodunu girin:");
            if (roomCode != null && !roomCode.trim().isEmpty()) {
                try (Socket socket = new Socket("127.0.0.1", 9090); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    out.println("JOIN_ROOM#" + roomCode);
                    String response = in.readLine();
                    if (response.startsWith("ROOM_FOUND#")) {
                        int port = Integer.parseInt(response.split("#")[1]);
                        new ClientUI("127.0.0.1", port);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, response, "Hata", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Bağlantı hatası", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonPanel.add(btnMultiplayer);
        buttonPanel.add(btnCreateRoom);
        buttonPanel.add(btnJoinRoom);
        JButton btnListRooms = new JButton("Oda Listesini Göster");
        btnListRooms.addActionListener(e -> {
            try (Socket socket = new Socket("127.0.0.1", 9090); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                out.println("LIST_ROOMS");
                String response = in.readLine();
                if (response.startsWith("ROOM_LIST#")) {
                    String[] rooms = response.substring("ROOM_LIST#".length()).split("\n");
                    if (rooms.length == 0 || rooms[0].isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Aktif oda yok.", "Oda Listesi", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JList<String> list = new JList<>(rooms);
                        JOptionPane.showMessageDialog(this, new JScrollPane(list), "Aktif Odalar", JOptionPane.PLAIN_MESSAGE);
                    }
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Sunucuya bağlanılamadı.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonPanel.add(btnListRooms);

        add(buttonPanel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        lblServerStatus = new JLabel("Sunucu: kontrol ediliyor...", SwingConstants.CENTER);
        lblClientCount = new JLabel("Bağlı oyuncu: kontrol ediliyor...", SwingConstants.CENTER);
        lblPort = new JLabel("Port: 12345", SwingConstants.CENTER);

        infoPanel.add(lblServerStatus);
        infoPanel.add(lblClientCount);
        infoPanel.add(lblPort);
        add(infoPanel, BorderLayout.SOUTH);

        updateStatus();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        startStatusChecker();
    }

    private void updateStatus() {
        if (isServerAlive()) {
            lblServerStatus.setText("Sunucu: AÇIK");
            lblClientCount.setText("Bağlı oyuncu: -");
        } else {
            lblServerStatus.setText("Sunucu: KAPALI");
            lblClientCount.setText("Bağlı oyuncu: -");
        }
    }

    private void startStatusChecker() {
        Timer statusTimer = new Timer(3000, e -> updateStatus());
        statusTimer.setRepeats(true);
        statusTimer.start();
    }

    private boolean isServerAlive() {
        try (Socket socket = new Socket("127.0.0.1", 12345); OutputStream out = socket.getOutputStream()) {
            String checkMsg = Message.GenerateMsg(Message.Type.CHECK_CONNECTION, "ping");
            out.write((checkMsg + "\n").getBytes());
            out.flush();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StartUI::new);
    }
}

/*package client;

import common.Message;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class StartUI extends JFrame {

    private JLabel lblServerStatus;
    private JLabel lblClientCount;
    private JLabel lblPort;

    public StartUI() {
        setTitle("Go Başlatıcı");
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Başlık
        JLabel title = new JLabel("GO OYUNU", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Buton paneli
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton btnQuickMatch = new JButton("Hızlı Eşleşme");
        JButton btnCreateRoom = new JButton("Özel Oda Aç");
        JButton btnJoinRoom = new JButton("Odaya Katıl");

        // Buton işlevleri
        btnQuickMatch.addActionListener(e -> {
            // TODO: MasterServer'a bağlanıp hızlı eşleştirme mesajı gönder
            JOptionPane.showMessageDialog(this, "Hızlı eşleştirme sistemi henüz entegre edilmedi.");
        });

        btnCreateRoom.addActionListener(e -> {
            String roomCode = JOptionPane.showInputDialog(this, "Oda kodu belirleyin (örn: ABC123):", "Oda Oluştur", JOptionPane.PLAIN_MESSAGE);
            if (roomCode != null && !roomCode.trim().isEmpty()) {
                // TODO: MasterServer'a CREATE_ROOM mesajı gönder
                JOptionPane.showMessageDialog(this, "Oda açılıyor: " + roomCode);
            }
        });

        btnJoinRoom.addActionListener(e -> {
            String roomCode = JOptionPane.showInputDialog(this, "Katılmak istediğiniz oda kodunu girin:", "Odaya Katıl", JOptionPane.PLAIN_MESSAGE);
            if (roomCode != null && !roomCode.trim().isEmpty()) {
                // TODO: MasterServer'a JOIN_ROOM mesajı gönder
                JOptionPane.showMessageDialog(this, "Odaya bağlanılıyor: " + roomCode);
            }
        });

        buttonPanel.add(btnQuickMatch);
        buttonPanel.add(btnCreateRoom);
        buttonPanel.add(btnJoinRoom);
        add(buttonPanel, BorderLayout.CENTER);

        // Alt bilgi paneli
        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        lblServerStatus = new JLabel("Sunucu: kontrol ediliyor...", SwingConstants.CENTER);
        lblClientCount = new JLabel("Bağlı oyuncu: kontrol ediliyor...", SwingConstants.CENTER);
        lblPort = new JLabel("Port: 12345", SwingConstants.CENTER);

        infoPanel.add(lblServerStatus);
        infoPanel.add(lblClientCount);
        infoPanel.add(lblPort);

        add(infoPanel, BorderLayout.SOUTH);

        updateStatus(); // İlk durumu kontrol et
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        startStatusChecker();  // Durumu düzenli kontrol et
    }

    private void updateStatus() {
        if (isServerAlive()) {
            lblServerStatus.setText("Sunucu: AÇIK");
            lblClientCount.setText("Bağlı oyuncu: bilinmiyor"); // Artık GoServer'a bağlı değil
        } else {
            lblServerStatus.setText("Sunucu: KAPALI");
            lblClientCount.setText("Bağlı oyuncu: -");
        }
    }

    private void startStatusChecker() {
        Timer statusTimer = new Timer(3000, e -> updateStatus());
        statusTimer.setRepeats(true);
        statusTimer.start();
    }

    private boolean isServerAlive() {
        try (Socket socket = new Socket("127.0.0.1", 12345); OutputStream out = socket.getOutputStream()) {
            String checkMsg = Message.GenerateMsg(Message.Type.CHECK_CONNECTION, "ping");
            out.write((checkMsg + "\n").getBytes());
            out.flush();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StartUI::new);
    }
}*/
