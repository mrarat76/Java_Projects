package client;

import common.Message;
import server.GoServer;

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
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        JButton btnMultiplayer = new JButton("Multiplayer");
        JButton btnSingle = new JButton("Single Player (Pasif)");
        btnSingle.setEnabled(false);

        btnMultiplayer.addActionListener(e -> {
            if (!isServerAlive()) {
                JOptionPane.showMessageDialog(this,
                        "Sunucu şu anda kapalı.\nLütfen önce sunucuyu başlatın.",
                        "Sunucu Kapalı",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            SwingUtilities.invokeLater(() -> {
                new ClientUI();
                dispose();
            });

        });

        buttonPanel.add(btnMultiplayer);
        buttonPanel.add(btnSingle);
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

        // Durum güncelleyiciyi başlat
        updateStatus(); // Timer yerine sadece ilk durumda durumu göster

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        startStatusChecker();  // 🟢 burada başlat

    }

    private void updateStatus() {
        if (isServerAlive()) {
            lblServerStatus.setText("Sunucu: AÇIK");
            try {
                lblClientCount.setText("Bağlı oyuncu: " + GoServer.getConnectedPlayerCount());
            } catch (Exception e) {
                lblClientCount.setText("Bağlı oyuncu: bilinmiyor");
            }
        } else {
            lblServerStatus.setText("Sunucu: KAPALI");
            lblClientCount.setText("Bağlı oyuncu: -");
        }
    }

    private void startStatusChecker() {
        Timer statusTimer = new Timer(3000, e -> {
            boolean alive = isServerAlive();

            lblServerStatus.setText("Sunucu: " + (alive ? "AÇIK" : "KAPALI"));
            lblClientCount.setText("Bağlı oyuncu: " + (alive ? GoServer.getConnectedPlayerCount() : "–"));
        });
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

    /* private boolean isServerAlive() {
        try (Socket socket = new Socket("127.0.0.1", 12345)) {
            // Sadece bağlantı testi, herhangi bir veri gönderme!
            return true;
        } catch (IOException e) {
            return false;
        }
    }*/
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StartUI::new);
    }
}
