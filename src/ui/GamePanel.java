package ui;

import Model.Ball;
import Model.Brick;
import Model.Paddle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    public static final int GAME_WIDTH = 580;
    public static final int GAME_HEIGHT = 700;
    static final Dimension SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int PADDLE_WIDTH = 50;
    static final int PADDLE_HEIGHT = 20;
    public static final int BRICK_WIDTH = 50;
    public static final int BRICK_HEIGHT = 40;
    public static final int UPPER_SPACE = 50;
    static final int BRICK_SPACE = 20;
    static final int RADIUS = 5;
    Graphics graphics;
    Paddle paddle;
    Ball ball;
    ArrayList<Brick> bricks;
    Thread gameThread;
    Image image;


    public GamePanel() {
        newBall();
        newPaddle();
        newBricks();
        setPreferredSize(SIZE);
        setFocusable(true);
        addKeyListener(new AL());
        gameThread = new Thread(this);
        gameThread.start();

    }

    public void paint(Graphics g) {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }

    public void draw(Graphics g) {
        paddle.draw(g);
        ball.draw(g);
        drawBricks(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawBricks(Graphics g) {
        for(Brick b: bricks) {
            b.draw(g);
        }
    }

    public void newBall() {
        ball = new Ball(GAME_WIDTH/2 - RADIUS, 500 , RADIUS*2, RADIUS*2);

    }

    public void newPaddle() {
        paddle = new Paddle(GAME_WIDTH/2 - PADDLE_WIDTH/2, 650 - PADDLE_HEIGHT/2,
                PADDLE_WIDTH, PADDLE_HEIGHT);

    }

    public void newBricks() {
        bricks = new ArrayList<>();
        int row = 0;
        int col = 0;
        for (int i = 1; i <= 48; i++) {
            col++;
            Brick brick = new Brick(col*BRICK_SPACE + (col - 1)*BRICK_WIDTH, UPPER_SPACE +
                    row*(BRICK_SPACE + BRICK_HEIGHT),
                    BRICK_WIDTH, BRICK_HEIGHT, row, col);
            if (i % 8 == 0) {
                row++;
                col = 0;
            }
            bricks.add(brick);
        }

    }

    public void checkCollision() {
        checkPaddleCollision();
        checkBallCollision();

    }

    private void checkBallCollision() {

    }

    private void checkPaddleCollision() {
        double x = paddle.getX();
        if (x < 0) {
            paddle.setX(0);
        }
        if (x + PADDLE_WIDTH > GAME_WIDTH) {
            paddle.setX(GAME_WIDTH - PADDLE_WIDTH);
        }

    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public void move() {
        ball.move();
        paddle.move();
    }


    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);

        }

        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);

        }
    }
}
