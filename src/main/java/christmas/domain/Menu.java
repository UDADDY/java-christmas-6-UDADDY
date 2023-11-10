package christmas.domain;

import christmas.domain.constant.MenuBoard;

public class Menu {
    private MenuBoard name;
    private Integer count;

    public Menu(MenuBoard menu, Integer count) {
        this.name = menu;
        this.count = count;
    }

    public Integer getPrice() {
        return this.name.getPrice() * this.count;
    }

    public Integer getCount() {
        return this.count;
    }

    public boolean isDessert() {
        if (name.equals(MenuBoard.CHOCOLATE_CAKE) || name.equals(MenuBoard.ICECREAM))
            return true;
        return false;
    }

    public boolean isMain() {
        if (name.equals(MenuBoard.T_BONE_STEAK) || name.equals(MenuBoard.BARBEQUE_RIP) || name.equals(MenuBoard.SEAFOOD_PASTA) || name.equals(MenuBoard.CHRISTMAST_PASTA))
            return true;
        return false;
    }

    public boolean isChampagne() {
        if (name.equals(MenuBoard.CHAMPAGNE))
            return true;
        return false;
    }

    public Menu provide() {
        Menu newMenu = new Menu(this.name, count++);
        return newMenu;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menu=" + name +
                ", count=" + count +
                '}';
    }
}
