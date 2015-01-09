package snake;

import java.util.ArrayList;

public class Snake implements ISnake {
    private ArrayList<ISnakeSegment> snake = new ArrayList<>();
    /**
     * Номер головного элемента
     */
    private final int HEAD_POSITION = 0;

    public Snake(ISnakeSegment head, ISnakeSegment body, ISnakeSegment tail) {
        head.setType(ISnakeSegment.Type.HEAD);
        body.setType(ISnakeSegment.Type.BODY);
        tail.setType(ISnakeSegment.Type.TAIL);

        this.snake.add(head);
        this.snake.add(body);
        this.snake.add(tail);
    }

    @Override
    public void addSegment(ISnakeSegment segment) {
        snake.add(segment);
    }

    @Override
    public void addSegment() {
        ISnakeSegment tail = snake.get(snake.size() - 1);
        snake.add(new SnakeSegment(
                ISnakeSegment.Type.TAIL, tail.getDirectionFrom(), tail.getDirectionTo(), tail.getX(), tail.getY()
        ));
    }

    @Override
    public ISnakeSegment getHead() {
        return snake.get(HEAD_POSITION);
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

            // Проверка и проставление напрвления сегмента (нужно для проставления правильного спрайта)
            thisElem.setDirectionFrom(nextElem.getDirectionFrom());
            thisElem.setDirectionTo(nextElem.getDirectionTo());

            thisElem.setType(ISnakeSegment.Type.BODY);
        }

        getTail().setType(ISnakeSegment.Type.TAIL);
        getHead().setType(ISnakeSegment.Type.HEAD);

        // Логика для шейного сегмента
        // TODO: Убрать хардкод
        snake.get(1).setDirectionFrom(snake.get(2).getDirectionTo());
        snake.get(1).setDirectionTo(head.getDirectionTo());

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
        ArrayList<ISnakeSegment.Direction> directions = new ArrayList<>();
        directions.add(ISnakeSegment.Direction.UP);
        directions.add(ISnakeSegment.Direction.RIGHT);
        directions.add(ISnakeSegment.Direction.DOWN);
        directions.add(ISnakeSegment.Direction.LEFT);
        int directionIndex = directions.indexOf(getDirection());

        if (to_right) {
            directionIndex++;
        } else {
            directionIndex--;
            directionIndex += directions.size();
        }

        directionIndex %= directions.size();

        getHead().setDirectionFrom(getHead().getDirectionTo());
        getHead().setDirectionTo(directions.get(directionIndex));
    }

    @Override
    public ISnakeSegment.Direction getDirection() {
        return getHead().getDirectionTo();
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
