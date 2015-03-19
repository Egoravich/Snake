package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {
    public static void main(String[] args) {
        JPanel canvas = Screen.getInstance();
        canvas.setPreferredSize(new Dimension(Screen.getInstance().getScreenWidth(), Screen.getInstance().getScreenHeight()));

        JPanel panel = new JPanel();
        panel.setBackground(Color.black);

        panel.setLayout(new BorderLayout(0, 0));
        panel.add(canvas);

        JFrame win = new JFrame("Snake");
        win.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == 10 && Screen.getInstance().getState() == IScreen.State.MAIN_MENU) {
                    // Enter
                    Thread game = new Thread(new Runnable() {
                        public void run() {
                            Game.start(Screen.getInstance());
                        }
                    });
                    game.start();
                }

                if (keyCode == 40 && Game.isPlay()) {
                    // keyDown
                    if (Game.getSnake().getDirection() == ISnakeSegment.Direction.LEFT) {
                        Game.setPressKeyLeft(true);
                    } else if (Game.getSnake().getDirection() == ISnakeSegment.Direction.RIGHT) {
                        Game.setPressKeyRight(true);
                    }
                } else if (keyCode == 38 && Game.isPlay()) {
                    // keyTop
                    if (Game.getSnake().getDirection() == ISnakeSegment.Direction.LEFT) {
                        Game.setPressKeyRight(true);
                    } else if (Game.getSnake().getDirection() == ISnakeSegment.Direction.RIGHT) {
                        Game.setPressKeyLeft(true);
                    }
                } else if (keyCode == 37 && Game.isPlay()) {
                    // keyLeft
                    if (Game.getSnake().getDirection() == ISnakeSegment.Direction.UP) {
                        Game.setPressKeyLeft(true);
                    } else if (Game.getSnake().getDirection() == ISnakeSegment.Direction.DOWN) {
                        Game.setPressKeyRight(true);
                    }
                } else if (keyCode == 39 && Game.isPlay()) {
                    // keyRight
                    if (Game.getSnake().getDirection() == ISnakeSegment.Direction.UP) {
                        Game.setPressKeyRight(true);
                    } else if (Game.getSnake().getDirection() == ISnakeSegment.Direction.DOWN) {
                        Game.setPressKeyLeft(true);
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        win.add(panel);
        win.pack();
        win.setVisible(true);

    }
}
