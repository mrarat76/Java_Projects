package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class GoServerUI extends JFrame {

    private JTextArea logArea;
    private JButton btnShutdown;
    private JLabel lblClientCount;
    private JLabel lblScore;

    private JComboBox<String> cmbPlayers;
    private JButton btnKick;

    private GoServer server;

    public GoServerUI(GoServer server) {
        this.server = server;
        setTitle("Go Sunucu Paneli");
        setSize(700, 500);
        setLayout(new BorderLayout());

        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        lblClientCount = new JLabel("Bağlı Oyuncu: 0", SwingConstants.CENTER);
        lblClientCount.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(lblClientCount);

        lblScore = new JLabel("Skor - Siyah: 0 | Beyaz: 0", SwingConstants.CENTER);
        lblScore.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(lblScore);
        add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new FlowLayout());

        btnShutdown = new JButton("SUNUCUYU KAPAT");
        btnShutdown.setFont(new Font("Arial", Font.BOLD, 14));
        btnShutdown.setForeground(Color.RED);
        btnShutdown.addActionListener((ActionEvent e) -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Sunucuyu kapatmak istediğinizden emin misiniz?",
                    "Kapatma Onayı", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    server.shutdown();
                } catch (IOException ex) {
                    log("[UI] Kapatma hatası: " + ex.getMessage());
                }
               // System.exit(0);
            }
        });

        cmbPlayers = new JComboBox<>();
        btnKick = new JButton("SEÇİLİYİ AT");
        btnKick.addActionListener(e -> {
            String selected = (String) cmbPlayers.getSelectedItem();
            if (selected != null && selected.startsWith("Oyuncu ")) {
                int id = Integer.parseInt(selected.replace("Oyuncu ", ""));
                try {
                    server.kickPlayer(id);
                    log("[SERVER] Oyuncu " + id + " atıldı.");
                } catch (IOException ex) {
                    log("[UI] Atma hatası: " + ex.getMessage());
                }
            }
        });

        bottomPanel.add(cmbPlayers);
        bottomPanel.add(btnKick);
        bottomPanel.add(btnShutdown);
        add(bottomPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void log(String msg) {
        logArea.append(msg + "\n");
        System.out.println(msg);
    }

    public void updateClientCount() {
        lblClientCount.setText("Bağlı Oyuncu: " + server.getConnectedPlayerCount());
    }

    public void updateScore(int blackScore, int whiteScore) {
        lblScore.setText("Skor - Siyah: " + blackScore + " | Beyaz: " + whiteScore);
    }

    public void updatePlayerList(List<Integer> playerIds) {
        cmbPlayers.removeAllItems();
        for (Integer id : playerIds) {
            cmbPlayers.addItem("Oyuncu " + id);
        }
    }

    public boolean isRunning() {
        return true;
    }

    public int getCurrentClientCount() {
        return server.getConnectedPlayerCount();
    }
} 


/*package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class GoServerUI extends JFrame {

    private JTextArea logArea;
    private JButton btnShutdown;
    private JLabel lblClientCount;
    private JLabel lblScore;

    private JComboBox<String> cmbPlayers;
    private JButton btnKick;

    public GoServerUI() {
        setTitle("Go Sunucu Paneli");
        setSize(700, 500);
        setLayout(new BorderLayout());

        // Log alanı
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, BorderLayout.CENTER);

        // Üst panel: oyuncu sayısı + skor
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        lblClientCount = new JLabel("Bağlı Oyuncu: 0", SwingConstants.CENTER);
        lblClientCount.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(lblClientCount);

        lblScore = new JLabel("Skor - Siyah: 0 | Beyaz: 0", SwingConstants.CENTER);
        lblScore.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(lblScore);

        add(topPanel, BorderLayout.NORTH);

        // Alt panel: sunucu kapat & kick
        JPanel bottomPanel = new JPanel(new FlowLayout());

        btnShutdown = new JButton("SUNUCUYU KAPAT");
        btnShutdown.setFont(new Font("Arial", Font.BOLD, 14));
        btnShutdown.setForeground(Color.RED);
        btnShutdown.addActionListener((ActionEvent e) -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Sunucuyu kapatmak istediğinizden emin misiniz?",
                    "Kapatma Onayı", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    GoServer.shutdown();
                } catch (IOException ex) {
                    log("[UI] Kapatma hatası: " + ex.getMessage());
                }
                System.exit(0);
            }
        });

        // Oyuncu seçimi ve atma
        cmbPlayers = new JComboBox<>();
        btnKick = new JButton("SEÇİLİYİ AT");
        btnKick.addActionListener(e -> {
            String selected = (String) cmbPlayers.getSelectedItem();
            if (selected != null && selected.startsWith("Oyuncu ")) {
                int id = Integer.parseInt(selected.replace("Oyuncu ", ""));
                try {
                    GoServer.kickPlayer(id);
                    log("[SERVER] Oyuncu " + id + " atıldı.");
                } catch (IOException ex) {
                    log("[UI] Atma hatası: " + ex.getMessage());
                }
            }
        });

        bottomPanel.add(cmbPlayers);
        bottomPanel.add(btnKick);
        bottomPanel.add(btnShutdown);
        add(bottomPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void log(String msg) {
        logArea.append(msg + "\n");
        System.out.println(msg);
    }

    public void updateClientCount() {
        lblClientCount.setText("Bağlı Oyuncu: " + GoServer.getConnectedPlayerCount());
    }

    public void updateScore(int blackScore, int whiteScore) {
        lblScore.setText("Skor - Siyah: " + blackScore + " | Beyaz: " + whiteScore);
    }

    public void updatePlayerList(java.util.List<Integer> playerIds) {
        cmbPlayers.removeAllItems();
        for (Integer id : playerIds) {
            cmbPlayers.addItem("Oyuncu " + id);
        }
    }

    public boolean isRunning() {
        return true; // İstersen dinamik olarak değiştirebilirsin shutdown'da false yaparak
    }

    public int getCurrentClientCount() {
        return GoServer.getConnectedPlayerCount(); // aşağıdaki metodla birlikte çalışır
    }

}*/
