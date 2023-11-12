package christmas;

import christmas.controller.MarketController;
import christmas.domain.Splitter;
import christmas.validator.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.text.DecimalFormat;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(new Splitter(), new InputValidator());
        OutputView outputView = new OutputView(new DecimalFormat("###,###"));
        MarketController marketController = new MarketController(inputView, outputView);
        marketController.run();
    }
}
