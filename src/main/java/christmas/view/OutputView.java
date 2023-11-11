package christmas.view;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.constant.MenuBoard;

import java.text.DecimalFormat;
import java.util.List;

public class OutputView {

    private DecimalFormat decimalFormat = new DecimalFormat("###,###");

    public void printMenu(Order order) {
        System.out.println("<주문 메뉴>");

        List<Menu> menus = order.getMenus();
        for (Menu menu : menus) {
            System.out.println(menu.getName() + " " + menu.getCount() + "개");
        }
    }

    public void printTotalPrice(Order order) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(decimalFormat.format(order.getTotalPrice()) + "원");
    }

    public void printGiveaway(Order order) {
        System.out.println("<증정 메뉴>");
        if (!order.isGiveaway()) {
            System.out.println("없음");
            return;
        }

        System.out.println("샴페인 1개");

    }
}
