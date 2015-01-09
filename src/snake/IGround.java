package snake;

public interface IGround {
    static enum Type {
        GROUND,
        GRASS_FEW,
        GRASS_MEDIUM,
        GRASS_HIGH,
    }

    final int DEFAULT_X = 0;
    final int DEFAULT_Y = 0;
    final Type DEFAULT_TYPE = Type.GROUND;

    public int getX();
    public int getY();
    public void setX(int x);
    public void setY(int y);
    public Type getType();
    public void setType(Type type);
}
