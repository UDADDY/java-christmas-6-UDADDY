package christmas.domain;

import christmas.domain.constant.MenuBoard;

public class Menu {
    private MenuBoard menu;
    private Integer count;

    public Menu(MenuBoard menu, Integer count) {
        this.menu = menu;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menu=" + menu +
                ", count=" + count +
                '}';
    }
}
