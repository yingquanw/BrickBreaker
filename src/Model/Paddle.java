package Model;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {

    Color color;
    int xVelocity;
    int speed = 10;



    public Paddle(int x, int y, int width, int height) {
        super(x,y,width,height);

    }

    public void move(){

    }

    public void draw(){

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
