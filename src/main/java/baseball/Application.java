package baseball;

import baseball.common.GameState;
import baseball.view.GameConsoleView;

public class Application {
    public static void main(String[] args) throws IllegalArgumentException {
        GameState state = GameConsoleView.onGameJoin();
        do {
            state = handler(state);
        } while(state != GameState.END);
    }

    private static GameState handler(GameState state) throws IllegalArgumentException {
        if (state == GameState.START) {
            return GameConsoleView.onGameStart();
        }

        if (state == GameState.PLAY) {
            return GameConsoleView.onGamePlay();
        }

        if (state == GameState.MENU) {
            return GameConsoleView.onGameMenu();
        }

        return GameState.END;
    }
}