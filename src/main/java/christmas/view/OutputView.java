package christmas.view;

import christmas.domain.Menu;
import christmas.domain.Order;

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
}
