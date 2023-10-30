package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    private Model model = new Model();

    public void initialize() {
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
    private void restartGame(ActionEvent event){
        //code for restarting game
    }
    public void resetButton(Button button){
        //code for resetting button, checking that button cant be pressed again
    }
    public void checkWin(){
        //code for checking if someone has won
    }
    public void buttonsCantBePressed(){
        //code for making buttons unpressable
    }
    public void playerTurn(){
        //code for player turn
    }
    public void computerTurn(){
        //code for computer turn
    }
    public void setPlayerSymbol(){
        //code for setting player symbol
    }
}