package client;

import utils.BoardState;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientUI extends JFrame {

    GoClient client;
    BoardState boardState;
    GameBoard gameBoard;
    private int myColor = 1;
    private JLabel lblScore;
    private boolean opponentConnected = false;

    JTextArea txt_log;
    JTextField txt_input;
    private JButton btn_pass;
    private JButton btn_restart;

    private int playerId = -1;

    private String myName = "-";
    private String opponentName = "-";

    private JTable scoreTable;
    private DefaultTableModel scoreTableModel;
    private int blackWins = 0;
    private int whiteWins = 0;

    public static DefaultListModel<String> lst_clientIds_model;

    public ClientUI() {
        try {
            String username = JOptionPane.showInputDialog(null, "Kullanıcı adınızı girin:", "Giriş", JOptionPane.PLAIN_MESSAGE);
            if (username == null || username.trim().isEmpty()) {
                username = "Oyuncu" + ((int) (Math.random() * 1000));
            }
            this.myName = username;

            boardState = new BoardState(19);
            this.client = new GoClient("127.0.0.1", 12345, this);
            this.client.SendMessage("HELLO_PLAYER#");
            this.client.SendMessage("USERNAME#" + username);
            this.gameBoard = new GameBoard(boardState, this.client);

            initComponents();
            this.client.Listen();
        } catch (Exception ex) {
            DisplayMessage("Bağlantı hatası: " + ex.getMessage());
        }
    }

    private void initComponents() {
        setTitle("Go Oyunu");
        setSize(900, 600);
        setLayout(new BorderLayout());

        txt_log = new JTextArea();
        txt_log.setEditable(false);

        txt_input = new JTextField();
        btn_pass = new JButton("PAS GEÇ");

        lst_clientIds_model = new DefaultListModel<>();
        JList<String> clientList = new JList<>(lst_clientIds_model);

        lblScore = new JLabel("Siyah: 0 | Beyaz: 0", SwingConstants.CENTER);
        lblScore.setFont(new Font("Arial", Font.BOLD, 14));

        // Skor tablosu
        String[] columns = {"Oyuncu Adı", "Renk", "Skor"};
        Object[][] data = {
            {"-", "Siyah", 0},
            {"-", "Beyaz", 0}
        };
        scoreTableModel = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        scoreTable = new JTable(scoreTableModel);
        JScrollPane scoreScrollPane = new JScrollPane(scoreTable);
        scoreScrollPane.setPreferredSize(new Dimension(200, 60));

        // Alt panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(lblScore, BorderLayout.NORTH);
        bottomPanel.add(txt_input, BorderLayout.CENTER);

        // Sağ panel (skor tablosu + oyuncu listesi)
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(scoreScrollPane, BorderLayout.NORTH);
        rightPanel.add(new JScrollPane(clientList), BorderLayout.CENTER);

        add(new JScrollPane(txt_log), BorderLayout.WEST);
        add(gameBoard, BorderLayout.CENTER);
        add(btn_pass, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
        add(rightPanel, BorderLayout.EAST);

        txt_input.addActionListener(e -> {
            String msg = txt_input.getText();
            try {
                client.SendMessage(msg);
            } catch (Exception ex) {
                DisplayMessage("Mesaj gönderilemedi.");
            }
            txt_input.setText("");
        });

        btn_pass.addActionListener(e -> {
            try {
                client.SendMessage("PASS#");
                DisplayMessage("[Siz] Pas geçtiniz. Sıra rakipte.");
            } catch (Exception ex) {
                DisplayMessage("Pas geçme başarısız.");
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    client.Disconnect();
                } catch (Exception ignored) {
                }
            }
        });

        setVisible(true);
    }

    public void DisplayMessage(String msg) {
        txt_log.append("[Sistem] " + msg + "\n");
    }

    public void RestartGame() {
        this.boardState = new BoardState(19);
        this.boardState.setInitialPlayer(this.myColor);
        this.gameBoard = new GameBoard(boardState, client);

        getContentPane().removeAll();

        add(new JScrollPane(txt_log), BorderLayout.WEST);
        add(gameBoard, BorderLayout.CENTER);
        add(btn_pass, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(lblScore, BorderLayout.NORTH);
        bottomPanel.add(txt_input, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        JList<String> clientList = new JList<>(lst_clientIds_model);
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JScrollPane(scoreTable), BorderLayout.NORTH);
        rightPanel.add(new JScrollPane(clientList), BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        if (btn_restart != null) {
            add(btn_restart, BorderLayout.EAST);
        }

        revalidate();
        repaint();
        updateScoreboard();
        DisplayMessage("Yeni oyuncu geldi. Oyun yeniden başladı.");
    }

    public void updateScoreboard() {
        int s1 = boardState.calculateScore(1); // siyah
        int s2 = boardState.calculateScore(2); // beyaz

        String black = (myColor == 1) ? myName : opponentName;
        String white = (myColor == 2) ? myName : opponentName;

        scoreTableModel.setValueAt(black, 0, 0); // siyah oyuncu adı
        scoreTableModel.setValueAt(white, 1, 0); // beyaz oyuncu adı
        scoreTableModel.setValueAt(s1, 0, 2);    // siyah skor
        scoreTableModel.setValueAt(s2, 1, 2);    // beyaz skor
    }

    public void HandleGameOver() {
        int score1 = boardState.calculateScore(1);
        int score2 = boardState.calculateScore(2);

        if (score1 > score2) {
            blackWins++;
            scoreTableModel.setValueAt(blackWins, 0, 2);
        } else if (score2 > score1) {
            whiteWins++;
            scoreTableModel.setValueAt(whiteWins, 1, 2);
        }

        String result = "[Oyun Bitti]\nSiyah: " + score1 + "\nBeyaz: " + score2;
        String winner;
        if (score1 > score2) {
            winner = "Siyah (" + ((myColor == 1) ? myName : opponentName) + ")";
            result += "\nKazanan: " + winner;
        } else if (score2 > score1) {
            winner = "Beyaz (" + ((myColor == 2) ? myName : opponentName) + ")";
            result += "\nKazanan: " + winner;
        } else {
            winner = "Yok (Berabere)";
            result += "\nBerabere!";
        }

        JOptionPane.showMessageDialog(this, result, "Oyun Sonu - Kazanan: " + winner, JOptionPane.INFORMATION_MESSAGE);
        DisplayMessage(result);
        showRestartButton();
    }

    private void showRestartButton() {
        btn_restart = new JButton("YENİDEN OYNA");
        btn_restart.addActionListener(e -> {
            try {
                client.SendMessage("RESTART#");
            } catch (Exception ex) {
                DisplayMessage("Yeniden başlatılamadı.");
            }
        });

        add(btn_restart, BorderLayout.EAST);
        revalidate();
        repaint();
    }

    public boolean isOpponentConnected() {
        return opponentConnected;
    }

    public void setOpponentConnected(boolean value) {
        this.opponentConnected = value;
    }

    public void setPlayerId(int id) {
        this.playerId = id;
        this.myColor = (id == 0) ? 1 : 2;
        if (this.boardState != null) {
            this.boardState.setInitialPlayer(this.myColor);
        }

        // kullanıcı adını tabloya yaz
        if (myColor == 1) {
            scoreTableModel.setValueAt(myName, 0, 0);
        } else {
            scoreTableModel.setValueAt(myName, 1, 0);
        }
    }

    public void setOpponentName(String name) {
        this.opponentName = name;
        if (myColor == 1) {
            scoreTableModel.setValueAt(name, 1, 0);
        } else {
            scoreTableModel.setValueAt(name, 0, 0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClientUI::new);
    }
}

/*package client;

import utils.BoardState;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;

public class ClientUI extends JFrame {

    GoClient client;
    BoardState boardState;
    GameBoard gameBoard;
    private int myColor = 1;
    private JLabel lblScore;
    private boolean opponentConnected = false;
    private JTable scoreTable;
    private DefaultTableModel scoreTableModel;
    private int blackWins = 0;
    private int whiteWins = 0;

    JTextArea txt_log;
    JTextField txt_input;
    private JButton btn_pass;
    private JButton btn_restart;

    private int playerId = -1;
    private String myName = "";         // Kullanıcının kendi adı
    private String opponentName = "-";  // Varsayılan rakip adı

    public static DefaultListModel<String> lst_clientIds_model;

    public ClientUI() {
        try {
            String username = JOptionPane.showInputDialog(null, "Kullanıcı adınızı girin:", "Giriş", JOptionPane.PLAIN_MESSAGE);
            if (username == null || username.trim().isEmpty()) {
                username = "Oyuncu" + ((int) (Math.random() * 1000));
            }
            this.myName = username; // ← 🔥 tam buraya ekle

            // 🧠 Önce board ve client oluştur
            boardState = new BoardState(19);
            this.client = new GoClient("127.0.0.1", 12345, this);
            this.client.SendMessage("HELLO_PLAYER#"); // 🔴 Bu mesaj sunucuya oyuncu olduğunu bildirir
            this.client.SendMessage("USERNAME#" + username);

            this.gameBoard = new GameBoard(boardState, this.client);

            // 🔧 Arayüz bileşenleri
            initComponents();

            // 🔊 Dinlemeye başla
            this.client.Listen();

        } catch (Exception ex) {
            DisplayMessage("Bağlantı hatası: " + ex.getMessage());
        }
    }

    private void initComponents() {
        setTitle("Go Oyunu");
        setSize(800, 600);
        setLayout(new BorderLayout());
        String[] columns = {"Oyuncu Adı", "Renk", "Skor"};
        Object[][] data = {
            {"-", "Siyah", 0},
            {"-", "Beyaz", 0}
        };
        scoreTableModel = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // hücreler değiştirilemez
            }
        };
        scoreTable = new JTable(scoreTableModel);
        JScrollPane scoreScrollPane = new JScrollPane(scoreTable);
        scoreScrollPane.setPreferredSize(new Dimension(200, 60));

        txt_log = new JTextArea();
        txt_log.setEditable(false);

        txt_input = new JTextField();
        btn_pass = new JButton("PAS GEÇ");

        lst_clientIds_model = new DefaultListModel<>();
        JList<String> clientList = new JList<>(lst_clientIds_model);

        lblScore = new JLabel("Siyah: 0 | Beyaz: 0", SwingConstants.CENTER);
        lblScore.setFont(new Font("Arial", Font.BOLD, 14));

        // 🟡 Alt panel (score + input)
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(lblScore, BorderLayout.NORTH);
        bottomPanel.add(txt_input, BorderLayout.CENTER);

        // 🟡 Sol panel (log)
        add(new JScrollPane(txt_log), BorderLayout.WEST);

        // 🟡 Orta panel (tahta)
        add(gameBoard, BorderLayout.CENTER);

        // 🟡 Üst panel (pas butonu)
        add(btn_pass, BorderLayout.NORTH);

        // 🟡 Sağ panel (oyuncu listesi)
        add(new JScrollPane(clientList), BorderLayout.EAST);

        // 🟡 Alt paneli ekle (skor + input)
        add(bottomPanel, BorderLayout.SOUTH);

        // Kapatma işlemi
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    client.Disconnect();
                } catch (Exception ignored) {
                }
            }
        });

        setVisible(true);
    }

    public void DisplayMessage(String msg) {
        txt_log.append("[Sistem] " + msg + "\n");
    }

    public void setOpponentName(String name) {
        this.opponentName = name;
    }

    public void RestartGame() {
        this.boardState = new BoardState(19);
        this.boardState.setInitialPlayer(this.myColor);
        this.gameBoard = new GameBoard(boardState, client);

        getContentPane().removeAll();
        add(new JScrollPane(txt_log), BorderLayout.WEST);
        add(gameBoard, BorderLayout.CENTER);
        add(txt_input, BorderLayout.SOUTH);
        add(btn_pass, BorderLayout.NORTH);
        add(new JScrollPane(new JList<>(lst_clientIds_model)), BorderLayout.EAST);

        if (btn_restart != null) {
            add(btn_restart, BorderLayout.EAST);
        }

        revalidate();
        repaint();
        DisplayMessage("Yeni oyuncu geldi. Oyun yeniden başladı.");
    }

    public void updateScoreboard() {
        int s1 = boardState.calculateScore(1);
        int s2 = boardState.calculateScore(2);
        lblScore.setText("Siyah: " + s1 + " | Beyaz: " + s2);
    }

    public void HandleGameOver() {
        int score1 = boardState.calculateScore(1);
        int score2 = boardState.calculateScore(2);
        String result = "[Oyun Bitti]\nSiyah: " + score1 + "\nBeyaz: " + score2;

        String winner;
        if (score1 > score2) {
            winner = "Siyah (1)";
            result += "\nKazanan: " + winner;
        } else if (score2 > score1) {
            winner = "Beyaz (2)";
            result += "\nKazanan: " + winner;
        } else {
            winner = "Yok (Berabere)";
            result += "\nBerabere!";
        }

        JOptionPane.showMessageDialog(this, result, "Oyun Sonu - Kazanan: " + winner, JOptionPane.INFORMATION_MESSAGE);
        DisplayMessage(result);
        showRestartButton();
    }

    private void showRestartButton() {
        btn_restart = new JButton("YENİDEN OYNA");
        btn_restart.addActionListener(e -> {
            try {
                client.SendMessage("RESTART#");
            } catch (Exception ex) {
                DisplayMessage("Yeniden başlatılamadı.");
            }
        });

        add(btn_restart, BorderLayout.EAST);
        revalidate();
        repaint();
    }

    public boolean isOpponentConnected() {
        return opponentConnected;
    }

    public void setOpponentConnected(boolean value) {
        this.opponentConnected = value;
    }

    public void setPlayerId(int id) {
        this.playerId = id;
        this.myColor = (id == 0) ? 1 : 2;
        if (this.boardState != null) {
            this.boardState.setInitialPlayer(this.myColor);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClientUI::new);
    }
}*/

 /*package client;

import utils.BoardState;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientUI extends JFrame {

    GoClient client;
    BoardState boardState;
    GameBoard gameBoard;
    private int myColor = 1;
    private JLabel lblScore;
    private boolean opponentConnected = false;

    JTextArea txt_log;
    JTextField txt_input;
    private JButton btn_pass;
    private JButton btn_restart;

    private int playerId = -1;

    public static DefaultListModel<String> lst_clientIds_model;

    public ClientUI() {
        try {
            initComponents();
            this.client = new GoClient("127.0.0.1", 12345, this);
            this.client.Listen();
        } catch (Exception ex) {
            DisplayMessage("Bağlantı hatası: " + ex.getMessage());
        }
    }

    private void initComponents() {
        setTitle("Go Oyunu");
        setSize(800, 600);
        setLayout(new BorderLayout());

        txt_log = new JTextArea();
        txt_log.setEditable(false);

        txt_input = new JTextField();
        btn_pass = new JButton("PAS GEÇ");

        lst_clientIds_model = new DefaultListModel<>();
        JList<String> clientList = new JList<>(lst_clientIds_model);

        boardState = new BoardState(19);
        gameBoard = new GameBoard(boardState, this.client); // ✅ client referansını gönder

        lblScore = new JLabel("Siyah: 0 | Beyaz: 0"); // 👈 skor etiketi oluştur
        add(lblScore, BorderLayout.SOUTH);
        txt_input.addActionListener(e -> {
            String msg = txt_input.getText();
            try {
                client.SendMessage(msg);
            } catch (Exception ex) {
                DisplayMessage("Mesaj gönderilemedi.");
            }
            txt_input.setText("");
        });

        btn_pass.addActionListener(e -> {
            try {
                client.SendMessage("PASS#");
                DisplayMessage("[Siz] Pas geçtiniz. Sıra rakipte.");
            } catch (Exception ex) {
                DisplayMessage("Pas geçme başarısız.");
            }
        });

        add(new JScrollPane(txt_log), BorderLayout.WEST);
        add(gameBoard, BorderLayout.CENTER);
        add(txt_input, BorderLayout.SOUTH);
        add(btn_pass, BorderLayout.NORTH);
        add(new JScrollPane(clientList), BorderLayout.EAST); // yeni eklenen istemci listesi

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    client.Disconnect();
                } catch (Exception ignored) {
                }
            }
        });

        setVisible(true);
    }

    public void DisplayMessage(String msg) {
        txt_log.append("[Sistem] " + msg + "\n");
    }

    public void RestartGame() {
        this.boardState = new BoardState(19);
        this.boardState.setInitialPlayer(this.myColor); // 👈 renk atanıyor
        this.gameBoard = new GameBoard(boardState, client); // Client bağlandı

        getContentPane().removeAll();
        add(new JScrollPane(txt_log), BorderLayout.WEST);
        add(gameBoard, BorderLayout.CENTER);
        add(txt_input, BorderLayout.SOUTH);
        add(btn_pass, BorderLayout.NORTH);
        add(new JScrollPane(new JList<>(lst_clientIds_model)), BorderLayout.EAST); // tekrar bağla

        if (btn_restart != null) {
            add(btn_restart, BorderLayout.EAST);
        }

        revalidate();
        repaint();
        DisplayMessage("Yeni oyuncu geldi. Oyun yeniden başladı.");
    }

    public void updateScoreboard() {
        int s1 = boardState.calculateScore(1);
        int s2 = boardState.calculateScore(2);
        lblScore.setText("Siyah: " + s1 + " | Beyaz: " + s2);
    }

    public void HandleGameOver() {
        int score1 = boardState.calculateScore(1);
        int score2 = boardState.calculateScore(2);
        String result = "[Oyun Bitti]\nSiyah: " + score1 + "\nBeyaz: " + score2;

        String winner;
        if (score1 > score2) {
            winner = "Siyah (1)";
            result += "\nKazanan: " + winner;
        } else if (score2 > score1) {
            winner = "Beyaz (2)";
            result += "\nKazanan: " + winner;
        } else {
            winner = "Yok (Berabere)";
            result += "\nBerabere!";
        }

        // 🔥 Kazananı başlıkta da göster
        JOptionPane.showMessageDialog(this, result, "Oyun Sonu - Kazanan: " + winner, JOptionPane.INFORMATION_MESSAGE);

        DisplayMessage(result);
        showRestartButton();
    }

    private void showRestartButton() {
        btn_restart = new JButton("YENİDEN OYNA");
        btn_restart.addActionListener(e -> {
            try {
                client.SendMessage("RESTART#");
            } catch (Exception ex) {
                DisplayMessage("Yeniden başlatılamadı.");
            }
        });

        add(btn_restart, BorderLayout.EAST);
        revalidate();
        repaint();
    }

    public boolean isOpponentConnected() {
        return opponentConnected;
    }
    public void setOpponentConnected(boolean value) {
    this.opponentConnected = value;
}
    

    public void setPlayerId(int id) {
        this.playerId = id;
        this.myColor = (id == 0) ? 1 : 2;
        if (this.boardState != null) {
            this.boardState.setInitialPlayer(this.myColor);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClientUI::new);
    }
} */

 /*package client;

import utils.BoardState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientUI extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private GoClient client;
    private BoardState boardState;
    private GameBoard gameBoard;

    public ClientUI() {
        setTitle("Go Oyunu");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        inputField = new JTextField();

        boardState = new BoardState(19);
        client = new GoClient("127.0.0.1", 12345, this);
        gameBoard = new GameBoard(boardState, client);

        add(new JScrollPane(chatArea), BorderLayout.WEST);
        add(gameBoard, BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);

        inputField.addActionListener(e -> {
            String msg = inputField.getText();
            if (!msg.isEmpty()) {
                client.sendMessage(msg);
                inputField.setText("");
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.disconnect();
            }
        });

        setVisible(true);
    }

    public void receiveMessage(String msg) {
        chatArea.append("[Rakip] " + msg + "\n");
    }

    public void displayMessage(String msg) {
        chatArea.append("[Sistem] " + msg + "\n");
    }

    public void restartGame() {
        boardState = new BoardState(19);
        gameBoard = new GameBoard(boardState, client);
        getContentPane().removeAll();
        add(new JScrollPane(chatArea), BorderLayout.WEST);
        add(gameBoard, BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);
        revalidate();
        repaint();
        displayMessage("Yeni oyuncu geldi. Oyun yeniden başladı.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClientUI::new);
    }
}
 */
