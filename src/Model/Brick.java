package Model;

import java.awt.*;

public class Brick extends Rectangle {

    public Color color;
    public int id;

    public Brick(int x, int y, int width, int height, int id) {
        super(x,y,width,height);
        this.id = id;

    }
}
