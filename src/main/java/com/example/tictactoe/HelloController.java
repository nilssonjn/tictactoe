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
        ObservableList<Button> gameButtons = FXCollections.observableArrayList(button0, button1, button2, button3, button4, button5, button6, button7, button8);
        model.setButtons(gameButtons);

        for (Button button : model.getButtons()) { //removes focus from buttons
            button.setFocusTraversable(false);
        }
    }

    public void handleButtonClick(MouseEvent event) {
        Button clickedButton = (Button) event.getSource();
        model.setPlayerSymbol(clickedButton);
        checkWin();
    }

    public void ExitGameWhenClicked() {
        Platform.exit();
    }

    public void resetGame() {
        model.resetGameData();
        showWinner.setText("Tic-Tac-Toe");
        enableButtons();
    }

    public void ResetGameWhenClicked() {
        resetGame();
    }

    public void enableButtons() {
        for (Button button : model.getButtons()) {
            button.setDisable(false);
        }
    }

    public void checkWin() {
        for (int i = 0; i < 8; i++) {
            String line = switch (i) {
                case 0 -> button0.getText() + button1.getText() + button2.getText();
                case 1 -> button3.getText() + button4.getText() + button5.getText();
                case 2 -> button6.getText() + button7.getText() + button8.getText();
                case 3 -> button0.getText() + button3.getText() + button6.getText();
                case 4 -> button1.getText() + button4.getText() + button7.getText();
                case 5 -> button2.getText() + button5.getText() + button8.getText();
                case 6 -> button0.getText() + button4.getText() + button8.getText();
                case 7 -> button2.getText() + button4.getText() + button6.getText();
                default -> null;
            };
            if (line.equals("XXX")) {
                showWinner.setText("X wins!");
                break;
            } else if (line.equals("OOO")) {
                showWinner.setText("O wins!");
                break;
            }
        }
    }
}
//    public void disableButtonAfterClick(Button button) {
//        button.setOnMouseClicked(mouseEvent -> {
//            setPlayerSymbol(button);
//            button.setDisable(true);
//            checkWinOrDraw();
//            computerTurn();
//        });
//    }

//        public void playerTurn () {
//            //code for player turn
//        }
//
//        public void computerTurn () {
//            if (!model.isBoardFull() && !model.checkWin()) {
//                ArrayList<Button> availableButtons = new ArrayList<>();
//                for (Button button : buttons) {
//                    if (!button.isDisabled()) availableButtons.add(button);
//                }
//                if (!availableButtons.isEmpty()) {
//                    Button randomButton = availableButtons.get(random.nextInt(availableButtons.size()));
//                    setComputerSymbol(randomButton);
//                    randomButton.setDisable(true);
//                    checkWinOrDraw();
//                }
//            }
//        }
//
//        if (playerTurn % 2 == 0) {
//            button.setText("X");
//            playerTurn = 1;
//        } else {
//            button.setText("O");
//            playerTurn = 0;
//        }
//    }

//        public void setComputerSymbol (Button button){
//            if (playerTurn % 2 == 0) {
//                button.setText("X");
//                playerTurn = 1;
//            } else {
//                button.setText("O");
//            }
//            playerTurn = (playerTurn + 1) % 2;