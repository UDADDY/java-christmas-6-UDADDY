package christmas.domain;

import christmas.domain.constant.Weekday;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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


}
