package com.example.tictactoe;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Model {


    private SimpleIntegerProperty playerScore = new SimpleIntegerProperty();
    private SimpleIntegerProperty computerScore = new SimpleIntegerProperty();
    private int playerTurn = 0;
    //private Text playerText = new Text();
    private ObservableList<Button> buttons = FXCollections.observableArrayList();


//    public Text getPlayerText() {
//        return playerText;
//    }
//
//    public void setPlayerText(Text playerText) {
//        this.playerText = playerText;
//    }

    public ObservableList<Button> getButtons() {
        return buttons;
    }

    public void setButtons(ObservableList<Button> gameButtons) {
        this.buttons = gameButtons;
    }

    public void resetGameData() {
        for (Button button : this.buttons) {
            button.setText("");
            button.setDisable(false);
        }
        playerScore.set(0);
        computerScore.set(0);
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

    public void updateScores() {
       setPlayerScore(getPlayerScore() + 1);
       setComputerScore(getComputerScore() + 1);
    }

    public SimpleIntegerProperty playerScoreProperty() {
        return playerScore;
    }

    public SimpleIntegerProperty computerScoreProperty() {
        return computerScore;
    }
}

