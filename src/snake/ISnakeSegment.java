package snake;

public interface ISnakeSegment {
    static enum Type {
        HEAD, BODY, TAIL,
    }
    static enum Direction {
        UP, RIGHT, DOWN, LEFT,
    }

    static final Type DEFAULT_TYPE = Type.BODY;
    static final int DEFAULT_XY = 0;

    public Type getType();
    public void setType(Type type);

    public Direction getDirectionFrom();
    public void setDirectionFrom(Direction directionFrom);

    public Direction getDirectionTo();
    public void setDirectionTo(Direction directionTo);

    public int getX();
    public int getY();
    public void setX(int x);
    public void setY(int y);
}
