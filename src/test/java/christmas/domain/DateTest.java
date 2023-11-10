package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @DisplayName("1~25일 사이인지 판단하는 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 11, 25})
    void isChristmastDiscountable(int day) {
        // given
        Date date = new Date(day);

        // when
        boolean isChristmasDiscountable = date.isChristmastDiscountable();

        // then
        assertEquals(isChristmasDiscountable, true);
    }

    @DisplayName("1~25일이 아닌지 판단하는 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 26, 27, 31})
    void 크리스마스_디데이_할인_아닌_날(int day) {
        // given
        Date date = new Date(day);

        // when
        boolean isChristmasDiscountable = date.isChristmastDiscountable();

        // then
        assertEquals(isChristmasDiscountable, false);
    }

    @DisplayName("크리스마스 디데이 할인 금액 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1, 1000", "2, 1100", "25, 3400"})
    void getDiscountPriceChristmas(int day, int expectdDiscountPrice) {
        // when
        Date date = new Date(day);

        // given
        int discountPrice = date.getDiscountPriceChristmas();

        // then
        assertEquals(discountPrice, expectdDiscountPrice);
    }

    @DisplayName("평일인지 판단하는 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1, false", "2, false", "3, true", "4, true", "10, true",})
    void isWeekday(Integer day, boolean expectedIsWeekday) {
        // when
        Date date = new Date(day);

        // given
        boolean isWeekday = date.isWeekday();

        // then
        assertEquals(isWeekday, expectedIsWeekday);
    }

    @DisplayName("주말인지 판단하는 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1, true", "2, true", "3, false", "4, false", "29, true",})
    void isWeekend(int day, boolean expected) {
        // when
        Date date = new Date(day);

        // given
        boolean isWeeknd = date.isWeekend();

        // then
        assertEquals(isWeeknd, expected);
    }
}