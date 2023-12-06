package christmas.domain.constant;

public enum ChristmasDDay {
    MINIMUM(1),
    MAXIMUM(25);

    private Integer day;

    private ChristmasDDay(Integer day) {
        this.day = day;
    }

    public Integer getDay() {
        return this.day;
    }
}
