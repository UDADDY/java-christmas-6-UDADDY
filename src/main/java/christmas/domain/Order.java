package christmas.domain;

import christmas.domain.constant.DiscountName;
import christmas.domain.constant.EventBadge;
import christmas.domain.constant.MenuBoard;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Menu> menus;
    private Date date;
    private Integer totalPrice;
    private List<Benefit> benefits;

    public Order(List<Menu> menus, Date date) {
        this.menus = new ArrayList<>(menus);
        this.date = date;
        this.benefits = new ArrayList<>();
        totalPrice = calculatePrice();
    }

    public Integer getTotalPrice() {
        return this.totalPrice;
    }

    public Integer getTotalDiscountPrice() {
        Integer sum = 0;
        for (Benefit benefit : benefits) {
            if (!benefit.getDiscountName().equals(DiscountName.GIVEAWAY))
                sum += benefit.getPrice();
        }
        return sum;
    }

    public Integer getTotalBenefitPrice() {
        Integer sum = 0;
        for (Benefit benefit : benefits) {
            sum += benefit.getPrice();
        }
        return sum;
    }

    public List<Benefit> getBenefits() {
        return this.benefits;
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
            benefits.add(new Benefit(DiscountName.CHRISTMAS_D_DAY, date.getDiscountPriceChristmas()));
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
            benefits.add(new Benefit(DiscountName.WEEKDAY, countDessert * 2_023));
        }
    }

    public void discountWeekend() {
        if (date.isWeekend()) {
            Integer countMain = getCountMain();
            benefits.add(new Benefit(DiscountName.WEEKEND, countMain * 2_023));
        }
    }

    public void discountSpecialDay() {
        if (date.isSpecialDay())
            benefits.add(new Benefit(DiscountName.SPECIAL, 1_000));
    }

    public void discountGiveaway() {
        if (isGiveaway())
            provideChampagne();
    }

    public boolean isGiveaway() {
        if (this.totalPrice >= 120_000)
            return true;
        return false;
    }

    public void provideChampagne() {
        benefits.add(new Benefit(DiscountName.GIVEAWAY, 25_000));
        if (containChampagne()) {
            int index = menus.indexOf(new Menu(MenuBoard.CHAMPAGNE, 0));
            menus.get(index).provide();
            return;
        }
        menus.add(new Menu(MenuBoard.CHAMPAGNE, 1));
    }

    public boolean containChampagne() {
        Menu filteredMenus = menus.stream()
                .filter(menu -> menu.getName().equals(MenuBoard.CHAMPAGNE.getName()))
                .findAny()
                .orElse(null);

        if (filteredMenus == null)
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
        if (getTotalBenefitPrice() >= EventBadge.SANTA.getMinimumStandard()) {
            return EventBadge.SANTA;
        }
        if (getTotalBenefitPrice() >= EventBadge.TREE.getMinimumStandard()) {
            return EventBadge.TREE;
        }
        if (getTotalBenefitPrice() >= EventBadge.STAR.getMinimumStandard()) {
            return EventBadge.STAR;
        }
        return EventBadge.NOTHING;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public Integer getCountByName(MenuBoard menuBoard) {
        Integer count = (int) menus.stream()
                .filter(menu -> menu.equals(menuBoard))
                .count();
        return count;
    }
}
