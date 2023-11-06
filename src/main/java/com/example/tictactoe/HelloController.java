package com.example.tictactoe;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.util.List;

public class HelloController {

    private Model model = new Model();

    @FXML
    public Label computerScore;
    @FXML
    public Label playerScore;
    @FXML
    public Button exitButton;
    @FXML
    public Text showWinner;
    @FXML
    public Button restartGame;

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
    private List<Button> buttons;

    public void initialize() {
        bindProperties();
        ObservableList<Button> gameButtons = FXCollections.observableArrayList(button0, button1, button2, button3, button4, button5, button6, button7, button8);
        model.setButtons(gameButtons);

        for (Button button : model.getButtons()) { //removes focus from buttons
            button.setFocusTraversable(false);
        }
    }
    private void bindProperties() {
        playerScore.textProperty().bind(model.playerScoreProperty().asString());
        computerScore.textProperty().bind(model.computerScoreProperty().asString());
    }

    public void handleButtonClick(MouseEvent event) {
        Button clickedButton = (Button) event.getSource();
        model.setPlayerSymbol(clickedButton);

        //model.checkWin(button0, button1, button2, button3, button4, button5, button6, button7, button8, showWinner);
        model.randomComputerMove();
        model.checkWin(button0, button1, button2, button3, button4, button5, button6, button7, button8, showWinner);
    }

    public void ExitGameWhenClicked() {
        Platform.exit();
    }

    public void resetGame() {
        model.resetGameData();
        showWinner.setText("Tic-Tac-Toe");
    }

    public void ResetGameWhenClicked() {
        resetGame();
    }
}