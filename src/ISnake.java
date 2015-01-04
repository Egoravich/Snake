import java.util.ArrayList;

public interface ISnake {
    enum SnakeDirection {
        UP,
        RIGHT,
        DOWN,
        LEFT,
    }

    public void addSegment();
    public void move();
    public SnakeDirection getDirection();
    public void setDirection(SnakeDirection direction);

    public ArrayList<ISnakeSegment> getSegments();
}
