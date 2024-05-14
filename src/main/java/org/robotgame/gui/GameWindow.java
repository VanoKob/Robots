package org.robotgame.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class GameWindow extends AbstractWindow
{
    private final GameVisualizer m_visualizer;
    public GameWindow() 
    {
        super("Игровое поле", false, true, true, true);
        m_visualizer = new GameVisualizer();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }

    @Override
    protected void closeWindow() {
    }
}
