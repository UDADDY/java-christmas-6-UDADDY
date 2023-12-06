package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.Splitter;
import christmas.domain.constant.InputMessage;
import christmas.validator.InputValidator;

import java.util.List;

public class InputView {
    private final Splitter splitter;

    public InputView(final Splitter splitter) {
        this.splitter = splitter;
    }

    public Date inputDate() {
        System.out.println(InputMessage.INPUT_DATE.getMessage());
        String input = Console.readLine();
        InputValidator.validateDate(input);
        Date date = new Date(Integer.parseInt(input));
        return date;
    }

    public List<Menu> inputMenu() {
        System.out.println();
        System.out.println(InputMessage.INPUT_MENU.getMessage());
        String input = Console.readLine();
        List<Menu> menus = splitter.splitMenu(input);
        return menus;
    }
}
