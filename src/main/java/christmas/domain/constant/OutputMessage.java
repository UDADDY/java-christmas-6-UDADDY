package christmas.domain.constant;

public enum OutputMessage {
    MENU("<주문 메뉴>"),
    TOTAL_PRICE("<할인 전 총주문 금액>"),
    GIVEAWY("<증정 메뉴>"),
    BENEFIT("<혜택 내역>"),
    TOTAL_BENEFIT_PRICE("<총혜택 금액>"),
    AFTER_DISCOUNT_PRICE("<할인 후 예상 결제 금액>"),
    EVENT_BADGE("<12월 이벤트 배지>");

    private String message;

    private OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
