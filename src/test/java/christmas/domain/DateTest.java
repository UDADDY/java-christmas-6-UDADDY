package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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
}