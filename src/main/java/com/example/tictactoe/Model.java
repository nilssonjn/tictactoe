package com.example.tictactoe;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Model {


    private SimpleIntegerProperty playerScore = new SimpleIntegerProperty();
    private SimpleIntegerProperty computerScore = new SimpleIntegerProperty();
    private int playerTurn = 0;
    private ObservableList<Button> buttons = FXCollections.observableArrayList();


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
        clickedButton.setText("X");
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

    public void checkWin(Button button0, Button button1, Button button2,
                         Button button3, Button button4, Button button5,
                         Button button6, Button button7, Button button8,
                         Text showWinner) {
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
                disableButtonsAfterWin();
                return;
            } else if (line.equals("OOO")) {
                showWinner.setText("O wins!");
                disableButtonsAfterWin();
                return;
            }
        }
    }

    public void disableButtonsAfterWin() {
        for (Button button : this.buttons) {
            button.setDisable(true);
        }
    }

    public void randomComputerMove() {
        Random random = new Random();
        List<Button> emptyButtons = buttons.stream()
                .filter(button -> button.getText().isEmpty())
                .toList();
        if (!emptyButtons.isEmpty()){
            int randomIndex = random.nextInt(emptyButtons.size());
            emptyButtons.get(randomIndex).setText("O");
            emptyButtons.get(randomIndex).setDisable(true);
        }
    }
}

