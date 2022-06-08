package Model;

import java.awt.*;


public class Brick extends Rectangle {

    Color color;
    int row;
    int col;


    public Brick(int x, int y, int width, int height, int row, int col) {
        super(x,y,width,height);
        this.row = row;
        this.col = col;
        color = Color.white;
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

}
