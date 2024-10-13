package makeup.model;
public class MakeupProduct extends Product {
    public enum Category {
        LIPSTICK, EYESHADOW
    }

    private final Category category;

    public MakeupProduct(String name, double price, Category category) {
        super(name, price);
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}