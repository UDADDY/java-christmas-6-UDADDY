package christmas.domain.constant;

public enum Weekday {
    DAY3(3),
    DAY4(4),
    DAY5(5),
    DAY6(6),
    DAY7(7),
    DAY10(10),
    DAY11(11),
    DAY12(12),
    DAY13(13),
    DAY14(14),
    DAY17(17),
    DAY18(18),
    DAY19(19),
    DAY20(20),
    DAY21(21),
    DAY24(24),
    DAY25(25),
    DAY26(26),
    DAY27(27),
    DAY28(28),
    DAY31(31);

    private Integer day;

    private Weekday(Integer day) {
        this.day = day;
    }

    public Integer getDay() {
        return this.day;
    }

}
