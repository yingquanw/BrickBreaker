package ui;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    GamePanel panel;

    public GameFrame() {
        panel = new GamePanel();
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Brick Breaker");
    }

}
