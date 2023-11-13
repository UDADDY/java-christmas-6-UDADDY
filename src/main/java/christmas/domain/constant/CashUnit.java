package christmas.domain.constant;

public enum CashUnit {
    CHRISTMAS_D_DAY_CRITERION(1000),
    CHRISTMAS_D_DAY_GROWTH(100);

    private Integer unit;

    private CashUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getUnit() {
        return this.unit;
    }
}
