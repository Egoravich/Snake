package snake;

import java.util.ArrayList;

public interface ISnake {
    enum SnakeDirection {
        UP,
        RIGHT,
        DOWN,
        LEFT,
    }

    public void addSegment();
    public void addSegment(ISnakeSegment segment);
    public void move();
    public SnakeDirection getDirection();

    public void turnRight();
    public void turnLeft();

    public ArrayList<ISnakeSegment> getSegments();
    public ISnakeSegment getTail();
}
