package baseball.common;

import baseball.model.GameScoreModel;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class GameRole {
    public static final int MAX_NUMBER_LENGTH = 3;
    private static final int MIN_DIGIT_NUMBER = 1, MAX_DIGIT_NUMBER = 9;

    public static GameScoreModel calcScore(List<Integer> computer, List<Integer> user) {
        GameScoreModel score = new GameScoreModel(0, 0);
        for (int i = 0; i < computer.size(); i++) {
            int index = user.indexOf(computer.get(i));
            if (index != - 1) {
                incScore(score, index, i);
            }
        }
        return score;
    }

    private static void incScore(GameScoreModel score, int index, int match) {
        if (index == match) {
            score.setStrike(score.getStrike() + 1);
            return;
        }
        score.setBall(score.getBall() + 1);
    }

    public static List<Integer> makeRandomNumbers() {
        List<Integer> numbers = new ArrayList<>();
        int size = 0;
        do {
            int number = Randoms.pickNumberInRange(MIN_DIGIT_NUMBER, MAX_DIGIT_NUMBER);
            if (!numbers.contains(number)) {
                numbers.add(number);
                size++;
            }
        } while (size < MAX_NUMBER_LENGTH);
        return numbers;
    }

    public static List<Integer> toDigits(int number) {
        List<Integer> numbers = new ArrayList<>();
        int length = getNumberLength(number);
        for (int i = 0; i < length; i++) {
            int pow = (int) Math.pow(10, (length - i) - 1);
            int digit = number / pow % 10;
            numbers.add(digit);
        }
        return numbers;
    }

    public static int getNumberLength(int number) {
        return (int) Math.log10(number) + 1;
    }

    public static boolean isZeroOrLess(int number) {
        return number <= 0;
    }

    public static boolean isValidNumberLength(int number) {
        return getNumberLength(number) == MAX_NUMBER_LENGTH;
    }

    public static boolean isValidNumberRange(int number) {
        return number >= MIN_DIGIT_NUMBER && number <= MAX_DIGIT_NUMBER;
    }

    public static boolean isValidNumbers(List<Integer> numbers) {
        List<Integer> temp = new ArrayList<>();
        for (int digit : numbers) {
            if (!isValidNumberRange(digit) || temp.contains(digit)) {
                return false;
            }
            temp.add(digit);
        }
        return true;
    }
}
