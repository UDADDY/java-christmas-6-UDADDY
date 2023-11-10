package christmas.domain;

import christmas.domain.constant.MenuBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    @DisplayName("할인 적용 전 가격이 잘 나오는 지 테스트")
    @Test
    public void calculateBeforeDiscount() {
        Order order = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 2), // 108,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75,000
                new Menu(MenuBoard.CEASAR_SALAD, 4) // 32,000
        ), new Date(1));
        Integer totalPrice = order.calculateBeforeDiscount(); // must be 215,000
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
    void containDessert() {
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

    @DisplayName("주문에 메인이 포함되어 있는지 판단하는 테스트")
    @Test
    void containMain() {
        // given
        Order order = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 2), // 108,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75,000
                new Menu(MenuBoard.ICECREAM, 4) // 32,000
        ), new Date(11));

        Order order2 = new Order(List.of(
                new Menu(MenuBoard.CHOCOLATE_CAKE, 2), // 108,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75,000
                new Menu(MenuBoard.CEASAR_SALAD, 4) // 32,000
        ), new Date(11));

        // when
        boolean isContainMain = order.containMain();
        boolean isContainMain2 = order2.containMain();

        // then
        assertEquals(isContainMain, true);
        assertEquals(isContainMain2, false);
    }
}