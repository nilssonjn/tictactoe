package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Arrays;

public class HelloController {
    private Model model = new Model();

    public void initialize() {
        buttons = new ArrayList<>(Arrays.asList(button0, button1, button2, button3, button4, button5, button6, button7, button8));
        for (Button button : buttons) {
            buttonsCantBePressed(button);
            button.setFocusTraversable(false);
        }

    }

    @FXML
    private Button button0;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;

    private int playerTurn = 0;
    ArrayList<Button> buttons = new ArrayList<>();

    @FXML
    private void restartGame(ActionEvent event) {
        //code for restarting game
    }

    public void resetButton(Button button) {
        //code for resetting button, checking that button cant be pressed again
    }

    public void checkWin() {
        //code for checking if someone has won
    }

    public void buttonsCantBePressed(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkWin();
        });
    }

    public void playerTurn() {
        //code for player turn
    }

    public void computerTurn() {
        //code for computer turn
    }

    public void setPlayerSymbol(Button button) {
        if (playerTurn % 2 == 0) {
            button.setText("X");
            playerTurn = 1;
        } else {
            button.setText("O");
            playerTurn = 0;
        }
    }
}