package com.example.tictactoe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Model {


    //private int playerTurn = 0;
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
}
