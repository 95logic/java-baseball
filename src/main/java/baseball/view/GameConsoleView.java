package baseball.view;

import baseball.common.GameMessage;
import baseball.common.GameRole;
import baseball.common.GameState;
import baseball.controller.GameMainController;
import baseball.model.GameScoreModel;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class GameConsoleView {

    public static GameState onGameJoin() {
        System.out.print(GameMessage.GAME_START);
        return GameMainController.onGameJoin();
    }

    public static GameState onGameStart() {
        return GameMainController.onGameStart();
    }

    public static GameState onGamePlay() throws IllegalArgumentException {
        System.out.print(GameMessage.INPUT_NUMBER);
        String command = Console.readLine();
        return GameMainController.onGamePlay(command);
    }

    public static void onGameResult(GameScoreModel score) {
        int ball = score.getBall();
        int strike = score.getStrike();
        if (ball == 0 && strike == 0) {
            System.out.println(GameMessage.NOTHING);
            return;
        }
        printScoreMessage(ball, strike);
    }

    private static void printScoreMessage(int ball, int strike) {
        StringBuilder builder = new StringBuilder();
        buildScoreMessage(builder, ball, strike);
        builder.append('\n');
        if (strike == GameRole.MAX_NUMBER_LENGTH) {
            builder.append(GameMessage.GAME_CLEAR);
        }
        System.out.print(builder);
    }

    private static void buildScoreMessage(StringBuilder builder, int ball, int strike) {
        List<String> messages = new ArrayList<>();
        addScoreSuffix(messages, ball, GameMessage.BALL);
        addScoreSuffix(messages, strike, GameMessage.STRIKE);
        for (String message : messages) {
            builder.append(message);
            builder.append(' ');
        }
        builder.deleteCharAt(builder.length() - 1);
    }

    private static void addScoreSuffix(List<String> messages, int score, String suffix) {
        if (score > 0) {
            messages.add(score + suffix);
        }
    }

    public static GameState onGameMenu() throws IllegalArgumentException {
        System.out.print(GameMessage.GAME_MENU);
        String command = Console.readLine();
        return GameMainController.onGameMenu(command);
    }
}
