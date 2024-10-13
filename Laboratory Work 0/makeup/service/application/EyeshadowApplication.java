package makeup.service.application;
import makeup.model.MakeupProduct;
public class EyeshadowApplication implements MakeupApplication {
    @Override
    public void apply(MakeupProduct product) {
        if (product.getCategory() == MakeupProduct.Category.EYESHADOW) {
            System.out.println("Applying " + product.getName() + " eyeshadow");
        }
    }
}