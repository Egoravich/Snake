package snake;

public interface IFood {
    static enum Type {
        APPLE,
        CHERRY,
    }

    final int DEFAULT_X = 0;
    final int DEFAULT_Y = 0;
    final Type DEFAULT_TYPE = Type.APPLE;

    public int getX();
    public int getY();
    public void setX(int x);
    public void setY(int y);
    public Type getType();
    public void setType(Type type);
}
