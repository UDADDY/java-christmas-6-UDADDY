package christmas.view;

import christmas.domain.Menu;
import christmas.domain.Order;

import java.util.List;

public class OutputView {

    public void printMenu(Order order) {
        System.out.println("<주문 메뉴>");

        List<Menu> menus = order.getMenus();
        for (Menu menu : menus) {
            System.out.println(menu.getName() + " " + menu.getCount() + "개");
        }

    }
}
