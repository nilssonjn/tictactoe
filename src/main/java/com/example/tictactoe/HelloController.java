package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class HelloController {
    private Model model = new Model();
    Random random = new Random();

    public void initialize() {
        buttons = new ArrayList<>(Arrays.asList(button0, button1, button2, button3, button4, button5, button6, button7, button8));
        for (Button button : buttons) {
            disableButtonAfterClick(button);
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
    @FXML
    private Text winnerText;

    private int playerTurn = 0;
    ArrayList<Button> buttons = new ArrayList<>();

    @FXML
    private void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
        winnerText.setText("");
        //code for restarting game
    }

    public void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
        //code for resetting button, checking that button cant be pressed again
    }

    public void checkWin() {
        //code for checking if someone has won
    }

    public void disableButtonAfterClick(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkWin();
            computerTurn();
        });
    }

    public void playerTurn() {
        //code for player turn
    }

    public void computerTurn() {
        if (!model.isBoardFull() && !model.checkWin()) {
            ArrayList<Button> availableButtons = new ArrayList<>();
            for (Button button : buttons) {
                if (!button.isDisabled()) availableButtons.add(button);
            }
            if (!availableButtons.isEmpty()) {
                Button randomButton = availableButtons.get(random.nextInt(availableButtons.size()));
                setComputerSymbol(randomButton);
                randomButton.setDisable(true);
                checkWin();
            }
        }
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

    public void setComputerSymbol(Button button) {
        if (playerTurn % 2 == 0) {
            button.setText("X");
            playerTurn = 1;
        } else {
            button.setText("O");
        }
        playerTurn = (playerTurn + 1) % 2;
    }
}