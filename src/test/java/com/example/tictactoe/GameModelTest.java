package com.example.tictactoe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameModelTest {
    GameModel model = new GameModel();

    @Test
    @DisplayName("The player can win a round")
    void thePlayerCanWinARound() {
        model.checkAndUpdateGameStatus(GameModel.GameStatus.WINNER);
        model.setCurrentPlayer(1);
        model.announceWinner(model.getCurrentPlayer());

        assertEquals("Player wins!", model.getAnnouncingWinner());
    }

    @Test
    @DisplayName("The computer can win a round")
    void theComputerCanWinARound() {
        model.checkAndUpdateGameStatus(GameModel.GameStatus.WINNER);
        model.setCurrentPlayer(0);
        model.announceWinner(model.getCurrentPlayer());

        assertEquals("Computer wins!", model.getAnnouncingWinner());
    }

    @Test
    @DisplayName("There is no winner resulting in a draw")
    void thereIsNoWinnerResultingInADraw() {
        model.checkAndUpdateGameStatus(GameModel.GameStatus.NO_WINNER);
        model.performActionBasedOnGameState(model.getStateOfTheGame());

        assertNotEquals("Player wins!", model.getAnnouncingWinner());
        assertNotEquals("Computer wins!", model.getAnnouncingWinner());
        assertEquals("Draw!", model.getAnnouncingWinner());
    }

    @Test
    @DisplayName("Check if button is available with no player symbol")
    void CheckIfButtonIsAvailableWithNoPlayerSymbol() {
        model.initializeLists(9);
        int index = model.randomComputerMove();

        assertTrue(index >= 0 && index < model.getButtonSymbolsList().size(),
                "The index is within the range of the list");

        String buttonValue = model.getButtonSymbolsList().get(index).getValue();
        assertTrue(buttonValue == null || buttonValue.isEmpty(),
                "The button is available");
    }

    @Test
    @DisplayName("Check that round is over when game is over ")
    void checkThatRoundIsOverWhenGameIsOver() {
        model.performActionBasedOnGameState(GameModel.GameStatus.NO_WINNER);

        assertTrue(model.isGameOver());
    }

    @Test
    @DisplayName("Check that round is over when there is a winner")
    void checkThatRoundIsOverWhenThereIsAWinner() {
        model.performActionBasedOnGameState(GameModel.GameStatus.WINNER);

        assertTrue(model.isGameOver());
    }

    @Test
    @DisplayName("The round is not over when the game is still playing")
    void theRoundIsNotOverWhenTheGameIsStillPlaying() {
        model.performActionBasedOnGameState(GameModel.GameStatus.PLAYING);

        assertFalse(model.isGameOver());

    }
}

