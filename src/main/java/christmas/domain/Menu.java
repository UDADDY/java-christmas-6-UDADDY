package christmas.domain;

import christmas.domain.constant.MenuBoard;

import java.util.Objects;

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

    public String getName() {
        return menu.getName();
    }

    public Integer getCount() {
        return this.count;
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

    public boolean isChampagne() {
        if (menu.equals(MenuBoard.CHAMPAGNE))
            return true;
        return false;
    }

    public Menu provide() {
        Menu newMenu = new Menu(this.menu, count++);
        return newMenu;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menu=" + menu +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu comparedMenu = (Menu) o;
        return menu == comparedMenu.menu && Objects.equals(count, comparedMenu.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu, count);
    }
}
