package snake;

public class Ground implements IGround {
    private int x = DEFAULT_X;
    private int y = DEFAULT_Y;
    private Type type = DEFAULT_TYPE;

    public Ground(int x, int y, Type type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public Ground() {}

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }
}
