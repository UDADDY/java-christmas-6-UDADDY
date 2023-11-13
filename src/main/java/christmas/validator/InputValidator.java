package christmas.validator;

import christmas.domain.constant.MenuBoard;

import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+");

    public static void validateDate(String input) {
        validateNumber(input);
        validateNull(input);
    }


    public static void validateMenu(String[] menuAndCount) {
        validateForm(menuAndCount);

        String menu = menuAndCount[0];
        String count = menuAndCount[1];

        validateExistMenuBoard(menu);
        validateIsNumber(count);
    }

    private static void validateNull(String input) {
        if (input.isEmpty())
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    private static void validateForm(String[] menuAndCount) {
        if (menuAndCount.length != 2)
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    private static void validateNumber(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches())
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    private static void validateExistMenuBoard(String menu) {
        if (MenuBoard.getValueByName(menu) == null)
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    private static void validateIsNumber(String count) {
        try {
            Integer.parseInt(count);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
