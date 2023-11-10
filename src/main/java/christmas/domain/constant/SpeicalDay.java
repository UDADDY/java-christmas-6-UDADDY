package christmas.domain.constant;

public enum SpeicalDay {
    DAY3(3),
    DAY10(10),
    DAY17(17),
    DAY24(24),
    DAY25(25),
    DAY31(31);


    private Integer day;

    private SpeicalDay(Integer day) {
        this.day = day;
    }

    public Integer getDay() {
        return this.day;
    }

}
