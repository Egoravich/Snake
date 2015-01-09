package snake;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {
    public static void main(String[] args) {
        JPanel canvas = Screen.getInstance();

        JFrame win = new JFrame("Snake");
        win.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if(keyCode == 40) {
                    // keyDown
                    if (Game.getSnake().getDirection() == ISnakeSegment.Direction.LEFT) {
                        Game.setPressKeyLeft(true);
                    } else if (Game.getSnake().getDirection() == ISnakeSegment.Direction.RIGHT) {
                        Game.setPressKeyRight(true);
                    }
                } else if(keyCode == 38) {
                    // keyTop
                    if (Game.getSnake().getDirection() == ISnakeSegment.Direction.LEFT) {
                        Game.setPressKeyRight(true);
                    } else if (Game.getSnake().getDirection() == ISnakeSegment.Direction.RIGHT) {
                        Game.setPressKeyLeft(true);
                    }
                } else if (keyCode == 37) {
                    // keyLeft
                    if (Game.getSnake().getDirection() == ISnakeSegment.Direction.UP) {
                        Game.setPressKeyLeft(true);
                    } else if (Game.getSnake().getDirection() == ISnakeSegment.Direction.DOWN) {
                        Game.setPressKeyRight(true);
                    }
                } else if (keyCode == 39) {
                    // keyRight
                    if (Game.getSnake().getDirection() == ISnakeSegment.Direction.UP) {
                        Game.setPressKeyRight(true);
                    } else if (Game.getSnake().getDirection() == ISnakeSegment.Direction.DOWN) {
                        Game.setPressKeyLeft(true);
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        // TODO: хардкод
        win.setSize(600, 600);
        win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        win.getContentPane().add(canvas);
        win.setVisible(true);

        Game.start(Screen.getInstance());
    }
}
