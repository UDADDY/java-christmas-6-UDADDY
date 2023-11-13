package christmas.domain;

import christmas.domain.constant.SpeicalDay;
import christmas.domain.constant.Weekday;
import christmas.domain.constant.Weekend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Date {
    private static final Integer DAY_MINIMUM = 1;
    private static final Integer DAY_MAXIMUM = 31;
    private Integer day;

    public Date(Integer day) {
        validate(day);
        this.day = day;
    }

    public Integer getDay() {
        return this.day;
    }

    public boolean isChristmastDiscountable() {
        if (1 <= day && day <= 25) {
            return true;
        }
        return false;
    }

    public Integer getDiscountPriceChristmas() {
        return 1000 + (day - 1) * 100;
    }

    public boolean isWeekday() {
        List<Weekday> weekdays = new ArrayList<>(List.of(Weekday.values()));
        List<Integer> days = weekdays.stream().map(Weekday::getDay).collect(Collectors.toList());
        if (days.contains(day))
            return true;
        return false;
    }

    public boolean isWeekend() {
        List<Weekend> weekends = new ArrayList<>(List.of(Weekend.values()));
        List<Integer> days = weekends.stream().map(Weekend::getDay).collect(Collectors.toList());
        if (days.contains(day))
            return true;
        return false;
    }

    public boolean isSpecialDay() {
        List<SpeicalDay> speicalDays = new ArrayList<>(List.of(SpeicalDay.values()));
        List<Integer> days = speicalDays.stream().map(SpeicalDay::getDay).collect(Collectors.toList());
        if (days.contains(day))
            return true;
        return false;
    }

    public void validate(Integer day) {
        if (!(1 <= day && day <= 31))
            throw new IllegalArgumentException();
    }


}
