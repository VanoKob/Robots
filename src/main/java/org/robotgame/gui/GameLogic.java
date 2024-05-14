package org.robotgame.gui;

public class GameLogic {
    protected boolean inGame = false;
    protected PacmanPlayer pacmanPlayer;
    protected GhostPlayer ghostPlayer;
    public GameLogic(PacmanPlayer pacmanPlayer, GhostPlayer ghostPlayer) {
        this.pacmanPlayer = pacmanPlayer;
        this.ghostPlayer = ghostPlayer;
    }

    protected void restart() {
        pacmanPlayer.x = 200;
        pacmanPlayer.y = 200;
        ghostPlayer.x = 0;
        ghostPlayer.y = 0;
        pacmanPlayer.req_dx = 0;
        pacmanPlayer.req_dy = 0;
        ghostPlayer.req_dx = 0;
        ghostPlayer.req_dy = 0;
    }

    protected void check_Death() {
        if ((pacmanPlayer.x + 10 > ghostPlayer.x - 10) &&
                (pacmanPlayer.x - 10 < ghostPlayer.x + 10) &&
                (pacmanPlayer.y + 10 > ghostPlayer.y - 10) &&
                (pacmanPlayer.y - 10 < ghostPlayer.y + 10)) {
            inGame = false;
        }
    }
}
