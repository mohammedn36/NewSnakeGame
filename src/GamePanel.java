import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 20;
    static final int DELAY = 100;

    Snake snake1;
    Snake snake2;

    Food food;
    boolean isPlayerOneTurn;
    Timer timer;
    private boolean isGameOver;

    GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        snake2.setDirection(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        snake2.setDirection(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_UP:
                        snake2.setDirection(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        snake2.setDirection(Direction.DOWN);
                        break;
                    case KeyEvent.VK_A:
                        snake1.setDirection(Direction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                        snake1.setDirection(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_W:
                        snake1.setDirection(Direction.UP);
                        break;
                    case KeyEvent.VK_S:
                        snake1.setDirection(Direction.DOWN);
                        break;
                }
            }
        });

        snake1 = new Snake(1, 0, Direction.RIGHT);
        snake2 = new Snake(SCREEN_WIDTH / UNIT_SIZE - 2, SCREEN_HEIGHT / UNIT_SIZE - 1, Direction.LEFT);
        food = new Food();

        isPlayerOneTurn = true;
        isGameOver = false;

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private boolean isCollisionWithScreenEdge(Snake snake) {
        Point head = snake.getHead();
        return head.x < 0 || head.x >= SCREEN_WIDTH / UNIT_SIZE || head.y < 0 || head.y >= SCREEN_HEIGHT / UNIT_SIZE;
    }

    private boolean isCollisionWithOtherSnake(Snake snake1, Snake snake2) {
        for (Point point : snake2.getBody()) {
            if (snake1.getHead().equals(point)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGameOver) {
            return;
        }

        if (isPlayerOneTurn) {
            snake1.move();
            if (snake1.getHead().equals(food.getPoint())) {
                snake1.grow();
                food = new Food();
            } else if (isCollisionWithScreenEdge(snake1) || isCollisionWithOtherSnake(snake1, snake2)) {
                isGameOver = true;
            }
        } else {
            snake2.move();
            if (snake2.getHead().equals(food.getPoint())) {
                snake2.grow();
                food = new Food();
            } else if (isCollisionWithScreenEdge(snake2) || isCollisionWithOtherSnake(snake2, snake1)) {
                isGameOver = true;
            }
        }

        isPlayerOneTurn = !isPlayerOneTurn;

        repaint();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (isGameOver) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Serif", Font.BOLD, 40));
            g.drawString("Game Over", SCREEN_WIDTH / 2 - 100, SCREEN_HEIGHT / 2);
            g.setFont(new Font("Serif", Font.BOLD, 30));
            g.setColor(Color.GREEN);
            g.drawString("Snake 1 size: " + snake1.getBody().size(), SCREEN_WIDTH / 2 - 93, SCREEN_HEIGHT / 2 + 50);
            g.setColor(Color.BLUE);
            g.drawString("Snake 2 size: " + snake2.getBody().size(), SCREEN_WIDTH / 2 - 93, SCREEN_HEIGHT / 2 + 90);
            return;
        }

        for (Point p : snake1.getBody()) {
            g.setColor(Color.GREEN);
            g.fillRect(p.x * UNIT_SIZE, p.y * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
        }

        for (Point p : snake2.getBody()) {
            g.setColor(Color.BLUE);
            g.fillRect(p.x * UNIT_SIZE, p.y * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
        }

        g.setColor(Color.RED);
        g.fillRect(food.getPoint().x * UNIT_SIZE, food.getPoint().y * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
    }

}
