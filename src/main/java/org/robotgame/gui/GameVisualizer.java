package org.robotgame.gui;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class GameVisualizer extends JPanel implements ActionListener
{
    private Image up, down, left, right, pacman, ghost;
    private final Timer timer;
    private PacmanPlayer pacmanPlayer;
    private GhostPlayer ghostPlayer;
    private GameLogic gameLogic;

    public GameVisualizer() {
        loadImages();
        timer = new Timer(20, this);
        timer.start();
        pacmanPlayer = new PacmanPlayer(4);
        ghostPlayer = new GhostPlayer(4);
        gameLogic = new GameLogic(pacmanPlayer, ghostPlayer);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed (KeyEvent e){

                int key = e.getKeyCode();

                if (gameLogic.inGame) {
                    if (key == KeyEvent.VK_LEFT) {
                        pacmanPlayer.req_dx = -1;
                        pacmanPlayer.req_dy = 0;
                    } else if (key == KeyEvent.VK_RIGHT) {
                        pacmanPlayer.req_dx = 1;
                        pacmanPlayer.req_dy = 0;
                    } else if (key == KeyEvent.VK_UP) {
                        pacmanPlayer.req_dx = 0;
                        pacmanPlayer.req_dy = -1;
                    } else if (key == KeyEvent.VK_DOWN) {
                        pacmanPlayer.req_dx = 0;
                        pacmanPlayer.req_dy = 1;
                    } else if (key == KeyEvent.VK_A) {
                        ghostPlayer.req_dx = -1;
                        ghostPlayer.req_dy = 0;
                    } else if (key == KeyEvent.VK_D) {
                        ghostPlayer.req_dx = 1;
                        ghostPlayer.req_dy = 0;
                    } else if (key == KeyEvent.VK_W) {
                        ghostPlayer.req_dx = 0;
                        ghostPlayer.req_dy = -1;
                    } else if (key == KeyEvent.VK_S) {
                        ghostPlayer.req_dx = 0;
                        ghostPlayer.req_dy = 1;
                    } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                        gameLogic.inGame = false;
                    }
                } else {
                    if (key == KeyEvent.VK_SPACE) {
                        gameLogic.inGame = true;
                        gameLogic.restart();
                    }
                }
            }
        });
        setDoubleBuffered(true);
        setFocusable(true);
    }

    private void loadImages() {
        pacman = getResourceImage("images/pacman.png");
        up = getResourceImage("images/up.gif");
        down = getResourceImage("images/down.gif");
        left = getResourceImage("images/left.gif");
        right = getResourceImage("images/right.gif");
        ghost = getResourceImage("images/ghost.gif");
    }

    private Image getResourceImage(String filename) {
        URL imageURL = getClass().getClassLoader().getResource(filename);
        return new ImageIcon(imageURL).getImage();
    }

    private void playGame(Graphics2D g2d) {
            pacmanPlayer.movePlayer(getWidth(), getHeight());
            drawPacman(g2d);
            ghostPlayer.movePlayer(getWidth(), getHeight());
            drawGhost(g2d);
            gameLogic.check_Death();
    }

    private void drawGhost(Graphics2D g2d) {
        g2d.drawImage(ghost, ghostPlayer.x + 1, ghostPlayer.y + 1, this);
    }

    private void drawPacman(Graphics2D g2d) {
        if (pacmanPlayer.req_dx == -1) {
            g2d.drawImage(left, pacmanPlayer.x + 1, pacmanPlayer.y + 1, this);
        } else if (pacmanPlayer.req_dx == 1) {
            g2d.drawImage(right, pacmanPlayer.x + 1, pacmanPlayer.y + 1, this);
        } else if (pacmanPlayer.req_dy == -1) {
            g2d.drawImage(up, pacmanPlayer.x + 1, pacmanPlayer.y + 1, this);
        } else if (pacmanPlayer.req_dy == 1){
            g2d.drawImage(down, pacmanPlayer.x + 1, pacmanPlayer.y + 1, this);
        } else {
            g2d.drawImage(pacman, pacmanPlayer.x + 1, pacmanPlayer.y + 1, this);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        if (gameLogic.inGame) {
            playGame(g2d);
        }
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
