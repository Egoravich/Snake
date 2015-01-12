package snake;

public interface IScreen {
    static enum State {
        MAIN_MENU,
        GAME_RUNNING,
        GAME_PAUSED,
        GAME_OVER,
    }

    public void drawScreen();

    public State getState();
    public void setState(State state);

    public int getScreenWidth();
    public int getScreenHeight();
}
