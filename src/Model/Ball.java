package Model;

import java.awt.*;

public class Ball extends Rectangle{

    public Color color;
    public int xVelocity;
    public int yVelocity;

    public Ball(int x, int y, int width, int height) {
        super(x,y,width,height);
        color = Color.yellow;
    }

    public void move(){
        x += xVelocity;
        y += yVelocity;

    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval(x, y, width ,height);
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }
}
