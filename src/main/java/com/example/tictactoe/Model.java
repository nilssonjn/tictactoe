package com.example.tictactoe;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model {
    private StringProperty message = new SimpleStringProperty();

    public String getMessage() {
        return message.get();
    }

    public StringProperty messageProperty() {
        return message;
    }

    public void setMessage(String message) {
        this.message.set(message);
    }
    public boolean isBoardFull() {
        //code for checking if board is full
        return false;
    }

    public boolean checkWin() {
        return false;
    }
}
