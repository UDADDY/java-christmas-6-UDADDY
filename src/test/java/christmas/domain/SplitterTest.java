package christmas.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SplitterTest {
    @Test
    void test() {
        Splitter splitter = new Splitter();
        final List<Menu> menus = splitter.splitMenu("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        for (Menu menu : menus) {
            System.out.println(menu.toString());
        }

    }

}