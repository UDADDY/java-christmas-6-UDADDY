package christmas.domain;

import christmas.domain.constant.EventBadge;
import christmas.domain.constant.MenuBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class OrderTest {
    @DisplayName("할인 적용 전 가격이 잘 나오는 지 테스트")
    @Test
    public void calculateBeforeDiscount() {
        Order order = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 2), // 108,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75,000
                new Menu(MenuBoard.CEASAR_SALAD, 4) // 32,000
        ), new Date(1));
        Integer totalPrice = order.calculatePrice(); // must be 215,000
        assertEquals(totalPrice, 215000);
    }

    @DisplayName("총 금액이 10,000원인지 판단하는 테스트")
    @Test
    void isDiscountable() {
        Order order = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 2), // 108,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75,000
                new Menu(MenuBoard.CEASAR_SALAD, 4) // 32,000
        ), new Date(1));

        Order order2 = new Order(List.of(
                new Menu(MenuBoard.ICECREAM, 1)
        ), new Date(1));

        assertEquals(order.isDiscountable(), true);
        assertEquals(order2.isDiscountable(), false);
    }

    @DisplayName("크리스마스 디데이 할인 적용하는 테스트")
    @Test
    void discountChristmas() {
        // given
        Order order = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 2), // 108,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75,000
                new Menu(MenuBoard.CEASAR_SALAD, 4) // 32,000
        ), new Date(11)); // 2,000원 할인

        // when
        order.discountChristmas();

        // then
        assertEquals(order.getTotalDiscountPrice(), 2_000);

    }

    @DisplayName("주문에 디저트 메뉴 개수 반환하는 기능 테스트")
    @Test
    void getCountDessert() {
        // given
        Order order = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 2), // 108,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75,000
                new Menu(MenuBoard.ICECREAM, 4) // 32,000
        ), new Date(11));

        Order order2 = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 2), // 108,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75,000
                new Menu(MenuBoard.CHOCOLATE_CAKE, 3),
                new Menu(MenuBoard.ICECREAM, 4) // 32,000
        ), new Date(11));

        // when
        Integer countDessert = order.getCountDessert();
        Integer countDessert2 = order2.getCountDessert();

        // then
        assertEquals(countDessert, 4);
        assertEquals(countDessert2, 7);
    }

    @DisplayName("주문에 메인 메뉴 개수 반환하는 기능 테스트")
    @Test
    void getCountMain() {
        // given
        Order order = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 2), // 108,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75,000
                new Menu(MenuBoard.ICECREAM, 4) // 32,000
        ), new Date(11));

        Order order2 = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 2), // 108,000
                new Menu(MenuBoard.T_BONE_STEAK, 3), // 75,000
                new Menu(MenuBoard.CEASAR_SALAD, 4) // 32,000
        ), new Date(11));

        // when
        Integer countMain = order.getCountMain();
        Integer countMain2 = order2.getCountMain();

        // then
        assertEquals(countMain, 2);
        assertEquals(countMain2, 5);
    }

    @DisplayName("평일이면 디저트 메뉴 할인하는 기능 테스트")
    @Test
    void discountWeekday() {
        // given
        Order weekdayOrder = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 2), // 108,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75,000
                new Menu(MenuBoard.ICECREAM, 4) // 10_000
        ), new Date(11));

        // when
        weekdayOrder.discountWeekday();

        // then
        assertEquals(weekdayOrder.getTotalDiscountPrice(), 4 * 2023);
    }

    @DisplayName("주말이면 메인 메뉴 할인하는 기능 테스트")
    @Test
    void discountWeekend() {
        // given
        Order weekendOrder = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 2), // 108,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75,000
                new Menu(MenuBoard.ICECREAM, 4) // 20_000
        ), new Date(15));

        // when
        weekendOrder.discountWeekend();

        // then
        assertEquals(weekendOrder.getTotalDiscountPrice(), 2 * 2023);
    }

    @DisplayName("스페셜 데이면 총주문 금액에서 1000원 할인하는 기능 테스트")
    @Test
    void discountSpecialDay() {
        // given
        Order speicalDayOrder = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 2), // 108,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75,000
                new Menu(MenuBoard.ICECREAM, 4) // 20_000
        ), new Date(10));

        // when
        speicalDayOrder.discountSpecialDay();

        // then
        assertEquals(speicalDayOrder.getTotalDiscountPrice(), 1_000);
    }

    @DisplayName("증정 이벤트 판단하는 기능 테스트")
    @Test
    void isGiveaway() {
        // given
        Order order = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 2), // 108,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75,000
                new Menu(MenuBoard.ICECREAM, 4) // 20_000
        ), new Date(10));


        Order orderNotGiveaway = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 2) // 108,000
        ), new Date(10));

        // when
        boolean isGiveaway = order.isGiveaway();
        boolean isNotGiveaway = orderNotGiveaway.isGiveaway();

        // then
        assertEquals(isGiveaway, true);
        assertEquals(isNotGiveaway, false);
    }

    @DisplayName("스페셜데이면 샴페인 제공하는 기능 테스트")
    @Test
    void provideChampagne() {
        // given
        Order giveawayOrder = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 2), // 108,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75,000
                new Menu(MenuBoard.ICECREAM, 4) // 20_000
        ), new Date(10));

        Order giveawayOrder2 = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 2), // 108,000
                new Menu(MenuBoard.CHOCOLATE_CAKE, 3), // 75,000
                new Menu(MenuBoard.ICECREAM, 4) // 20_000
        ), new Date(10));

        // when
        giveawayOrder.discountGiveaway();
        giveawayOrder2.discountGiveaway();

        // then
        assertEquals(giveawayOrder.getCountChampagne(), 4);
        assertEquals(giveawayOrder2.getCountChampagne(), 1);
    }

    @DisplayName("이벤트 배지(트리) 판단하는 기능 테스트")
    @Test
    void getEventBadge_트리() {
        // given
        Order order = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 2), // 108,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75_000
                new Menu(MenuBoard.ICECREAM, 4) // 20_000
        ), new Date(10));

        // when
        order.discountChristmas(); // 1_900
        order.discountWeekend(); // 0
        order.discountWeekday(); // 8_092
        order.discountSpecialDay(); // 1_000, totalDiscountPrice = 10_992

        // then
        assertEquals(order.getEventBadge(), EventBadge.TREE);
    }

    @DisplayName("이벤트 배지(별) 판단하는 기능 테스트")
    @Test
    void getEventBadge_스타() {
        // given
        Order order = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 2), // 108,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75_000
                new Menu(MenuBoard.ICECREAM, 2) // 20_000
        ), new Date(4));

        // when
        order.discountChristmas(); // 1_900
        order.discountWeekend(); // 0
        order.discountWeekday(); // 8_092
        order.discountSpecialDay(); // 1_000, totalDiscountPrice = 10_992

        // then
        assertEquals(order.getEventBadge(), EventBadge.STAR);
    }

    @DisplayName("이벤트 배지(산타) 판단하는 기능 테스트")
    @Test
    void getEventBadge_산타() {
        // given
        Order order = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 10), // 108,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75_000
                new Menu(MenuBoard.ICECREAM, 2) // 20_000
        ), new Date(23));

        // when
        order.discountChristmas(); // 1_900
        order.discountWeekend(); // 0
        order.discountWeekday(); // 8_092
        order.discountSpecialDay(); // 1_000, totalDiscountPrice = 10_992

        // then
        assertEquals(order.getEventBadge(), EventBadge.SANTA);
    }

    @DisplayName("메뉴가 중복되면 예외를 발생한다")
    @Test
    void 중복된_메뉴_입력() {
        // give, when, then
        assertThatThrownBy(() -> new Order(List.of(
                new Menu(MenuBoard.CHAMPAGNE, 1),
                new Menu(MenuBoard.CHAMPAGNE, 1),
                new Menu(MenuBoard.CHOCOLATE_CAKE, 1)
        ), new Date(3)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문에 메뉴 20개가 초과하면 예외를 발생한다")
    @Test
    void 초과_메뉴_개수() {
        // give, when, then
        assertThatThrownBy(() -> new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 35), // 108,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75_000
                new Menu(MenuBoard.ICECREAM, 2) // 20_000
        ), new Date(23)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문에 음료만 있으면 예외를 발생한다")
    @Test
    void 주문_음료만_예외() {
        // give, when, then
        assertThatThrownBy(() -> new Order(List.of(
                new Menu(MenuBoard.ZERO_COKE, 10), // 108,000
                new Menu(MenuBoard.CHAMPAGNE, 3)
        ), new Date(23)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

}