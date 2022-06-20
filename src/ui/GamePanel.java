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
    public static final Dimension SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    public static final int PADDLE_WIDTH = 100;
    public static final int PADDLE_HEIGHT = 10;
    public static final int PADDLE_YPosition = 650;
    public static final int BRICK_WIDTH = 50;
    public static final int BRICK_HEIGHT = 40;
    public static final int UPPER_SPACE = 50;
    public static final int BRICK_SPACE = 20;
    public static final int RADIUS = 8;

    private Paddle paddle;
    private Direction pDirect;
    private Ball ball;
    private ArrayList<Brick> bricks;
    private Thread gameThread;
    private Boolean gameStatus;
    private GameTimer gametimer;

    enum Direction {
        LEFT,
        RESTING,
        RIGHT
    }


    public GamePanel() {
        newTimer();
        newBall();
        newPaddle();
        newBricks();
        setPreferredSize(SIZE);
        setFocusable(true);
        addKeyListener(new AL());
        gameThread = new Thread(this);
        gameStatus = true;
    }

    private void newTimer() {
        gametimer = new GameTimer();
        add(gametimer);
    }


    private void newBall() {
        ball = new Ball(GAME_WIDTH/2 - RADIUS, GAME_HEIGHT - 350 , RADIUS*2, RADIUS*2);
    }

    private void newPaddle() {
        paddle = new Paddle(GAME_WIDTH/2 - PADDLE_WIDTH/2, PADDLE_YPosition,
                PADDLE_WIDTH, PADDLE_HEIGHT);

    }

    private void newBricks() {
        bricks = new ArrayList<>();
        int row = 0;
        int col = 0;
        for (int i = 1; i <= 40; i++) {
            col++;
            Brick brick = new Brick(col*BRICK_SPACE + (col - 1)*BRICK_WIDTH, UPPER_SPACE +
                    row*(BRICK_SPACE + BRICK_HEIGHT),
                    BRICK_WIDTH, BRICK_HEIGHT, row, col, i);
            if (i % 8 == 0) {
                row++;
                col = 0;
            }
            bricks.add(brick);
        }
    }

    // Paint the game
    @Override
    public void paintComponent(Graphics g) {
        Image image = createImage(getWidth(),getHeight());
        Graphics graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,null);
    }

    // Draw paddle, ball, and bricks
    private void draw(Graphics g) {
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

    // Run the game in 60 frames per second
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        gametimer.startTimer();
        while(gameStatus) {
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if(delta >= 1) {
                move();
                checkPaddleBound();
                checkCollision();
                repaint();
                delta--;
            }
        }
        gametimer.stopTimer();
    }

    // Move the paddle and ball
    private void move() {
        double prevPosOfPaddle = paddle.getX();
        paddle.move();
        double currPosOfPaddle = paddle.getX();
        setPaddleDirection(prevPosOfPaddle, currPosOfPaddle);
        ball.move();
    }


    // Make sure the paddle is within the bound of GAME_WIDTH
    private void checkPaddleBound() {
        double x = paddle.getX();
        if (x < 0) {
            paddle.setX(0);
        }
        if (x + PADDLE_WIDTH > GAME_WIDTH) {
            paddle.setX(GAME_WIDTH - PADDLE_WIDTH);
        }
    }

    // Check if the ball collied with objects
    private void checkCollision() {
        checkWall();
        checkPaddle();
        checkBrick();
    }


    private void checkWall(){
        // If the ball touches the bottom of the screen, the player loses the game
        if (ball.getY() + ball.getHeight() >= GAME_HEIGHT) {
            gameStatus = false;
        }
        // Hit the side walls
        if (ball.getX() < 0 || ball.getX() + RADIUS*2 > GAME_WIDTH) {
            ball.setXVelocity(-ball.getXVelocity());
        }
        // Hit the celling
        if (ball.getY() < 0) {
            ball.setYVelocity(-ball.getYVelocity());
        }
    }

    // If the moving direction of the paddle is the same as the ball, the xVelocity of the ball increases,
    // if opposite, then it decreases.
    private void checkPaddle(){
        if (ball.intersects(paddle)) {
            switch (pDirect) {
                case LEFT:
                    ball.setXVelocity(ball.getXVelocity() - 1);
                    break;
                case RIGHT:
                    ball.setXVelocity(ball.getXVelocity() + 1);
                    break;
            }
            ball.setYVelocity(-ball.getYVelocity());
        }
    }

    private void checkBrick() {
        int brickId = 0;
        // Records the id of the brick that has been hit.
        for (Brick b: bricks) {
            if (ball.intersects(b)) {
                ball.setXVelocity(-ball.getXVelocity());
                ball.setYVelocity(-ball.getYVelocity());
                brickId = b.getId();
                break;
            }
        }
        // If a brick has been hit, delete it.
        if (brickId != 0) {
            for(int i = bricks.size() - 1; i >= 0; --i) {
                if(bricks.get(i).getId() == brickId) {
                    bricks.remove(i);
                    break;
                }
            }
            // If there is no brick left, the player wins the game.
            if (bricks.size() == 0) {
                gameStatus = false;
                gametimer.stopTimer();
            }
        }
    }

    // Records the moving direction of the paddle
    private void setPaddleDirection(double prev, double curr){
        if (prev == curr) {
            pDirect = Direction.RESTING;
        } else if (prev > curr) {
            pDirect = Direction.LEFT;
        } else  {
            pDirect = Direction.RIGHT;
        }
    }

    // Key listener
    public class AL extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            //press space to start
            if (e.getKeyCode()==KeyEvent.VK_SPACE) {
                gameThread.start();
            }
            paddle.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }
    }
}
