package snake;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public abstract class Assets {
    private static final String SPR = System.getProperty("file.separator");
    private static final String DIR = System.getProperty("user.dir");
    private static final String SPRITE_MAP_PATH = SPR + "img" + SPR + "sprites.png";

    private static String spriteMapPath;
    private static BufferedImage spriteMap;
    static  {
        spriteMapPath = DIR + SPRITE_MAP_PATH;
        try (FileInputStream imgFile = new FileInputStream(spriteMapPath)) {
            spriteMap = ImageIO.read(imgFile);
        } catch (IOException e) {
            // TODO: --
            System.out.println("Can't read file " + spriteMapPath);
        }
    }

    private static ISprite bodyRight = new Sprite(spriteMap, 0, 0);
    private static ISprite bodyUp = new Sprite(spriteMap, 32, 0);

    private static ISprite headRight = new Sprite(spriteMap, 0, 64);
    private static ISprite headDown = new Sprite(spriteMap, 32, 64);
    private static ISprite headUp = new Sprite(spriteMap, 64, 64);
    private static ISprite headLeft = new Sprite(spriteMap, 96, 64);

    private static ISprite bodyTurnRightDown = new Sprite(spriteMap, 0, 32);
    private static ISprite bodyTurnLeftDown = new Sprite(spriteMap, 32, 32);
    private static ISprite bodyTurnRightUp = new Sprite(spriteMap, 64, 32);
    private static ISprite bodyTurnLeftUp = new Sprite(spriteMap, 96, 32);

    private static ISprite tailRight = new Sprite(spriteMap, 0, 96);
    private static ISprite tailLeft = new Sprite(spriteMap, 32, 96);
    private static ISprite tailUp = new Sprite(spriteMap, 64, 96);
    private static ISprite tailDown = new Sprite(spriteMap, 96, 96);

    private static ISprite apple = new Sprite(spriteMap, 0, 128);
    private static ISprite cherry = new Sprite(spriteMap, 0, 160);

    private static ISprite ground = new Sprite(spriteMap, 0, 192);
    private static ISprite grassFew = new Sprite(spriteMap, 32, 192);
    private static ISprite grassMedium = new Sprite(spriteMap, 64, 192);
    private static ISprite grassHide = new Sprite(spriteMap, 96, 192);

    private static ISprite gameOver = new Sprite(spriteMap, 0, 224, 200, 32, 0, false, 1);
    private static ISprite pressEnter = new Sprite(spriteMap, 0, 256, 256, 64, 0, false, 1);

    /**
     * По переданному сегменту змеи возвращает его спрайт.
     * @param segment
     * @return Экземпляр спрайта для данного сегмента змеи.
     */
    public static ISprite getSnakeSegmentSprite(ISnakeSegment segment) {
        ISprite spr = null;
        ISnakeSegment.Direction from = segment.getDirectionFrom();
        ISnakeSegment.Direction to = segment.getDirectionTo();

        switch (segment.getType()) {
            case HEAD:
                switch (to) {
                    case UP:
                        spr = headUp;
                        break;
                    case RIGHT:
                        spr = headRight;
                        break;
                    case DOWN:
                        spr = headDown;
                        break;
                    case LEFT:
                        spr = headLeft;
                        break;
                }
                break;
            case TAIL:
                switch (to) {
                    case UP:
                        spr = tailUp;
                        break;
                    case RIGHT:
                        spr = tailRight;
                        break;
                    case DOWN:
                        spr = tailDown;
                        break;
                    case LEFT:
                        spr = tailLeft;
                        break;
                }
                break;
            case BODY:
                if (from == to) {
                    if (to == ISnakeSegment.Direction.UP || to == ISnakeSegment.Direction.DOWN) {
                        spr = bodyUp;
                    } else {
                        spr = bodyRight;
                    }
                } else {
                    if ((from == ISnakeSegment.Direction.RIGHT && to == ISnakeSegment.Direction.UP)
                            || (from == ISnakeSegment.Direction.DOWN && to == ISnakeSegment.Direction.LEFT)) {
                        spr = bodyTurnLeftUp;
                    } else if ((from == ISnakeSegment.Direction.LEFT && to == ISnakeSegment.Direction.UP)
                            || (from == ISnakeSegment.Direction.DOWN && to == ISnakeSegment.Direction.RIGHT)) {
                        spr = bodyTurnRightUp;
                    } else if ((from == ISnakeSegment.Direction.UP && to == ISnakeSegment.Direction.LEFT)
                            || (from == ISnakeSegment.Direction.RIGHT && to == ISnakeSegment.Direction.DOWN)) {
                        spr = bodyTurnLeftDown;
                    } else {
                        spr = bodyTurnRightDown;
                    }
                }
                break;
        }

        return spr;
    }

    public static ISprite getGameOver() {
        return gameOver;
    }

    public static ISprite getCherry() {
        return cherry;
    }

    public static ISprite getApple() {
        return apple;
    }

    public static ISprite getGrassFew() {
        return grassFew;
    }

    public static ISprite getGrassMedium() {
        return grassMedium;
    }

    public static ISprite getGrassHide() {
        return grassHide;
    }

    public static ISprite getGround() {
        return ground;
    }

    public static ISprite getPressEnter() {
        return pressEnter;
    }
}
