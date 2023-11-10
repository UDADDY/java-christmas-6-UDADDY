package christmas.domain.constant;

public enum Weekend {
    DAY1(1),
    DAY2(2),
    DAY8(8),
    DAY9(9),
    DAY15(15),
    DAY16(16),
    DAY22(22),
    DAY23(23),
    DAY29(29),
    DAY30(30);

    private Integer day;

    private Weekend(Integer day) {
        this.day = day;
    }

    public Integer getDay() {
        return this.day;
    }

}
