package christmas.domain;

import java.util.List;

public class Order {
    private List<Menu> menus;

    public Order(List<Menu> menus) {
        this.menus = menus;
    }

    public Integer calculateBeforeDiscount() {
        Integer sum = 0;
        for (Menu menu : menus) {
            sum += menu.getPrice();
        }
        return sum;
    }
}
