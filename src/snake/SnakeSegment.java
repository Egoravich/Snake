package snake;

public class SnakeSegment implements ISnakeSegment {
    private Type type;
    private Direction directionFrom;
    private Direction directionTo;
    private int x, y = DEFAULT_XY;

    public SnakeSegment(Type type, Direction directionFrom, Direction directionTo, int x, int y) {
        this.type = type;
        this.directionFrom = directionFrom;
        this.directionTo = directionTo;
        this.x = x;
        this.y = y;
    }

    public SnakeSegment(Type type, Direction direction, int x, int y) {
        this(type, direction, direction, x, y);
    }

    public SnakeSegment(Direction direction, int x, int y) {
        this(DEFAULT_TYPE, direction, direction, x, y);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public Direction getDirectionFrom() {
        return directionFrom;
    }

    @Override
    public void setDirectionFrom(Direction directionFrom) {
        this.directionFrom = directionFrom;
    }

    @Override
    public Direction getDirectionTo() {
        return directionTo;
    }

    @Override
    public void setDirectionTo(Direction directionTo) {
        this.directionTo = directionTo;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

}
