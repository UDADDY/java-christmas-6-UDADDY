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
        OutputView outputView = new OutputView();
        Order order = new Order(List.of(
                new Menu(MenuBoard.BARBEQUE_RIP, 10), // 108,000
                new Menu(MenuBoard.CHAMPAGNE, 3), // 75_000
                new Menu(MenuBoard.ICECREAM, 2) // 20_000
        ), new Date(23));

        outputView.printMenu(order);

        assertThat("<주문 메뉴>\n" +
                "바비큐립 10개\n" +
                "샴페인 3개\n" +
                "아이스크림 2개").isEqualTo(outputStreamCaptor.toString().trim());
    }
}