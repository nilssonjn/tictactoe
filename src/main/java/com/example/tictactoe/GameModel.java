package com.example.tictactoe;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel {

    private static final int PLAYER_ONE = 1; //player
    private static final int PLAYER_TWO = 0; //computer
    private static final String PLAYER_X_SYMBOL = "X";
    private static final String PLAYER_O_SYMBOL = "O";
    private int remainingEmptySlots;
    private GameStatus stateOfTheGame;
    private final BooleanProperty gameOver = new SimpleBooleanProperty();
    private final SimpleIntegerProperty playerScore = new SimpleIntegerProperty();
    private final SimpleIntegerProperty computerScore = new SimpleIntegerProperty();
    private final SimpleIntegerProperty currentPlayer = new SimpleIntegerProperty();
    private final SimpleStringProperty announcingWinner = new SimpleStringProperty();
    private final ObservableList<SimpleStringProperty> buttonSymbolValues = FXCollections.observableArrayList();
    private final ObservableList<SimpleBooleanProperty> isButtonDisabled = FXCollections.observableArrayList();

    public boolean isGameOver() {
        return gameOver.get();
    }

    public BooleanProperty gameOverProperty() {
        return gameOver;
    }

    public int getRemainingEmptySlots() {
        return remainingEmptySlots;
    }

    public void setRemainingEmptySlots(int remainingEmptySlots) {
        this.remainingEmptySlots = remainingEmptySlots;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver.set(gameOver);
    }

    public GameStatus getStateOfTheGame() {
        return stateOfTheGame;
    }

    public void checkAndUpdateGameStatus(GameStatus gameState) {
        this.stateOfTheGame = gameState;
    }

    public ObservableList<SimpleStringProperty> getButtonSymbolsList() {
        return buttonSymbolValues;
    }

    public ObservableList<SimpleBooleanProperty> getButtonDisabledList() {
        return isButtonDisabled;
    }

    public String getAnnouncingWinner() {
        return announcingWinner.get();
    }

    public SimpleStringProperty announcingWinnerProperty() {
        return announcingWinner;
    }

    public void setAnnouncingWinner(String announcingWinner) {
        this.announcingWinner.set(announcingWinner);
    }

    public int getCurrentPlayer() {
        return currentPlayer.get();
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer.set(currentPlayer);
    }

    public void initializeButtonLists(int size) {
        for (int i = 0; i < size; i++) {
            buttonSymbolValues.add(new SimpleStringProperty());
            isButtonDisabled.add(new SimpleBooleanProperty());
        }
        setRemainingEmptySlots(size); // set the total number of empty slots/buttons in the game
    }

    public String setPlayerSymbol() {
        return getCurrentPlayer() == 1 ? PLAYER_X_SYMBOL : PLAYER_O_SYMBOL;
    }

    public void switchPlayerTurn() {
        setCurrentPlayer(getCurrentPlayer() == PLAYER_ONE ? PLAYER_TWO : PLAYER_ONE);
    }

    private final int[][] winningCombinations = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // horizontal
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // vertical
            {0, 4, 8}, {2, 4, 6}// diagonal
    };

    public void announceWinner(int currentPlayer) {
        String winner = currentPlayer == PLAYER_ONE ? "Player wins!" : "Computer wins!";
        setAnnouncingWinner(winner);
        updatePlayerAndComputerScore(currentPlayer);
    }

    public Integer getPlayerScore() {
        return playerScore.get();
    }

    public void setPlayerScore(Integer playerScore) {
        this.playerScore.set(playerScore);
    }

    public Integer getComputerScore() {
        return computerScore.get();
    }

    public void setComputerScore(Integer computerScore) {
        this.computerScore.set(computerScore);
    }

    public SimpleIntegerProperty playerScoreProperty() {
        return playerScore;
    }

    public SimpleIntegerProperty computerScoreProperty() {
        return computerScore;
    }

    public void updatePlayerAndComputerScore(int playerTurn) {
        if (playerTurn == PLAYER_ONE) {
            setPlayerScore(getPlayerScore() + 1);
        } else {
            setComputerScore(getComputerScore() + 1);
        }
    }

    public void addPlayerSymbolAndDisable(int index) {
        System.out.println("Adding symbol to index: " + index);
        getButtonSymbolsList().get(index).setValue(setPlayerSymbol()); // set the symbol for the specified index
        getButtonDisabledList().get(index).setValue(true); // disable the button at the specified index
    }

    public boolean checkForWinner() {
        StringBuilder playerSymbols = new StringBuilder(); // to store the player symbols for each combination, reset after each iteration
        for (int[] winningCombination : winningCombinations) {
            playerSymbols.setLength(0);
            for (int i : winningCombination) {
                playerSymbols.append(getButtonSymbolsList().get(i).getValue());
            }
            if (isWinningCombination(playerSymbols.toString())) {
                return true;
            }
        }
        return false;
    }

    private boolean isWinningCombination(String symbols) {
        return symbols.equals("XXX") || symbols.equals("OOO");
    }

    private void disableButtonsAfterWin() {
        isButtonDisabled.forEach(e -> e.set(true));
    }

    public int randomComputerMove() {
        Random random = new Random();
        List<Integer> emptyButtons = new ArrayList<>();

        for (int i = 0; i < getButtonSymbolsList().size(); i++) {
            if (isEmpty(getButtonSymbolsList().get(i).getValue())) emptyButtons.add(i);
        }
        System.out.println("Empty Buttons: " + emptyButtons);

        if (!emptyButtons.isEmpty()) {
            return emptyButtons.get(random.nextInt(emptyButtons.size()));
        }
        return -1; //there is no available moves
    }

    private static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public void checkAndUpdateGameStatus() {
        setRemainingEmptySlots(getRemainingEmptySlots() - 1);
        GameStatus status = determineGameStatus();
        checkAndUpdateGameStatus(status);
    }

    private GameStatus determineGameStatus() {
        if (checkForWinner()) {
            return GameStatus.WINNER;
        } else if (getRemainingEmptySlots() == 0) {
            return GameStatus.NO_WINNER;
        } else {
            return GameStatus.PLAYING;
        }
    }

    public void startGameLogic(int index) {
        addPlayerSymbolAndDisable(index);
        checkAndUpdateGameStatus();

        if (getStateOfTheGame() == GameStatus.PLAYING) {
            switchPlayerTurn();
            addPlayerSymbolAndDisable(randomComputerMove());
            checkAndUpdateGameStatus();
        }
        performActionBasedOnGameState(getStateOfTheGame());
    }

    private void handleWinner() {
        announceWinner(getCurrentPlayer());
        setGameOver(true);
        disableButtonsAfterWin();
    }

    private void handleNoWinner() {
        setAnnouncingWinner("Draw!");
        setGameOver(true);
    }

    public void performActionBasedOnGameState(GameStatus gameState) {
        switch (gameState) {
            case PLAYING -> switchPlayerTurn();
            case WINNER -> handleWinner();
            case NO_WINNER -> handleNoWinner();
        }
    }

    public void resetGame() {
        setCurrentPlayer(PLAYER_ONE);
        setGameOver(false);
        setRemainingEmptySlots(getButtonSymbolsList().size());
        checkAndUpdateGameStatus(GameStatus.PLAYING);
        resetButtonsStates();
    }

    private void resetButtonsStates() {
        isButtonDisabled.forEach(e -> e.set(false));
        buttonSymbolValues.forEach(e -> e.set(""));
    }

    public void resetScoresAndGameBoard() {
        resetGame();
        setPlayerScore(0);
        setComputerScore(0);
    }

    public enum GameStatus {
        PLAYING, WINNER, NO_WINNER
    }
}
