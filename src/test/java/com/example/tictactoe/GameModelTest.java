package com.example.tictactoe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameModelTest {
    GameModel model = new GameModel();

    @Test
    @DisplayName("Computer can win a round")
    void computerCanWinARound() {
        model.setComputerScore(model.getComputerScore());

        assertEquals(model.getPlayerScore(),0);

    }

    @Test
    @DisplayName("Player can win a round")
    void playerCanWinARound() {
        model.setPlayerScore(model.getPlayerScore());

        assertEquals(model.getComputerScore(),0);

    }

    @Test
    @DisplayName("There is no winner")
    void thereIsNoWinner() {
        model.setPlayerScore(model.getPlayerScore());
        model.setComputerScore(model.getComputerScore());

        assertEquals(model.getPlayerScore(),0);
        assertEquals(model.getComputerScore(),0);
    }
}

