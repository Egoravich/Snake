package snake;

import java.util.ArrayList;

public interface ISnake {
    public void addSegment();
    public void addSegment(ISnakeSegment segment);
    public void move();
    public ISnakeSegment.Direction getDirection();

    public void turnRight();
    public void turnLeft();

    public ArrayList<ISnakeSegment> getSegments();
    public ISnakeSegment getTail();
    public ISnakeSegment getHead();
}
