package christmas.domain;

import christmas.domain.constant.MenuBoard;

public class Menu {
    private MenuBoard menu;
    private Integer count;

    public Menu(MenuBoard menu, Integer count) {
        this.menu = menu;
        this.count = count;
    }

    public Integer getPrice() {
        return this.menu.getPrice() * this.count;
    }

    public boolean isDessert() {
        if (menu.equals(MenuBoard.CHOCOLATE_CAKE) || menu.equals(MenuBoard.ICECREAM))
            return true;
        return false;
    }

    public boolean isMain() {
        if (menu.equals(MenuBoard.T_BONE_STEAK) || menu.equals(MenuBoard.BARBEQUE_RIP) || menu.equals(MenuBoard.SEAFOOD_PASTA) || menu.equals(MenuBoard.CHRISTMAST_PASTA))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menu=" + menu +
                ", count=" + count +
                '}';
    }
}
