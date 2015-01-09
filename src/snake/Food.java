package snake;

public class Food implements IFood {
    private int x = DEFAULT_X;
    private int y = DEFAULT_Y;
    private Type type = DEFAULT_TYPE;

    public Food() {}

    public Food(int x, int y, Type type) {
        this.x = x;
        this.y = y;
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
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
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
