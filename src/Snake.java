import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

class Snake {
    private ArrayList<Point> body;
    private Direction direction;

    Snake(int x, int y, Direction initialDirection) {
        body = new ArrayList<>();
        body.add(new Point(x, y));
        direction = initialDirection;
    }

    void setDirection(Direction direction) {
        this.direction = direction;
    }

    Point getHead() {
        return body.get(body.size() - 1);
    }

    ArrayList<Point> getBody() {
        return body;
    }

    void move() {
        Point head = getHead();
        switch (direction) {
            case UP:
                body.add(new Point(head.x, head.y - 1));
                break;
            case DOWN:
                body.add(new Point(head.x, head.y + 1));
                break;
            case LEFT:
                body.add(new Point(head.x - 1, head.y));
                break;
            case RIGHT:
                body.add(new Point(head.x + 1, head.y));
                break;
        }
        body.remove(0);
    }

    void grow() {
        Point tail = body.get(0);
        body.add(0, tail);
    }
}
