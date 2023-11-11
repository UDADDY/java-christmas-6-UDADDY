package christmas.domain;

import christmas.domain.constant.EventBadge;
import christmas.domain.constant.MenuBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Order {
    private List<Menu> menus;
    private Date date;
    private Integer totalPrice;
    private Integer totalDiscountPrice;

    public Order(List<Menu> menus, Date date) {
        this.menus = new ArrayList<>(menus);
        this.date = date;
        totalPrice = calculatePrice();
        totalDiscountPrice = 0;
    }

    public Integer getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public Integer calculatePrice() {
        Integer sum = 0;
        for (Menu menu : menus) {
            sum += menu.getPrice();
        }
        return sum;
    }

    public boolean isDiscountable() {
        Integer priceTotal = calculatePrice();
        if (priceTotal > 10_000)
            return true;
        return false;
    }

    public void discountChristmas() {
        if (date.isChristmastDiscountable()) {
            int discoutPrice = date.getDiscountPriceChristmas();
            totalDiscountPrice += discoutPrice;
        }
    }

    public Integer getCountDessert() {
        Integer sum = 0;
        for (Menu menu : menus) {
            if (menu.isDessert())
                sum += menu.getCount();
        }
        return sum;
    }

    public Integer getCountMain() {
        Integer sum = 0;
        for (Menu menu : menus) {
            if (menu.isMain())
                sum += menu.getCount();
        }
        return sum;
    }

    public void discountWeekday() { // 디저트 할인
        if (date.isWeekday()) {
            Integer countDessert = getCountDessert();
            totalDiscountPrice += countDessert * 2_023;
        }
    }

    public void discountWeekend() {
        if (date.isWeekend()) {
            Integer countMain = getCountMain();
            totalDiscountPrice += countMain * 2_023;
        }
    }

    public void discountSpecialDay() {
        if (date.isSpecialDay())
            totalDiscountPrice += 1_000;
    }

    public boolean isGiveaway() {
        if (this.totalPrice >= 120_000)
            return true;
        return false;
    }

    public void provideChampagne() {
        if (containChampagne()) {
            menus.stream().filter(Menu::isChampagne).map(Menu::provide).collect(Collectors.toList());
            return;
        }
        menus.add(new Menu(MenuBoard.CHAMPAGNE, 1));
    }

    public boolean containChampagne() {
        List<Menu> filteredMenus = menus.stream().filter(Menu::isChampagne).collect(Collectors.toList());
        if (filteredMenus.isEmpty())
            return false;
        return true;
    }

    public Integer getCountChampagne() {
        for (Menu menu : menus) {
            if (menu.isChampagne())
                return menu.getCount();
        }
        return 0;
    }

    public EventBadge getEventBadge() {
        if (totalDiscountPrice >= EventBadge.SANTA.getMinimumStandard()) {
            return EventBadge.SANTA;
        }
        if (totalDiscountPrice >= EventBadge.TREE.getMinimumStandard()) {
            return EventBadge.TREE;
        }
        if (totalDiscountPrice >= EventBadge.STAR.getMinimumStandard()) {
            return EventBadge.STAR;
        }
        return EventBadge.NOTHING;
    }

    public List<Menu> getMenus() {
        return menus;
    }
}
