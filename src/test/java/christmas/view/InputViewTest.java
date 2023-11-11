package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.Splitter;
import christmas.domain.constant.MenuBoard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;


class InputViewTest {
    @AfterEach
    void closeConsole() {
        Console.close();
    }

    @DisplayName("날짜 입력 기능 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1, 1", "24, 24", "25, 25", "27, 27"})
    void inputDate(String input, int day) {
        // given
        System.setIn(createUserInput(input));
        Splitter splitter = new Splitter();
        InputView inputView = new InputView(splitter);

        // when
        Date date = inputView.inputDate();

        // then
        assertThat(date.getDay()).isEqualTo(day);
//        assertThatThrownBy(() -> inputView.inputDate())
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("숫자로 입력해야합니다.");
    }

    @DisplayName("메뉴 입력 기능 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"})
    void inputMenu(String input) {
        // given
        System.setIn(createUserInput(input));
        Splitter splitter = new Splitter();
        InputView inputView = new InputView(splitter);
        Date date = new Date(24);

        List<Menu> expected = List.of(
                new Menu(MenuBoard.T_BONE_STEAK, 1),
                new Menu(MenuBoard.BARBEQUE_RIP, 1),
                new Menu(MenuBoard.CHOCOLATE_CAKE, 2),
                new Menu(MenuBoard.ZERO_COKE, 1)
        );

        // when
        List<Menu> menus = inputView.inputMenu();

        // then
        assertThat(menus).isEqualTo(expected);
    }

    InputStream createUserInput(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }
}