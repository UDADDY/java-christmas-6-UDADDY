package christmas.domain;

import christmas.domain.constant.MenuBoard;

import java.util.ArrayList;
import java.util.List;

public class Splitter {
    public List<Menu> splitMenu(final String input) { // "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"
        List<Menu> menus = new ArrayList<>();
        final String[] menuAndCounts = input.split(",");
        for (String menuAndCount : menuAndCounts) {
            String[] splited = menuAndCount.split("-");
            String name = splited[0];
            Integer count = Integer.parseInt(splited[1]);
            MenuBoard menuBoard = MenuBoard.getValueByName(name);
            menus.add(new Menu(menuBoard, count));
        }
        return menus;
    }
}
