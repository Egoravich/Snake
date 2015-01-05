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

        result = 5;
        segment = new SnakeSegment(result, 0);
        assertEquals(segment.getX(), result);

        result = 0;
        segment = new SnakeSegment(result, 0);
        assertEquals(segment.getX(), result);

        result = -5;
        segment = new SnakeSegment(result, 0);
        assertEquals(segment.getX(), result);
    }

    @Test
    public void testSetY() throws Exception {
        ISnakeSegment segment;
        int result;

        result = 5;
        segment = new SnakeSegment(0, result);
        assertEquals(segment.getY(), result);

        result = 0;
        segment = new SnakeSegment(0, result);
        assertEquals(segment.getY(), result);

        result = -5;
        segment = new SnakeSegment(0, result);
        assertEquals(segment.getY(), result);
    }

    @Test
    public void testGetAndSetType() throws Exception {
        ISnakeSegment segment;
        ISnakeSegment.SegmentType result;

        result = ISnakeSegment.SegmentType.BODY;
        segment = new SnakeSegment(0, 0);
        assertEquals(segment.getType(), result);

        result = ISnakeSegment.SegmentType.HEAD;
        segment = new SnakeSegment(result, 0, 0);
        assertEquals(segment.getType(), result);

        result = ISnakeSegment.SegmentType.TAIL;
        segment = new SnakeSegment(0, 0);
        segment.setType(result);
        assertEquals(segment.getType(), result);
    }

} 
