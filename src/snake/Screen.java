package snake;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class Screen extends JPanel implements IScreen {
    private final int SCREEN_WIDTH = 32 * Game.getDefaultColumnCount();
    private final int SCREEN_HEIGHT = 32 * Game.getDefaultRowCount();

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
        if (Game.isPlay()) {
            setState(State.GAME_RUNNING);
        } else if (Game.isPause()) {
            setState(State.GAME_PAUSED);
        } else if(Game.isGameOver()) {
            setState(State.GAME_OVER);
        } else {
            setState(State.MAIN_MENU);
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        switch (getState()) {
            case GAME_RUNNING:
                drawGameRunning(g);
                break;
            case GAME_PAUSED:
                drawGamePaused(g);
                break;
            case GAME_OVER:
                drawGameRunning(g);
                drawGameOver(g);
                break;
            case MAIN_MENU:
                drawMainMenu(g);
                break;
        }
    }

    private void drawMainMenu(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        drawSpriteInCenter(Assets.getPressEnter(), g);
    }

    private void drawGameRunning(Graphics g) {
        long timeNow = getTimeNow();
        ISprite tmp;
        int tmpX, tmpY;

        // Отрисовка сетки (земли)
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
            tmpX = oneBlock.getX() * getOneCellWidth();
            tmpY = oneBlock.getY() * getOneCellHeight();

            tmp.drawSprite(tmpX, tmpY, getOneCellWidth(), getOneCellHeight(), timeNow, g);
        }
        // /Отрисовка сетки

        // Отрисовка змеи
        for (ISnakeSegment segment : Game.getSnake().getSegments()) {
            tmp = Assets.getSnakeSegmentSprite(segment);
            tmpX = segment.getX() * getOneCellWidth();
            tmpY = segment.getY() * getOneCellHeight();
            tmp.drawSprite(tmpX, tmpY, getOneCellWidth(), getOneCellHeight(), timeNow, g);
        }
        // /Отрисовка змеи (земли)

        // Отрисовка еды
        IFood food = Game.getFood();
        if (food.getType() == IFood.Type.APPLE) {
            tmp = Assets.getApple();
        } else {
            tmp = Assets.getCherry();
        }
        tmpX = food.getX() * getOneCellWidth();
        tmpY = food.getY() * getOneCellHeight();
        tmp.drawSprite(tmpX, tmpY, getOneCellWidth(), getOneCellHeight(), timeNow, g);
        // /Отрисовка еды

        // Отрисовка счета
        g.setColor(Color.blue);
        g.drawString("Score: " + Game.getScore(), 10, 16);
        // Отрисовка счета
    }


    private void drawGamePaused(Graphics g) {
        drawGameRunning(g);
    }
    private void drawGameOver(Graphics g) {
        drawSpriteInCenter(Assets.getGameOver(), g);

//        Assets.getGameOver().drawSprite(10, 100, getTimeNow(), g);
    }

    private void drawSpriteInCenter(ISprite spr, Graphics g) {
        int y = getScreenHeight() / 2 - spr.getOneFrameHeight() / 2;
        int x = getScreenWidth() / 2 - spr.getOneFrameWidth() / 2;

        spr.drawSprite(x, y, getTimeNow(), g);
    }

    private int getOneCellWidth() {
        return SCREEN_WIDTH / Game.getDefaultColumnCount();
    }

    private int getOneCellHeight() {
        return SCREEN_HEIGHT / Game.getDefaultRowCount();
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    @Override
    public int getScreenHeight() {
        return SCREEN_HEIGHT;
    }
}
