package Model;

import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle{

    private Color color;
    private int xVelocity;
    private int yVelocity;
    private int initialXVelocity;
    private int initialYVelocity;

    public Ball(int x, int y, int width, int height) {
        super(x,y,width,height);
        color = Color.yellow;
        // Use the formula Math.floor(Math.random()*(max-min+1)+min) to generate values with the min and the
        // max value inclusive.
        initialXVelocity = (int)Math.floor(Math.random()*(2-(-2)+1)-2);
        initialYVelocity = 3;
        xVelocity = initialXVelocity;
        yVelocity = initialYVelocity;

    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, width ,height);
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public int getYVelocity() {
        return yVelocity;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }
}
