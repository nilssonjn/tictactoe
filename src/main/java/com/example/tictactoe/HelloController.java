package com.example.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {

    private Model model = new Model();

    @FXML
    private Button button0;

    public void initialize() {
        welcomeText.textProperty().bind(model.messageProperty()); //binding the text visible in label
    }

}