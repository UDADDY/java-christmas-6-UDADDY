package christmas.view;

import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.constant.MenuBoard;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class OutputViewTest {

    final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    final PrintStream standardOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @DisplayName("주문 메뉴 출력 기능 테스트")
    @Test
    void printMenu() {
        // given
        OutputView outputView = new OutputView();
        Order order = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 10), // 540,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75_000
                new Menu(MenuBoard.ICECREAM, 2) // 10_000
        ), new Date(23));

        // when
        outputView.printMenu(order);

        // then
        assertThat("<주문 메뉴>\n" +
                "바비큐립 10개\n" +
                "샴페인 3개\n" +
                "아이스크림 2개").isEqualTo(outputStreamCaptor.toString().trim());
    }

    @DisplayName("할인 전 총금액 출력 기능 테스트")
    @Test
    void printTotalPrice() {
        // given
        OutputView outputView = new OutputView();
        Order order = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 10), // 540,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75_000
                new Menu(MenuBoard.ICECREAM, 2) // 10_000
        ), new Date(23));

        // when
        outputView.printTotalPrice(order);

        // then
        assertThat(outputStreamCaptor.toString().trim()).isEqualTo(
                "<할인 전 총주문 금액>\n" +
                        "625,000원");
    }

    @DisplayName("증정 메뉴 출력 기능 테스트")
    @Test
    void printGiveaway() {
        // given
        OutputView outputView = new OutputView();
        Order order = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 10), // 540,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75_000
                new Menu(MenuBoard.ICECREAM, 2) // 10_000
        ), new Date(23));

        // when
        outputView.printGiveaway(order);

        // then
        assertThat(outputStreamCaptor.toString().trim()).isEqualTo(
                "<증정 메뉴>\n" +
                        "샴페인 1개"
        );
    }

    @DisplayName("증정 메뉴 출력 기능 테스트(없음)")
    @Test
    void printNoGiveaway() {
        // given
        OutputView outputView = new OutputView();
        Order order = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 1), // 540,000
                new Menu(MenuBoard.CHAMPAGNE, 1), // 75_000
                new Menu(MenuBoard.ICECREAM, 1) // 10_000
        ), new Date(23));

        // when
        outputView.printGiveaway(order);

        // then
        assertThat(outputStreamCaptor.toString().trim()).isEqualTo(
                "<증정 메뉴>\n" +
                        "없음"
        );
    }
}