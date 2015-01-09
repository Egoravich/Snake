package test;

import org.junit.Test;
import snake.ISnakeSegment;
import snake.SnakeSegment;

import static org.junit.Assert.*;

public class SnakeSegmentTest {

    @Test
    public void testSetX() throws Exception {
        ISnakeSegment segment;
        int result;
        ISnakeSegment.Direction direction = ISnakeSegment.Direction.UP;

        result = 5;
        segment = new SnakeSegment(direction, result, 0);
        assertEquals(segment.getX(), result);

        result = 0;
        segment = new SnakeSegment(direction, result, 0);
        assertEquals(segment.getX(), result);

        result = -5;
        segment = new SnakeSegment(direction, result, 0);
        assertEquals(segment.getX(), result);
    }

    @Test
    public void testSetY() throws Exception {
        ISnakeSegment segment;
        int result;
        ISnakeSegment.Direction direction = ISnakeSegment.Direction.UP;

        result = 5;
        segment = new SnakeSegment(direction, 0, result);
        assertEquals(segment.getY(), result);

        result = 0;
        segment = new SnakeSegment(direction, 0, result);
        assertEquals(segment.getY(), result);

        result = -5;
        segment = new SnakeSegment(direction, 0, result);
        assertEquals(segment.getY(), result);
    }

    @Test
     public void testGetAndSetType() throws Exception {
        ISnakeSegment segment;
        ISnakeSegment.Type result;

        result = ISnakeSegment.Type.BODY;
        segment = new SnakeSegment(ISnakeSegment.Direction.DOWN, 0, 0);
        assertEquals(segment.getType(), result);

        result = ISnakeSegment.Type.HEAD;
        segment = new SnakeSegment(result, ISnakeSegment.Direction.DOWN, 0, 0);
        assertEquals(segment.getType(), result);

        result = ISnakeSegment.Type.TAIL;
        segment = new SnakeSegment(ISnakeSegment.Direction.DOWN, 0, 0);
        segment.setType(result);
        assertEquals(segment.getType(), result);
    }

    @Test
    public void testGetAndSetDirection() throws Exception {
        ISnakeSegment segment;
        ISnakeSegment.Direction from;
        ISnakeSegment.Direction to;

        from = ISnakeSegment.Direction.UP;
        to = ISnakeSegment.Direction.LEFT;
        segment = new SnakeSegment(ISnakeSegment.Type.BODY, from, to, 0, 0);
        assertEquals(segment.getDirectionFrom(), from);
        assertEquals(segment.getDirectionTo(), to);

        from = ISnakeSegment.Direction.DOWN;
        to = ISnakeSegment.Direction.UP;
        segment = new SnakeSegment(
                ISnakeSegment.Type.BODY, ISnakeSegment.Direction.RIGHT, ISnakeSegment.Direction.LEFT, 0, 0
        );
        segment.setDirectionFrom(from);
        segment.setDirectionTo(to);
        assertEquals(segment.getDirectionFrom(), from);
        assertEquals(segment.getDirectionTo(), to);

    }



} 
