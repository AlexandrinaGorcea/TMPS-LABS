package makeup.service.application;
import makeup.model.MakeupProduct;
public class LipstickApplication implements MakeupApplication {
    @Override
    public void apply(MakeupProduct product) {
        if (product.getCategory() == MakeupProduct.Category.LIPSTICK) {
            System.out.println("Applying " + product.getName() + " lipstick");
        }
    }
}