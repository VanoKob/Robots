package org.robotgame.gui;

import javax.swing.*;

public abstract class AbstractPlayer extends JPanel{
    protected int x, y, dx, dy, req_dx, req_dy;
    private final int PLAYER_SPEED;
    public AbstractPlayer (int speed) {
        PLAYER_SPEED = speed;
    }
    private static int applyLimits(int value, int min, int max)
    {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }

    protected void movePlayer(int width, int height) {
        dx = req_dx;
        dy = req_dy;
        int newX = x + PLAYER_SPEED * dx;
        int newY = y + PLAYER_SPEED * dy;
        newX = applyLimits(newX, 0, width - 24);
        newY = applyLimits(newY, 0, height - 24);
        x = newX;
        y = newY;
    }
}
