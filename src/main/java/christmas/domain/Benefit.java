package christmas.domain;

import christmas.domain.constant.DiscountName;

public class Benefit {
    private DiscountName discountName;
    private Integer price;

    public Benefit(final DiscountName discountName, final Integer price) {
        this.discountName = discountName;
        this.price = price;
    }

    public Integer getPrice() {
        return this.price;
    }

    public DiscountName getDiscountName() {
        return this.discountName;
    }
}
