package christmas.validator;

import christmas.domain.constant.ErrorMessage;
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

    public static void validateNull(String input) {
        if (input.isEmpty())
            throw new IllegalArgumentException(ErrorMessage.WRONG_DATE.getMessage());
    }

    private static void validateForm(String[] menuAndCount) {
        if (menuAndCount.length != 2)
            throw new IllegalArgumentException(ErrorMessage.WRONG_DATE.getMessage());
    }

    private static void validateNumber(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches())
            throw new IllegalArgumentException(ErrorMessage.WRONG_DATE.getMessage());
    }

    private static void validateExistMenuBoard(String menu) {
        if (MenuBoard.getValueByName(menu) == null)
            throw new IllegalArgumentException(ErrorMessage.WRONG_MENU.getMessage());
    }

    private static void validateIsNumber(String count) {
        try {
            Integer.parseInt(count);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.WRONG_MENU.getMessage());
        }
    }
}
