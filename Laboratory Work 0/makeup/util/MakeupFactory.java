package makeup.util;
import makeup.model.MakeupProduct;

public class MakeupFactory {
    public static MakeupProduct createLipstick(String name, double price) {
        return new MakeupProduct(name, price, MakeupProduct.Category.LIPSTICK);
    }

    public static MakeupProduct createEyeshadow(String name, double price) {
        return new MakeupProduct(name, price, MakeupProduct.Category.EYESHADOW);
    }
}