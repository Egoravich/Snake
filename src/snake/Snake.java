package snake;

import java.util.ArrayList;

public class Snake implements ISnake {
    private ArrayList<ISnakeSegment> snake = new ArrayList<>();
    private SnakeDirection direction;
    private final int HEAD_POSITION = 0;

    public Snake(SnakeDirection direction, ISnakeSegment head, ISnakeSegment body, ISnakeSegment tail) {
        this.direction = direction;
        this.snake.add(head);
        this.snake.add(body);
        this.snake.add(tail);
    }

    public Snake(ISnakeSegment head, ISnakeSegment body, ISnakeSegment tail) {
        this(SnakeDirection.UP, head, body, tail);
    }

    @Override
    public void addSegment(ISnakeSegment segment) {
        this.snake.add(segment);
    }

    @Override
    public void addSegment() {
        ISnakeSegment tail = snake.get(snake.size() - 1);
        this.snake.add(new SnakeSegment(tail.getX(), tail.getY()));
    }

    @Override
    public void move() {
        ISnakeSegment thisElem;
        ISnakeSegment nextElem;
        ISnakeSegment head = snake.get(HEAD_POSITION);

        // Проход начиная с хвоста по всем, кроме головного элемента
        for (int i = snake.size() - 1; i > HEAD_POSITION; i--) {
            thisElem = snake.get(i);
            nextElem = snake.get(i - 1);

            thisElem.setX(nextElem.getX());
            thisElem.setY(nextElem.getY());
        }

        // Логика для головы
        switch (getDirection()) {
            case UP:
                head.setY(head.getY() - 1);
                break;
            case DOWN:
                head.setY(head.getY() + 1);
                break;
            case RIGHT:
                head.setX(head.getX() + 1);
                break;
            case LEFT:
                head.setX(head.getX() - 1);
                break;
        }
    }

    @Override
    public void turnRight() {
        turnTo(true);
    }

    @Override
    public void turnLeft() {
        turnTo(false);
    }

    private void turnTo(boolean to_right) {
        ArrayList<SnakeDirection> directions = new ArrayList<>();
        directions.add(SnakeDirection.UP); directions.add(SnakeDirection.RIGHT);
        directions.add(SnakeDirection.DOWN); directions.add(SnakeDirection.LEFT);
        int directionIndex = directions.indexOf(getDirection());

        if (to_right) {
            directionIndex++;
        }
        else {
            directionIndex--;
            directionIndex += directions.size();
        }

        directionIndex %= directions.size();

        this.direction = directions.get(directionIndex);
    }

    @Override
    public SnakeDirection getDirection() {
        return direction;
    }

    @Override
    public ArrayList<ISnakeSegment> getSegments() {
        return snake;
    }

    @Override
    public ISnakeSegment getTail() {
        return getSegments().get(getSegments().size() - 1);
    }
}
