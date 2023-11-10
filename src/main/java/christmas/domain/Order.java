package christmas.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Order {
    private List<Menu> menus;
    private Date date;
    private Integer totalPrice;
    private Integer totalDiscountPrice;

    public Order(List<Menu> menus, Date date) {
        this.menus = menus;
        this.date = date;
        totalPrice = calculateBeforeDiscount();
        totalDiscountPrice = 0;
    }

    public Integer getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public Integer calculateBeforeDiscount() {
        Integer sum = 0;
        for (Menu menu : menus) {
            sum += menu.getPrice();
        }
        return sum;
    }

    public boolean isDiscountable() {
        Integer priceTotal = calculateBeforeDiscount();
        if (priceTotal > 10_000)
            return true;
        return false;
    }

    public void discountChristmas() {
        int discoutPrice = date.getDiscountPriceChristmas();
        totalDiscountPrice += discoutPrice;
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

    }
}
