package utils;

import java.util.HashSet;
import java.util.Set;

public class BoardState {

    private int blackCaptures = 0;
    private int whiteCaptures = 0;

    private int[][] board;
    private int currentPlayer;

    public BoardState(int size) {
        board = new int[size][size];
        currentPlayer = 1;
    }

    public boolean placeStone(int row, int col) {
        if (board[row][col] != 0) {
            return false;
        }

        // Geçici taşı yerleştir
        board[row][col] = currentPlayer;

        // 🔍 Rakip taşlardan düşen var mı? (önce kontrol et)
        int beforeCaptureCount = getTotalCaptures();

        removeCapturedStones(3 - currentPlayer);

        int afterCaptureCount = getTotalCaptures();

        // 🔁 Kendi taş grubunun özgürlüğü kaldı mı?
        boolean[][] visited = new boolean[board.length][board.length];
        Set<String> selfGroup = new HashSet<>();
        boolean hasLiberty = hasLiberty(row, col, currentPlayer, visited, selfGroup);

        if (!hasLiberty && beforeCaptureCount == afterCaptureCount) {
            // ❌ İntihar hamlesi → taşı geri al
            board[row][col] = 0;
            return false;
        }

        // Hamle geçerli
        currentPlayer = 3 - currentPlayer;
        return true;
    }

    private int getTotalCaptures() {
        return blackCaptures + whiteCaptures;
    }


    /*public boolean placeStone(int row, int col) {
        if (board[row][col] != 0) {
            return false;
        }

        board[row][col] = currentPlayer;
        removeCapturedStones(3 - currentPlayer);
        currentPlayer = 3 - currentPlayer;
        return true;
    }*/
    private void removeCapturedStones(int opponent) {
        int size = board.length;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (board[r][c] == opponent) {
                    boolean[][] visited = new boolean[size][size];
                    Set<String> group = new HashSet<>();
                    if (!hasLiberty(r, c, opponent, visited, group)) {
                        for (String s : group) {
                            String[] parts = s.split(",");
                            board[Integer.parseInt(parts[0])][Integer.parseInt(parts[1])] = 0;

                            if (currentPlayer == 1) {
                                blackCaptures++;
                            } else {
                                whiteCaptures++;
                            }
                        }
                    }
                }
            }
        }
    }

    /*
    bozulursa çalışan versiyondur bunu kullanırsın 
    private void removeCapturedStones(int opponent) {
        int size = board.length;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (board[r][c] == opponent) {
                    boolean[][] visited = new boolean[size][size]; // ⬅️ her grup için yeni visited
                    Set<String> group = new HashSet<>();
                    if (!hasLiberty(r, c, opponent, visited, group)) {
                        for (String s : group) {
                            String[] parts = s.split(",");
                            board[Integer.parseInt(parts[0])][Integer.parseInt(parts[1])] = 0;
                        }
                    }
                }
            }
        }
    }*/
 /*  private void removeCapturedStones(int opponent) {
        int size = board.length;
        boolean[][] visited = new boolean[size][size];

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (board[r][c] == opponent && !visited[r][c]) {
                    Set<String> group = new HashSet<>();
                    if (!hasLiberty(r, c, opponent, visited, group)) {
                        for (String s : group) {
                            String[] parts = s.split(",");
                            board[Integer.parseInt(parts[0])][Integer.parseInt(parts[1])] = 0;
                        }
                    }
                }
            }
        }
    }*/
    private boolean hasLiberty(int r, int c, int player, boolean[][] visited, Set<String> group) {
        if (r < 0 || c < 0 || r >= board.length || c >= board.length) {
            return false;
        }
        if (visited[r][c]) {
            return false;
        }

        visited[r][c] = true;

        if (board[r][c] == 0) {
            return true; // boşluk bulundu
        }
        if (board[r][c] != player) {
            return false;
        }

        group.add(r + "," + c);

        boolean liberty = false;
        liberty |= hasLiberty(r + 1, c, player, visited, group);
        liberty |= hasLiberty(r - 1, c, player, visited, group);
        liberty |= hasLiberty(r, c + 1, player, visited, group);
        liberty |= hasLiberty(r, c - 1, player, visited, group);

        return liberty;
    }

    /* private boolean hasLiberty(int r, int c, int player, boolean[][] visited, Set<String> group) {
        if (r < 0 || c < 0 || r >= board.length || c >= board.length) {
            return false;
        }
        if (visited[r][c]) {
            return false;
        }

        visited[r][c] = true;
        group.add(r + "," + c);

        if (board[r][c] == 0) {
            return true;
        }
        if (board[r][c] != player) {
            return false;
        }

        return hasLiberty(r + 1, c, player, visited, group)
                || hasLiberty(r - 1, c, player, visited, group)
                || hasLiberty(r, c + 1, player, visited, group)
                || hasLiberty(r, c - 1, player, visited, group);
    }*/
    public int getStone(int r, int c) {
        return board[r][c];
    }

    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = 0;
            }
        }
        currentPlayer = 1;
        blackCaptures = 0;
        whiteCaptures = 0;

    }

    public int calculateScore(int player) {
        return (player == 1) ? blackCaptures : whiteCaptures;
    }

    /*
    public int calculateScore(int player) {
        int score = 0;
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == player) {
                    score++;
                }
            }
        }
        return score;
    }*/
    private int myColor = 1; // default siyah

    public void setInitialPlayer(int color) {
        this.myColor = color;
    }

    public int getMyColor() {
        return myColor;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int player) {
        this.currentPlayer = player;
    }

}
