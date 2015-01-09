package test;

import org.junit.Test;
import snake.ISnake;
import snake.ISnakeSegment;
import snake.Snake;
import snake.SnakeSegment;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SnakeTest {

    @Test
    public void testGetSegments() throws Exception {
        ISnakeSegment segments[] = {
                new SnakeSegment(ISnakeSegment.Direction.UP, 5, 5),
                new SnakeSegment(ISnakeSegment.Direction.UP, 5, 6),
                new SnakeSegment(ISnakeSegment.Direction.UP, 5, 7)
        };

        ISnake snake;
        snake = new Snake(segments[0], segments[1], segments[2]);

        assertArrayEquals(segments, snake.getSegments().toArray());
    }

    @Test
    public void testGetTail() throws Exception {
        ISnakeSegment tail = new SnakeSegment(ISnakeSegment.Direction.UP, 5, 7);
        ISnake snake = new Snake(
                new SnakeSegment(ISnakeSegment.Direction.UP, 5, 5),
                new SnakeSegment(ISnakeSegment.Direction.UP, 5, 6),
                tail
        );

        assertEquals(tail, snake.getTail());
    }

    @Test
    public void testAddSegment() throws Exception {
        ISnake snake = new Snake(
                new SnakeSegment(ISnakeSegment.Direction.UP, 5, 5),
                new SnakeSegment(ISnakeSegment.Direction.UP, 5, 6),
                new SnakeSegment(ISnakeSegment.Direction.UP, 5, 7)
        );

        ISnakeSegment tail = snake.getTail();
        int snakeLenght = snake.getSegments().size();

        snake.addSegment();
        ISnakeSegment newTail = snake.getTail();
        int newSnakeLenght = snake.getSegments().size();

        assertEquals(newTail.getX(), tail.getX());
        assertEquals(newTail.getY(), tail.getY());
        assertEquals(snakeLenght + 1, newSnakeLenght);
    }

    @Test
    public void testGetDirection() throws Exception {
        ISnake snake;
        ISnakeSegment.Direction result;

        result = ISnakeSegment.Direction.LEFT;
        snake = new Snake(
                new SnakeSegment(result, 5, 5),
                new SnakeSegment(ISnakeSegment.Direction.UP, 5, 6),
                new SnakeSegment(ISnakeSegment.Direction.RIGHT, 5, 7)
        );
        assertEquals(snake.getDirection(), result);
    }

    @Test
    public void testTurnRight() throws Exception {
        ISnake snake = new Snake(
                new SnakeSegment(ISnakeSegment.Direction.UP, 5, 5),
                new SnakeSegment(ISnakeSegment.Direction.UP, 5, 6),
                new SnakeSegment(ISnakeSegment.Direction.UP, 5, 7)
        );

        snake.turnRight();
        assertEquals(snake.getDirection(), ISnakeSegment.Direction.RIGHT);

        snake.turnRight();
        assertEquals(snake.getDirection(), ISnakeSegment.Direction.DOWN);

        snake.turnRight();
        assertEquals(snake.getDirection(), ISnakeSegment.Direction.LEFT);

        snake.turnRight();
        assertEquals(snake.getDirection(), ISnakeSegment.Direction.UP);
    }

    @Test
    public void testTurnLeft() throws Exception {
        ISnake snake = new Snake(
                new SnakeSegment(ISnakeSegment.Direction.UP, 5, 5),
                new SnakeSegment(ISnakeSegment.Direction.UP, 5, 6),
                new SnakeSegment(ISnakeSegment.Direction.UP, 5, 7)
        );

        snake.turnLeft();
        assertEquals(snake.getDirection(), ISnakeSegment.Direction.LEFT);

        snake.turnLeft();
        assertEquals(snake.getDirection(), ISnakeSegment.Direction.DOWN);

        snake.turnLeft();
        assertEquals(snake.getDirection(), ISnakeSegment.Direction.RIGHT);

        snake.turnLeft();
        assertEquals(snake.getDirection(), ISnakeSegment.Direction.UP);
    }

    @Test
    public void testMove() throws Exception {
        int x[] = {2, 2, 1, 1, 1, 2, 3, 4, 4, 4, 4, 4, 5, 6, 7, 7, 7, 6};
        int y[] = {4, 3, 3, 2, 1, 1, 1, 1, 2, 3, 4, 5, 5, 5, 5, 4, 3, 3};

        ISnake snake = new Snake(
                new SnakeSegment(ISnakeSegment.Direction.DOWN, x[0], y[0]),
                new SnakeSegment(ISnakeSegment.Direction.DOWN, x[1], y[1]),
                new SnakeSegment(ISnakeSegment.Direction.DOWN, x[2], y[2])
        );

        for (int i = 3; i < x.length || i < y.length; i++) {
            snake.addSegment(new SnakeSegment(ISnakeSegment.Direction.DOWN, x[i], y[i]));
        }

        snake.move();

        ArrayList<ISnakeSegment> snakeSegments = snake.getSegments();
        for (int i = snakeSegments.size() - 1; i > 0; i--) {
            assertEquals(snakeSegments.get(i).getX(), x[i - 1]);
            assertEquals(snakeSegments.get(i).getY(), y[i - 1]);
        }

        ISnakeSegment head = snakeSegments.get(0);
        assertEquals(head.getX(), x[0]);
        assertEquals(head.getY(), y[0] + 1);
    }

    @Test
    public void testMove2() throws Exception {
        int x[] = {2, 2, 1, 1, 1, 2, 3, 4, 4, 4, 4, 4, 5, 6, 7, 7, 7, 6};
        int y[] = {4, 3, 3, 2, 1, 1, 1, 1, 2, 3, 4, 5, 5, 5, 5, 4, 3, 3};

        final int LAST_INDEX = x.length - 1;

        ISnake snake = new Snake(
                new SnakeSegment(ISnakeSegment.Direction.RIGHT, x[LAST_INDEX], y[LAST_INDEX]),
                new SnakeSegment(ISnakeSegment.Direction.DOWN, x[LAST_INDEX], y[LAST_INDEX]),
                new SnakeSegment(ISnakeSegment.Direction.DOWN, x[LAST_INDEX], y[LAST_INDEX])
        );

        for (int i = 3; i < x.length || i < y.length; i++) {
            snake.addSegment(new SnakeSegment(ISnakeSegment.Direction.DOWN, x[0], y[0]));
        }

        snake.move();
        snake.turnRight(); snake.move(); snake.move();
        snake.turnRight(); snake.move(); snake.move(); snake.move();
        snake.turnRight(); snake.move(); snake.move(); snake.move(); snake.move();
        snake.turnLeft();  snake.move(); snake.move(); snake.move();
        snake.turnLeft();  snake.move(); snake.move();
        snake.turnLeft();  snake.move();
        snake.turnRight(); snake.move();

        ArrayList<ISnakeSegment> snakeSegments = snake.getSegments();
        for (int i = 0; i < snakeSegments.size(); i++) {
            assertEquals(snakeSegments.get(i).getX(), x[i]);
            assertEquals(snakeSegments.get(i).getY(), y[i]);
        }
    }

}