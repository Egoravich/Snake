package snake;

public class SnakeSegment implements ISnakeSegment {
    private SegmentType type;
    private int x, y = 0;

    public SnakeSegment(SegmentType type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public SnakeSegment(int x, int y) {
        this(SegmentType.BODY, x, y);
    }

    @Override
    public SegmentType getType() {
        return type;
    }

    @Override
    public void setType(SegmentType type) {
        this.type = type;
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
