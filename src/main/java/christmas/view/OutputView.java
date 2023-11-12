package christmas.view;

import christmas.domain.Benefit;
import christmas.domain.Menu;
import christmas.domain.Order;

import java.text.DecimalFormat;
import java.util.List;

public class OutputView {

    private DecimalFormat decimalFormat = new DecimalFormat("###,###");

    public void printMenu(final Order order) {
        System.out.println("<주문 메뉴>");

        List<Menu> menus = order.getMenus();
        for (Menu menu : menus) {
            System.out.println(menu.getName() + " " + menu.getCount() + "개");
        }
    }

    public void printTotalPrice(final Order order) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(decimalFormat.format(order.getTotalPrice()) + "원");
    }

    public void printGiveaway(final Order order) {
        System.out.println("<증정 메뉴>");
        if (!order.isGiveaway()) {
            System.out.println("없음");
            return;
        }

        System.out.println("샴페인 1개");
    }

    public void printBenefit(final Order order) {
        System.out.println("<혜택 내역>");
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

        System.out.println("<총혜택 금액>");
        if (order.getTotalBenefitPrice() == 0) {
            System.out.println("0원");
            return;
        }

        System.out.printf("-%s원", decimalFormat.format(order.getTotalBenefitPrice()));
    }

    public void printPriceAfterDiscount(final Order order) {
        final Integer priceAfterDiscount = order.getTotalPrice() - order.getTotalDiscountPrice();
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%s원", decimalFormat.format(priceAfterDiscount));
    }

    public void printEventBadge(final Order order) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(order.getEventBadge().getName());
    }
}
