package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.domain.Splitter;
import christmas.domain.constant.MenuBoard;
import christmas.validator.InputValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;


class InputViewTest {
    private InputView inputView;

    @BeforeEach
    void setUp() {
        inputView = new InputView(new Splitter());
    }

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

        // when
        Date date = inputView.inputDate();

        // then
        assertThat(date.getDay()).isEqualTo(day);
    }

    @DisplayName("날짜 입력 시 숫자가 아닌 값이 들어오면 예외를 발생시킨다")
    @ParameterizedTest
    @ValueSource(strings = {"a, a19, 20bdd3"})
    void inputDateNotInteger(String input) {
        // given
        System.setIn(createUserInput(input));

        // when, then
        assertThatThrownBy(() -> inputView.inputDate())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("날짜 입력 시 1~31 사이 값이 아니면 예외를 발생시킨다")
    @ParameterizedTest
    @ValueSource(strings = {"31a, -1, 0, 32, 1000b"})
    void inputDateNotRange(String input) {
        // given
        System.setIn(createUserInput(input));

        // when, then
        assertThatThrownBy(() -> inputView.inputDate())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴 입력 기능 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"})
    void inputMenu(String input) {
        // given
        System.setIn(createUserInput(input));
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

    @DisplayName("메뉴판에 없는 메뉴를 입력하면 예외를 발생시킨다")
    @ParameterizedTest
    @ValueSource(strings = {"aaa-1,바비큐립-1,초코케이크-2,제로콜라-1"})
    void 메뉴판에_없는_메뉴_예외(String input) {
        // given
        System.setIn(createUserInput(input));

        // when, then
        assertThatThrownBy(() -> inputView.inputMenu())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    InputStream createUserInput(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }
}