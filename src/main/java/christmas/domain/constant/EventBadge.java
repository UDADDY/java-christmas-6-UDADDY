package christmas.domain.constant;

public enum EventBadge {
    NOTHING("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private String name;
    private Integer minimumStandard;

    private EventBadge(String name, Integer minimumStandard) {
        this.name = name;
        this.minimumStandard = minimumStandard;
    }

    public String getName() {
        return this.name;
    }

    public Integer getMinimumStandard() {
        return this.minimumStandard;
    }
}
