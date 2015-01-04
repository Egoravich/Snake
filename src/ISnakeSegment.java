
public interface ISnakeSegment {
    enum SegmentType {
        HEAD,
        BODY,
        TAIL,
    }

    public SegmentType getType();

    public int getX();
    public int getY();
    public void setX(int x);
    public void setY(int y);
}
