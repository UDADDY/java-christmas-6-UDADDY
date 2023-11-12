package christmas.validator;

import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+");

    public static void validateDate(String input) {
        validateNumber(input);
    }

    public static void validateMenu(String input) {

    }

    private static void validateNumber(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches())
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}
