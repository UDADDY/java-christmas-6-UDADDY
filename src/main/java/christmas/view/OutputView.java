package christmas.view;

import christmas.domain.Benefit;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.constant.OutputMessage;

import java.text.DecimalFormat;
import java.util.List;

public class OutputView {

    private DecimalFormat decimalFormat;

    public OutputView(DecimalFormat decimalFormat) {
        this.decimalFormat = decimalFormat;
    }

    public void printMenu(final Order order) {
        System.out.println();
        System.out.println(OutputMessage.MENU.getMessage());

        List<Menu> menus = order.getMenus();
        for (Menu menu : menus) {
            System.out.println(menu.getName() + " " + menu.getCount() + "개");
        }
    }

    public void printTotalPrice(final Order order) {
        System.out.println();
        System.out.println(OutputMessage.TOTAL_PRICE.getMessage());
        System.out.println(decimalFormat.format(order.getTotalPrice()) + "원");
    }

    public void printGiveaway(final Order order) {
        System.out.println();
        System.out.println(OutputMessage.GIVEAWY.getMessage());
        if (!order.isGiveaway()) {
            System.out.println("없음");
            return;
        }

        System.out.println("샴페인 1개");
    }

    public void printBenefit(final Order order) {
        System.out.println();
        System.out.println(OutputMessage.BENEFIT.getMessage());
        if (order.getTotalBenefitPrice() == 0) {
            System.out.println("없음");
            return;
        }
        List<Benefit> benefits = order.getBenefits();
        for (Benefit benefit : benefits) {
            System.out.printf("%s: -%s원\n", benefit.getDiscountName().getName(), decimalFormat.format(benefit.getPrice()));
        }
    }

    public void printTotalBenefitPrice(final Order order) {
        List<Benefit> benefits = order.getBenefits();

        System.out.println();
        System.out.println(OutputMessage.TOTAL_BENEFIT_PRICE.getMessage());
        if (order.getTotalBenefitPrice() == 0) {
            System.out.println("0원");
            return;
        }

        System.out.printf("-%s원", decimalFormat.format(order.getTotalBenefitPrice()));
    }

    public void printPriceAfterDiscount(final Order order) {
        final Integer priceAfterDiscount = order.getTotalPrice() - order.getTotalDiscountPrice();
        System.out.println();
        System.out.println(OutputMessage.AFTER_DISCOUNT_PRICE.getMessage());
        System.out.printf("%s원", decimalFormat.format(priceAfterDiscount));
    }

    public void printEventBadge(final Order order) {
        System.out.println();
        System.out.println();
        System.out.println(OutputMessage.EVENT_BADGE.getMessage());
        System.out.println(order.getEventBadge().getName());
    }
}
