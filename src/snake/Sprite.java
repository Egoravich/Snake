package snake;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sprite implements ISprite {

    /**
     * Загруженное изображение карты спрайта.
     */
    private BufferedImage spriteMap = null;
    /**
     * @see ISprite#DEFAULT_POSITION_ON_MAP_X
     */
    private int positionOnMapX = DEFAULT_POSITION_ON_MAP_X;
    /**
     * @see ISprite#DEFAULT_POSITION_ON_MAP_Y
     */
    private int positionOnMapY = DEFAULT_POSITION_ON_MAP_Y;
    /**
     * @see ISprite#DEFAULT_ONE_IMG_WIDTH
     */
    private int oneFrameWidth = DEFAULT_ONE_IMG_WIDTH;
    /**
     * @see ISprite#DEFAULT_ONE_IMG_HEIGTH
     */
    private int oneFrameHeight = DEFAULT_ONE_IMG_HEIGTH;
    /**
     * @see ISprite#DEFAULT_CHANGE_INTERVAL
     */
    private int changeInterval = DEFAULT_CHANGE_INTERVAL;

    /**
     * @see ISprite#DEFAULT_REPEAT_ON
     */
    private boolean repeatOn = DEFAULT_REPEAT_ON;
    /**
     * Флаг, показывающий что это первый проход анимации.
     *
     * @see Sprite#repeatOn
     */
    private boolean isFirstRepeat = true;
    /**
     * @see ISprite#DEFAULT_CURRENT_FRAME_NUMBER
     */
    private int currentFrameNumber = DEFAULT_CURRENT_FRAME_NUMBER;
    /**
     * @see ISprite#DEFAULT_FRAMES_COUNT
     */
    private int framesCount = DEFAULT_FRAMES_COUNT;
    /**
     * Время последней смены файла.
     */
    private long lastUpdateTime = DEFAULT_LAST_UPDATE_TIME;
    /**
     * Общий контекст в которм происходит отрисовка. Испальзуется, если не
     * передан контекст для конкретной отрисовки.
     *
     * @see ISprite#drawSprite(int, int, long)
     */
    private Graphics drawContext = null;

    /**
     * Конструктор с проверкой параметров.
     * Описание параметров {@link Sprite#Sprite(int, int, int, int, int, boolean, int, java.awt.image.BufferedImage)}.
     */
    private Sprite(BufferedImage spriteMap) {
        if (spriteMap == null) {
            System.out.println("Sprite map is null, in object " + this.hashCode());
        }
    }

    /**
     * Конструктор для спрайта со всеми возможными параметрами.
     *
     * @param oneFrameWidth  Ширина одного изображения в наборе.
     * @param oneFrameHeight Высота одного изображения в наборе.
     * @param positionOnMapX Позиция <i>x</i> первого изображения из набора на карте спрайтов. (0:0 левый верхний угол)
     * @param positionOnMapY Позиция <i>Y</i> первого изображения из набора на карте спрайтов. (0:0 левый верхний угол)
     * @param changeInterval Интервал смены изображений (скорость анимации).
     * @param repeatOn       Нужно ли повторять анимацию.
     * @param framesCount    Количество изображений в наборе.
     * @param spriteMap      Карта спрайтов.
     * @param drawContext    Контекст рисования.
     * @see Sprite#Sprite(java.awt.image.BufferedImage, int, int, java.awt.Graphics)
     */
    public Sprite(BufferedImage spriteMap, int positionOnMapX, int positionOnMapY, int oneFrameWidth,
                  int oneFrameHeight, int changeInterval, boolean repeatOn, int framesCount, Graphics drawContext) {
        this(
                spriteMap,
                positionOnMapX,
                positionOnMapY,
                oneFrameWidth,
                oneFrameHeight,
                changeInterval,
                repeatOn,
                framesCount
        );
        this.drawContext = drawContext;
    }

    /**
     * Конструктор для спрайта со всеми возможными параметрами, без контекста рисования.
     *
     * Описание параметров {@link Sprite#Sprite(java.awt.image.BufferedImage, int, int, int, int, int, boolean, int, java.awt.Graphics)}.
     *
     * @see Sprite#Sprite(java.awt.image.BufferedImage, int, int, int, int, int, boolean, int, java.awt.Graphics)
     */
    public Sprite(BufferedImage spriteMap, int positionOnMapX, int positionOnMapY, int oneFrameWidth,
                  int oneFrameHeight, int changeInterval, boolean repeatOn, int framesCount) {
        this(spriteMap);
        this.spriteMap = spriteMap;
        this.positionOnMapX = positionOnMapX;
        this.positionOnMapY = positionOnMapY;
        this.oneFrameWidth = oneFrameWidth;
        this.oneFrameHeight = oneFrameHeight;
        this.changeInterval = changeInterval;
        this.repeatOn = repeatOn;
        this.framesCount = framesCount;
    }

    /**
     * Конструктор для спрайта с одним изображением и размером {@link snake.ISprite#DEFAULT_ONE_IMG_WIDTH}
     * на {@link snake.ISprite#DEFAULT_ONE_IMG_HEIGTH} px.
     * <p/>
     * Описание параметров {@link Sprite#Sprite(java.awt.image.BufferedImage, int, int, int, int, int, boolean, int, java.awt.Graphics)}.
     *
     * @see Sprite#Sprite(java.awt.image.BufferedImage, int, int, int, int, int, boolean, int, java.awt.Graphics)
     */
    public Sprite(BufferedImage spriteMap, int positionOnMapX, int positionOnMapY, Graphics drawContext) {
        this(spriteMap);
        this.spriteMap = spriteMap;
        this.positionOnMapX = positionOnMapX;
        this.positionOnMapY = positionOnMapY;
        this.drawContext = drawContext;
    }

    /**
     * Конструктор для спрайта с одним изображением и размером {@link snake.ISprite#DEFAULT_ONE_IMG_WIDTH}
     * на {@link snake.ISprite#DEFAULT_ONE_IMG_HEIGTH} px.
     * Без указания контеста.
     * <p/>
     * Описание параметров {@link Sprite#Sprite(java.awt.image.BufferedImage, int, int, int, int, int, boolean, int, java.awt.Graphics)}.
     *
     * @see Sprite#Sprite(java.awt.image.BufferedImage, int, int, int, int, int, boolean, int, java.awt.Graphics)
     */
    public Sprite(BufferedImage spriteMap, int positionOnMapX, int positionOnMapY) {
        this(spriteMap);
        this.spriteMap = spriteMap;
        this.positionOnMapX = positionOnMapX;
        this.positionOnMapY = positionOnMapY;
    }

    @Override
    public void drawSprite(int posX, int posY, long timeNow, Graphics g) {
        if (lastUpdateTime == DEFAULT_LAST_UPDATE_TIME) {
            lastUpdateTime = timeNow;
        }

        int posOnMapX = positionOnMapX + currentFrameNumber * oneFrameWidth;
        int posOnMapY = positionOnMapY;

        g.drawImage(
                spriteMap,
                posX,
                posY,
                posX + oneFrameWidth,
                posY + oneFrameHeight,
                posOnMapX,
                posOnMapY,
                posOnMapX + oneFrameWidth,
                posOnMapY + oneFrameHeight,
                null);

        if (changeInterval < timeNow - lastUpdateTime) {
            lastUpdateTime = timeNow;
            currentFrameNumber++;

            if (currentFrameNumber >= framesCount) {
                currentFrameNumber = 0;
                isFirstRepeat = false;
            }

            if (!isFirstRepeat && !repeatOn) {
                currentFrameNumber = framesCount - 1;
            }
        }

    }

    @Override
    public void drawSprite(int posX, int posY, long timeNow) {
        if (drawContext instanceof Graphics) {
            drawSprite(posX, posY, timeNow, drawContext);
        }
    }

    @Override
    public void setDrawContext(Graphics g) {
        drawContext = g;
    }

    @Override
    public int getOneFrameWidth() {
        return oneFrameWidth;
    }

    @Override
    public int getOneFrameHeight() {
        return oneFrameHeight;
    }

    public int getFramesCount() {
        return framesCount;
    }

    public boolean isRepeatOn() {
        return repeatOn;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        String LS = System.getProperty("line.separator");

        str.append("Sprite:");
        str.append(LS);

        str.append("\tpositionOnMapX: ");
        str.append(positionOnMapX);
        str.append(LS);

        str.append("\tpositionOnMapY: ");
        str.append(positionOnMapY);
        str.append(LS);

        str.append("\toneFrameWidth: ");
        str.append(oneFrameWidth);
        str.append(LS);

        str.append("\toneFrameHeight: ");
        str.append(oneFrameHeight);
        str.append(LS);

        str.append("\tchangeInterval: ");
        str.append(changeInterval);
        str.append(LS);

        str.append("\trepeatOn: ");
        str.append(repeatOn);
        str.append(LS);

        str.append("\tframesCount: ");
        str.append(framesCount);
        str.append(LS);

        str.append("\tlastUpdateTime: ");
        str.append(lastUpdateTime);
        str.append(Character.LINE_SEPARATOR);

        return str.toString();
    }
}
