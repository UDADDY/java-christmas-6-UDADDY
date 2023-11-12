package christmas.controller;

import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.view.InputView;
import christmas.view.OutputView;
import org.mockito.internal.matchers.Or;

import java.util.List;

public class MarketController {

    private final InputView inputView;
    private final OutputView outputView;

    public MarketController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Date date = inputView.inputDate();
        List<Menu> menus = inputView.inputMenu();
        Order order = new Order(menus, date);
        order.discountAll();
        outputView.printMenu(order);
        outputView.printTotalPrice(order);
        outputView.printGiveaway(order);
        outputView.printBenefit(order);
        outputView.printTotalBenefitPrice(order);
        outputView.printPriceAfterDiscount(order);
        outputView.printEventBadge(order);
    }
}
