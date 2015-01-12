package snake;

import java.util.ArrayList;

public abstract class Game {
    private static final int DEFAULT_ROW_COUNT = 10;//16
    private static final int DEFAULT_COLUMN_COUNT = 10;//18

    private static final int DEFAULT_CELL_WIDTH = 32;
    private static final int DEFAULT_CELL_HEIGHT = 32;

    private static final int DEFAULT_SCORE = 30;
    private static final int SCORE_FOR_ONE_FOOD = 10;

    private static boolean pressKeyLeft = false;
    private static boolean pressKeyRight = false;

    private static final int DEFAULT_SNAKE_HEAD_X = DEFAULT_COLUMN_COUNT / 2;
    private static final int DEFAULT_SNAKE_HEAD_Y = DEFAULT_ROW_COUNT / 2;
    private static final ISnakeSegment.Direction DEFAULT_SNAKE_DIRECTION = ISnakeSegment.Direction.UP;
    private static final int DEFAULT_UPDATE_INTERVAL = 350;

    private static ISnake snake;
    private static IFood food;
    private static ArrayList<IGround> ground;
    private static int score;
    private static boolean pause;
    private static boolean play;
    private static boolean gameOver;
    private static int updateInterval;

    private static void init() {
        snake = new Snake(
                new SnakeSegment(DEFAULT_SNAKE_DIRECTION, DEFAULT_SNAKE_HEAD_X, DEFAULT_SNAKE_HEAD_Y ),
                new SnakeSegment(DEFAULT_SNAKE_DIRECTION, DEFAULT_SNAKE_HEAD_X, DEFAULT_SNAKE_HEAD_Y + 1),
                new SnakeSegment(DEFAULT_SNAKE_DIRECTION, DEFAULT_SNAKE_HEAD_X, DEFAULT_SNAKE_HEAD_Y + 2)
        );

        food = new Food();
        repositionFood();

        ground = new ArrayList<>();
        IGround oneBlock;
        int rand;
        for (int i = 0; i < Game.getDefaultRowCount(); i++) {
            for (int j = 0; j < Game.getDefaultColumnCount(); j++) {
                rand = getRandom(0, 100);
                oneBlock = new Ground();
                oneBlock.setX(j);
                oneBlock.setY(i);
                if (rand < 25) {
                    oneBlock.setType(IGround.Type.GRASS_FEW);
                } else if (rand < 50) {
                    oneBlock.setType(IGround.Type.GRASS_MEDIUM);
                } else if (rand < 75) {
                    oneBlock.setType(IGround.Type.GRASS_HIGH);
                } else {
                    oneBlock.setType(IGround.Type.GROUND);
                }
                ground.add(oneBlock);
            }
        }

        score = DEFAULT_SCORE;

        pause = false;
        play = true;
        gameOver = false;

        updateInterval = DEFAULT_UPDATE_INTERVAL;
    }

    private static void update() {
        if (gameOver || pause) {
            return;
        }

        if (Game.isPressKeyLeft()) {
            snake.turnLeft();
            Game.setPressKeyLeft(false);
        }

        if (Game.isPressKeyRight()) {
            snake.turnRight();
            Game.setPressKeyRight(false);
        }

        // TODO: А точно это должно быть здесь? (snake.move())
        snake.move();

        if (snake.getHead().getX() < 0) {
            snake.getHead().setX(Game.getDefaultColumnCount() - 1);
        } else if (snake.getHead().getX() >= Game.getDefaultColumnCount()) {
            snake.getHead().setX(0);
        } else if (snake.getHead().getY() < 0) {
            snake.getHead().setY(Game.getDefaultRowCount() - 1);
        } else if (snake.getHead().getY() >= Game.getDefaultRowCount()) {
            snake.getHead().setY(0);
        }

        // Проверка гемовера и поеданий
        for (int x = 2; x < snake.getSegments().size(); x++) {
            if (snake.getHead().getX() == snake.getSegments().get(x).getX()
                    && snake.getHead().getY() == snake.getSegments().get(x).getY() ) {
                setGameOver(true);
                break;
            }
        }

        if(snake.getHead().getX() == food.getX() && snake.getHead().getY() == food.getY()) {
            // Поедание
            score += SCORE_FOR_ONE_FOOD;
            repositionFood();
            snake.addSegment();
        }
    }

    public static void start(IScreen screen) {
        init();

        while (true) {
            try {
                Thread.sleep(updateInterval);
                update();
                screen.drawScreen();
            } catch (InterruptedException e) {
                // TODO: Убрать/Обдумать
                e.printStackTrace();
            }
        }
    }

    public static void pause() {

    }

    public static void resume() {

    }

    private static void repositionFood() {
        if (getRandom(0, 100) > 50) {
            food.setType(IFood.Type.APPLE);
        } else {
            food.setType(IFood.Type.CHERRY);
        }

        int cellCount = Game.getDefaultColumnCount() * Game.getDefaultRowCount();
        int position = getRandom(0, cellCount - 1);
        int foodX, foodY;

        boolean isBreak = false;
        for (int i = position; i < position + cellCount; i++) {
            position = i;
            if (position >= cellCount) {
                position %= cellCount;
            }

            foodX = position % Game.getDefaultColumnCount();
            foodY = (position - foodX) / Game.getDefaultRowCount();

            for (ISnakeSegment segment : snake.getSegments()) {
                if (segment.getX() == foodX && segment.getY() == foodY) {
                    isBreak = false;
                    break;
                } else {
                    food.setX(foodX);
                    food.setY(foodY);
                    isBreak = true;
                }
            }

            if (isBreak) {
                break;
            }
        }
    }

    private static int getRandom(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public static ISnake getSnake() {
        return snake;
    }

    public static IFood getFood() {
        return food;
    }

    public static ArrayList<IGround> getGround() {
        return ground;
    }

    public static int getScore() {
        return score;
    }

    public static boolean isPause() {
        return pause;
    }

    public static boolean isPlay() {
        return play;
    }

    public static int getUpdateInterval() {
        return updateInterval;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    private static void setGameOver(boolean gameOver) {
        Game.gameOver = gameOver;
        if (gameOver) {
            pause = false;
            play = false;
        } else {
            pause = false;
            play = true;
        }
    }

    public static int getDefaultRowCount() {
        return DEFAULT_ROW_COUNT;
    }

    public static int getDefaultColumnCount() {
        return DEFAULT_COLUMN_COUNT;
    }

    public static int getDefaultCellHeight() {
        return DEFAULT_CELL_HEIGHT;
    }

    public static int getDefaultCellWidth() {
        return DEFAULT_CELL_WIDTH;
    }

    public static boolean isPressKeyLeft() {
        return pressKeyLeft;
    }

    public static void setPressKeyLeft(boolean pressKeyLeft) {
        Game.pressKeyLeft = pressKeyLeft;
    }

    public static boolean isPressKeyRight() {
        return pressKeyRight;
    }

    public static void setPressKeyRight(boolean pressKeyRight) {
        Game.pressKeyRight = pressKeyRight;
    }
}
