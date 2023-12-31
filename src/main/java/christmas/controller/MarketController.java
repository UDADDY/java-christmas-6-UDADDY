package christmas.controller;

import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

public class MarketController {

    private final InputView inputView;
    private final OutputView outputView;

    public MarketController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Date date = inputDate();
        List<Menu> menus = inputMenu();
        Order order = new Order(menus, date);
        order.discountAll();
        printAll(order);
    }

    private Date inputDate() {
        while (true) {
            try {
                Date date = inputView.inputDate();
                return date;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<Menu> inputMenu() {
        while (true) {
            try {
                List<Menu> menus = inputView.inputMenu();
                return menus;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printAll(Order order) {
        outputView.printMenu(order);
        outputView.printTotalPrice(order);
        outputView.printGiveaway(order);
        outputView.printBenefit(order);
        outputView.printTotalBenefitPrice(order);
        outputView.printPriceAfterDiscount(order);
        outputView.printEventBadge(order);
    }
}
