package christmas.domain;

import christmas.domain.constant.DiscountName;

public class Benefit {
    private DiscountName discountName;
    private Integer price;

    public Benefit(DiscountName discountName, Integer price) {
        this.discountName = discountName;
        this.price = price;
    }

    public Integer getPrice() {
        return this.price;
    }
}
