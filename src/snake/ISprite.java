package snake;

import java.awt.Graphics;

/**
 * Этот интерфейс описывает работу с картой спрайтов.
 * Под набором здесь понимается часть карты спрайтов выраженная набором изображений для одного объекта.
 */
public interface ISprite {
    /**
     * Ширина (по умолчанию) одного изображения в наборе.
     */
    static final int DEFAULT_ONE_IMG_WIDTH = 32;
    /**
     * Высота (по умолчанию) одного изображения в наборе.
     */
    static final int DEFAULT_ONE_IMG_HEIGTH = 32;
    /**
     * Интервал смены изображений (сдвига по спрайту) по умолчанию.
     */
    static final int DEFAULT_CHANGE_INTERVAL = 200;
    /**
     * Количество (по умолчанию) изображений в наборе.
     */
    static final int DEFAULT_FRAMES_COUNT = 1;
    /**
     * Позиция x первого изображения из набора на карте спрайтов.
     */
    static final int DEFAULT_POSITION_ON_MAP_X = 0;
    /**
     * Позиция y первого изображения из набора на карте спрайтов.
     */
    static final int DEFAULT_POSITION_ON_MAP_Y = 0;
    /**
     * Флаг показывающий, нужно ли повторять анимацию.
     */
    static final boolean DEFAULT_REPEAT_ON = true;
    /**
     * Номер кадра показываемого в данный момент.
     */
    static final int DEFAULT_CURRENT_FRAME_NUMBER = 0;
    /**
     * Время (по умолчанию) когда кадр для этого объекта был сменен последний раз.
     */
    static final long DEFAULT_LAST_UPDATE_TIME = -1;

    /**
     * Отрисовка текущего изображения спрайта в заданном контексте.
     *
     * @param posX    Координата x левого верхнего угла спрайта.
     * @param posY    Координата y левого верхнего угла спрайта.
     * @param g       Контекст в котором происходит отрисовка.
     * @param timeNow Текущее время. Служит для выбора конкретного изображения с карты спрайта.
     * @see ISprite#drawSprite(int, int, long)
     */
    public void drawSprite(int posX, int posY, long timeNow, Graphics g);

    /**
     * Отрисовка в контексте по умолчанию.
     *
     * @param posX
     * @param posY
     * @param timeNow see ISprite#drawSprite(int, int, long, Graphics)
     */
    public void drawSprite(int posX, int posY, long timeNow);

    /**
     * Установить контекст по умолчанию, в котором отрисовываются
     * все спрайты.
     *
     * @param g Контекст в котором будет происходить отрисовка.
     * @see ISprite#drawSprite(int, int, long)
     */
    public void setDrawContext(Graphics g);

    public int getOneFrameWidth();

    public int getOneFrameHeight();
}
