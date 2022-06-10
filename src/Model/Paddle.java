package Model;

import java.awt.*;
import java.awt.event.KeyEvent;


public class Paddle extends Rectangle {

    private Color color;
    private int xVelocity;
    private int speed = 5;



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

    // Press LEFT key to go left and RIGHT key to go right
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
