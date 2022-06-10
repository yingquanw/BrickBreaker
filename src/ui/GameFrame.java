package ui;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private GamePanel panel;

    public GameFrame() {
        panel = new GamePanel();
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        setVisible(true);
        setResizable(false);
        setTitle("Brick Breaker");
        //Place the game at the middle of the screen
        pack();
        setLocationRelativeTo(null);
    }

}
