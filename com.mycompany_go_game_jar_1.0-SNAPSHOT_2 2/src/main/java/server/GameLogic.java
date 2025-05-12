package server;

import utils.BoardState;

public class GameLogic {

    private int currentPlayerId = 0;
    private int passCount = 0;
    private BoardState boardState;

    public synchronized boolean isValidMove(int playerId) {
        return playerId == currentPlayerId;
    }

    public synchronized void switchTurn() {
        currentPlayerId = 1 - currentPlayerId;
    }

    public void setBoardState(BoardState boardState) {
        this.boardState = boardState;
    }

    public int getScore(int color) {
        return boardState != null ? boardState.calculateScore(color) : 0;
    }

    public synchronized void reset() {
        currentPlayerId = 0;
        passCount = 0;
    }

    public synchronized int getCurrentPlayer() {
        return currentPlayerId;
    }

    public synchronized boolean handlePass(int playerId) {
        if (playerId != currentPlayerId) {
            return false;
        }

        passCount++;

        if (passCount >= 2) {
            return true; // oyun bitmeli
        }

        switchTurn();
        return false;
    }

    public synchronized void clearPassCount() {
        passCount = 0;
    }
}
