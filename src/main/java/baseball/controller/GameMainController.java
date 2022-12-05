package baseball.controller;

import baseball.common.GameRole;
import baseball.common.GameState;
import baseball.model.GameScoreModel;
import baseball.view.GameConsoleView;

import java.util.List;

public class GameMainController {
    private static List<Integer> computer;

    public static GameState onGameJoin() {
        return GameState.START;
    }

    public static GameState onGameStart() {
        computer = GameRole.makeRandomNumbers();
        return GameState.PLAY;
    }

    public static GameState onGamePlay(String command) throws IllegalArgumentException {
        int number = Integer.parseInt(command);
        List<Integer> user = GameRole.toDigits(number);
        if (GameRole.isZeroOrLess(number) || !GameRole.isValidNumberLength(number) || !GameRole.isValidNumbers(user)) {
            throw new IllegalArgumentException();
        }
        return onGameResult(user);
    }

    private static GameState onGameResult(List<Integer> user) {
        GameState state = GameState.PLAY;
        GameScoreModel score = GameRole.calcScore(computer, user);
        if (score.getStrike() == GameRole.MAX_NUMBER_LENGTH) {
            state = GameState.MENU;
        }
        GameConsoleView.onGameResult(score);
        return state;
    }

    public static GameState onGameMenu(String command) throws IllegalArgumentException {
        int number = Integer.parseInt(command);
        if (number == 1 || number == 2) {
            return onGameMenuResult(number);
        }
        throw new IllegalArgumentException();
    }

    public static GameState onGameMenuResult(int menu) {
        if (menu == 1) {
            return onGameJoin();
        }
        return GameState.END;
    }
}
