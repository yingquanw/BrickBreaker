package Model;

import java.awt.*;


public class Brick extends Rectangle {

    private Color color;
    private int row;
    private int col;
    private int id;


    public Brick(int x, int y, int width, int height, int row, int col, int id) {
        super(x,y,width,height);
        this.row = row;
        this.col = col;
        this.id = id;
        this.color = Color.white;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public int getId() {
        return id;
    }

}
