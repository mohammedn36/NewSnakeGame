import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;


class Food {
    private Point point;

    Food() {
        Random random = new Random();
        int x = random.nextInt(GamePanel.SCREEN_WIDTH / GamePanel.UNIT_SIZE);
        int y = random.nextInt(GamePanel.SCREEN_HEIGHT / GamePanel.UNIT_SIZE);
        point = new Point(x, y);
    }

    Point getPoint() {
        return point;
    }
}

enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}


