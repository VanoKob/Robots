package org.robotgame.gui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {
    private PacmanPlayer pacmanPlayer;
    private GhostPlayer ghostPlayer;
    private GameLogic gameLogic;

    @BeforeEach
    void setUp() {
        pacmanPlayer = new PacmanPlayer(4);
        ghostPlayer = new GhostPlayer(4);
        gameLogic = new GameLogic(pacmanPlayer, ghostPlayer);
    }

    @Test
    void restart() {
        pacmanPlayer.x = 100;
        pacmanPlayer.y = 100;
        pacmanPlayer.req_dx = 1;
        pacmanPlayer.req_dy = 1;
        ghostPlayer.x = 50;
        ghostPlayer.y = 50;
        ghostPlayer.req_dx = 1;
        ghostPlayer.req_dy = 1;

        gameLogic.restart();

        assertEquals(200, pacmanPlayer.x);
        assertEquals(200, pacmanPlayer.y);
        assertEquals(0, pacmanPlayer.req_dx);
        assertEquals(0, pacmanPlayer.req_dy);
        assertEquals(0, ghostPlayer.x);
        assertEquals(0, ghostPlayer.y);
        assertEquals(0, ghostPlayer.req_dx);
        assertEquals(0, ghostPlayer.req_dy);
    }

    @Test
    void check_Death_PacmanAndGhostCollide() {
        pacmanPlayer.x = 100;
        pacmanPlayer.y = 100;
        ghostPlayer.x = 110;
        ghostPlayer.y = 110;

        gameLogic.check_Death();

        assertFalse(gameLogic.inGame);
    }

    @Test
    void checkDeath_PacmanAndGhostDoNotCollide() {
        gameLogic.restart();
        pacmanPlayer.x = 150;
        pacmanPlayer.y = 150;
        ghostPlayer.x = 100;
        ghostPlayer.y = 100;

        gameLogic.check_Death();

        assertTrue(gameLogic.inGame);
    }
}