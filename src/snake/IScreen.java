package snake;

public interface IScreen {
    static enum State {
        MAIN_MENU,
        GAME_ON,
        GAME_PAUSE,
        GAME_OVER,
    }

    public void drawScreen();

    public State getState();
    public void setState(State state);
}
