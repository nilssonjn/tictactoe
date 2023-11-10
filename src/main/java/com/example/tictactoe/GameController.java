package com.example.tictactoe;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.List;

public class GameController {

    private final GameModel model = new GameModel();

    @FXML
    private Label computerScore;
    @FXML
    private Label playerScore;
    @FXML
    private Text showWinner;

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

    private List<Button> buttons;

    public void initialize() {
        bindProperties();
        buttons = Arrays.asList(button0, button1, button2, button3, button4, button5, button6, button7, button8);
        for (Button button : buttons) {
            button.setFocusTraversable(false);
        }
    }

    private void bindProperties() {
        playerScore.textProperty().bind(model.playerScoreProperty().asString());
        computerScore.textProperty().bind(model.computerScoreProperty().asString());
    }

    public void handleButtonClick(MouseEvent event) {
        Button clickedButton = (Button) event.getSource();
        buttons.indexOf((Button) event.getSource());
        model.setPlayerSymbol(clickedButton);
    }

    public void exitGameWhenClicked() {
        Platform.exit();
    }

    public void resetGame() {
        model.resetGameData();
        showWinner.setText("Tic-Tac-Toe");
    }

    public void resetGameWhenClicked() {
        resetGame();
    }

    public void playAgainButton() {
        model.playAgainKeepingScores();
        showWinner.setText("Tic-Tac-Toe");
    }
}