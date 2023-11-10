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
        ));
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
        ));

        Order order2 = new Order(List.of(
                new Menu(MenuBoard.ICECREAM, 1)
        ));

        assertEquals(order.isDiscountable(), true);
        assertEquals(order2.isDiscountable(), false);
    }
}