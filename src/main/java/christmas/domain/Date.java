package christmas.domain;

import christmas.domain.constant.*;

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
        if (ChristmasDDay.MINIMUM.getDay() <= day && day <= ChristmasDDay.MAXIMUM.getDay()) {
            return true;
        }
        return false;
    }

    public Integer getDiscountPriceChristmas() {
        return CashUnit.CHRISTMAS_D_DAY_CRITERION.getUnit() + (day - 1) * CashUnit.CHRISTMAS_D_DAY_GROWTH.getUnit();
    }

    public boolean isWeekday() {
        List<Weekday> weekdays = new ArrayList<>(List.of(Weekday.values()));
        List<Integer> days = weekdays.stream().map(Weekday::getDay).toList();
        if (days.contains(day))
            return true;
        return false;
    }

    public boolean isWeekend() {
        List<Weekend> weekends = new ArrayList<>(List.of(Weekend.values()));
        List<Integer> days = weekends.stream().map(Weekend::getDay).toList();
        if (days.contains(day))
            return true;
        return false;
    }

    public boolean isSpecialDay() {
        List<SpeicalDay> speicalDays = new ArrayList<>(List.of(SpeicalDay.values()));
        List<Integer> days = speicalDays.stream().map(SpeicalDay::getDay).toList();
        if (days.contains(day))
            return true;
        return false;
    }

    public void validate(Integer day) {
        if (!(DAY_MINIMUM <= day && day <= DAY_MAXIMUM))
            throw new IllegalArgumentException();
    }


}
