package Model;

import java.awt.*;
import java.awt.event.KeyEvent;

import static ui.GamePanel.GAME_WIDTH;

public class Paddle extends Rectangle {

    Color color;
    int xVelocity;
    int speed = 10;



    public Paddle(int x, int y, int width, int height) {
        super(x,y,width,height);
        color = Color.BLUE;

    }

    public void setX(int x) {
        this.x = x;
    }

    public void move(){
        x = x + xVelocity;
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public void setXVelocity(int speed) {
        xVelocity = speed;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_LEFT) {
            setXVelocity(-speed);
        }

        if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
            setXVelocity(speed);
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_LEFT) {
            setXVelocity(0);
        }

        if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
            setXVelocity(0);
        }

    }
}
