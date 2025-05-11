package client;

import utils.BoardState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import common.Message;
import server.GoServerUI;

public class GameBoard extends JPanel {

    private static final int SIZE = 19;
    private static final int CELL_SIZE = 30;
    private static final int PADDING = 40;
    public static GoServerUI serverUIs;
    public static ClientUI ClientUI;

    private BoardState boardState;
    private GoClient client;

    public GameBoard(BoardState boardState, GoClient client) {
        this.boardState = boardState;
        this.client = client;
        

        setPreferredSize(new Dimension(SIZE * CELL_SIZE + PADDING * 2, SIZE * CELL_SIZE + PADDING * 2));
        setBackground(new Color(232, 197, 139)); // Go tahtası rengi

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               // Bu düzelicek. Şu opponentconnected flagi yerine counter koyulabilir.
                
                /* if (!client.ui.isOpponentConnected()) {
                    JOptionPane.showMessageDialog(null,
                            "Rakip oyuncu henüz bağlanmadı. Oyun başlatılamaz.",
                            "Rakip Bekleniyor", JOptionPane.WARNING_MESSAGE);
                    return;
                }*/
                        

                int row = (e.getY() - PADDING + CELL_SIZE / 2) / CELL_SIZE;
                int col = (e.getX() - PADDING + CELL_SIZE / 2) / CELL_SIZE;

                // Tahta sınırlarını aşmamalı
                if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
                    return;
                }

                // SIRA SİZDE Mİ? Eğer değilse taş koyma!
                if (boardState.getCurrentPlayer() != boardState.getMyColor()) {
                       

                    return;
                }

                // Kare boşsa taş koy
                if (boardState.getStone(row, col) == 0) {
                    if (boardState.placeStone(row, col)) {
                        repaint();
                        String data = row + "," + col;
                        String msg = Message.GenerateMsg(Message.Type.MSGFROMCLIENT, data);
                        try {
                            client.SendMessage(msg);
                        } catch (Exception ex) {
                            System.out.println("Taş gönderilemedi: " + ex.getMessage());
                        }
                        client.ui.updateScoreboard();  // ← TAM BURAYA KOY

                    }
                }
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Izgara çizimi
        for (int i = 0; i < SIZE; i++) {
            g.drawLine(PADDING, PADDING + i * CELL_SIZE, PADDING + (SIZE - 1) * CELL_SIZE, PADDING + i * CELL_SIZE);
            g.drawLine(PADDING + i * CELL_SIZE, PADDING, PADDING + i * CELL_SIZE, PADDING + (SIZE - 1) * CELL_SIZE);
        }

        // Taşlar
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int val = boardState.getStone(row, col);
                if (val != 0) {
                    g.setColor(val == 1 ? Color.BLACK : Color.WHITE);
                    g.fillOval(PADDING + col * CELL_SIZE - 10, PADDING + row * CELL_SIZE - 10, 20, 20);
                }
            }
        }
    }
}
