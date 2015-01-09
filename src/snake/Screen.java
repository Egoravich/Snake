package snake;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class Screen extends JPanel implements IScreen {
    private static Screen instance = null;
    private State state;

    private Screen() {
        state = State.MAIN_MENU;
    }

    public static Screen getInstance() {
        if (instance == null) {
            instance = new Screen();
        }

        return instance;
    }

    private static long getTimeNow() {
        return Calendar.getInstance().getTimeInMillis();
    }

    @Override
    public void drawScreen() {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (Game.isPlay()) {
            setState(State.GAME_ON);
            drawGameOn(g);
        }
        if (Game.isPause()) {
            setState(State.GAME_PAUSE);
            drawGamePause(g);
        } else if(Game.isGameOver()) {
            setState(State.GAME_OVER);
            drawGameOver(g);
        } else {
            setState(State.MAIN_MENU);
            drawMainMenu(g);
        }
    }

    private void drawMainMenu(Graphics g) {

    }
    private void drawGameOn(Graphics g) {
        long timeNow = getTimeNow();
        ISprite tmp = null;

        // Отрисовка сетки (земли)
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.LIGHT_GRAY);

        for (IGround oneBlock : Game.getGround()) {
            tmp = Assets.getGround();
            switch (oneBlock.getType()) {
                case GRASS_FEW:
                    tmp = Assets.getGrassFew();
                    break;
                case GRASS_MEDIUM:
                    tmp = Assets.getGrassMedium();
                    break;
                case GRASS_HIGH:
                    tmp = Assets.getGrassHide();
                    break;
            }
            tmp.drawSprite(
                    oneBlock.getX() * tmp.getOneFrameWidth(), oneBlock.getY() * tmp.getOneFrameHeight(), timeNow, g);
        }
        // /Отрисовка сетки

        // Отрисовка змеи
        for (ISnakeSegment segment : Game.getSnake().getSegments()) {
            ISprite spr = Assets.getSnakeSegmentSprite(segment);
            spr.drawSprite(
                    segment.getX() * spr.getOneFrameWidth(),
                    segment.getY() * spr.getOneFrameWidth(),
                    timeNow, g
            );
        }
        // /Отрисовка змеи (земли)

        // Отрисовка еды
        IFood food = Game.getFood();
        if (food.getType() == IFood.Type.APPLE) {
            tmp = Assets.getApple();
            tmp.drawSprite(
                    food.getX() * tmp.getOneFrameWidth(),
                    food.getY() * tmp.getOneFrameHeight(),
                    timeNow, g
            );
        } else {
            tmp = Assets.getCherry();
            tmp.drawSprite(
                    food.getX() * tmp.getOneFrameWidth(),
                    food.getY() * tmp.getOneFrameHeight(),
                    timeNow, g
            );
        }
        // /Отрисовка еды

        // Game over
//        if (Main.isGameOver) {
//            Assets.getGameOver().drawSprite(10, 100, timeNow, g);
//        }
        // /Game over
    }
    private void drawGamePause(Graphics g) {
        drawGameOn(g);
    }
    private void drawGameOver(Graphics g) {
        Assets.getGameOver().drawSprite(10, 100, getTimeNow(), g);
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    @Override
    public State getState() {
        return state;
    }
}
