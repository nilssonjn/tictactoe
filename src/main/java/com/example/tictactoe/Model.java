package com.example.tictactoe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Model {


    private int playerScore = 0;
    private int computerScore = 0;
    private int playerTurn = 0;
    private Text playerText = new Text();
    private ObservableList<Button> buttons = FXCollections.observableArrayList();


    public Text getPlayerText() {
        return playerText;
    }

    public void setPlayerText(Text playerText) {
        this.playerText = playerText;
    }

    public ObservableList<Button> getButtons() {
        return buttons;
    }

    public void setButtons(ObservableList<Button> gameButtons) {
        this.buttons = gameButtons;
    }

    public void resetGameData() {
        for (Button button : this.buttons) {
            button.setText("");
        }
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public void setComputerScore(int computerScore) {
        this.computerScore = computerScore;
    }
    public void setPlayerSymbol(Button clickedButton) {
        if (playerTurn % 2 == 0) {
            clickedButton.setText("X");
            playerTurn = 1;
        } else {
            clickedButton.setText("O");
            playerTurn = 0;
        }
        clickedButton.setDisable(true);
    }

}
