package christmas.domain;

public class Date {

    private Integer day;

    public Date(Integer day) {
        this.day = day;
    }

    public boolean isChristmastDiscountable() {
        if (1 <= day && day <= 25) {
            return true;
        }
        return false;
    }
}
