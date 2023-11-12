package christmas.validator;

import java.util.regex.Pattern;

public class InputValidator {

    private final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+");

    public void validateDate(String input) {
        validateNumber(input);
        validateRange(input);
    }

    private void validateNumber(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches())
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    private void validateRange(String input) {
        try {
            Integer date = Integer.parseInt(input);
            if (!(1 <= date && date <= 31))
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
