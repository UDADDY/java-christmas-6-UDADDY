package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.Splitter;
import christmas.validator.InputValidator;

import java.util.List;

public class InputView {
    private final Splitter splitter;

    public InputView(final Splitter splitter) {
        this.splitter = splitter;
    }

    public Date inputDate() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n" +
                "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        InputValidator.validateDate(input);
        Date date = new Date(Integer.parseInt(input));
        return date;
    }

    public List<Menu> inputMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();
        List<Menu> menus = splitter.splitMenu(input);
        return menus;
    }
}
