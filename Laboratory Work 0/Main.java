import makeup.service.MakeupBag;
import makeup.service.application.EyeshadowApplication;
import makeup.service.application.LipstickApplication;
import makeup.util.MakeupFactory;

public class Main {
    public static void main(String[] args) {
        MakeupBag makeupBag = new MakeupBag();

        makeupBag.addProduct(MakeupFactory.createLipstick("Ruby Red", 15.99));
        makeupBag.addProduct(MakeupFactory.createEyeshadow("Smoky Gray", 12.99));

        makeupBag.applyMakeup(new LipstickApplication());
        makeupBag.applyMakeup(new EyeshadowApplication());
    }
}