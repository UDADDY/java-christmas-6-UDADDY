package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Date;
import christmas.domain.Splitter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;


class InputViewTest {
    @AfterEach
    void closeConsole() {
        Console.close();
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 1", "24, 24", "25, 25", "27, 27"})
    void inputDate(String str, int day) {
        System.setIn(createUserInput(str));

        Splitter splitter = new Splitter();
        InputView inputView = new InputView(splitter);
        Date date = inputView.inputDate();

        assertThat(date.getDay()).isEqualTo(day);

//        assertThatThrownBy(() -> inputView.inputDate())
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("숫자로 입력해야합니다.");
    }

    @Test
    void inputMenu() {
    }

    InputStream createUserInput(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }
}