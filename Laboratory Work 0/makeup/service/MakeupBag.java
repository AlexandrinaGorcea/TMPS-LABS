package makeup.service;

import makeup.service.application.MakeupApplication;
import makeup.model.MakeupProduct;

import java.util.ArrayList;
import java.util.List;

public class MakeupBag {
    private final List<MakeupProduct> products = new ArrayList<>();

    public void addProduct(MakeupProduct product) {
        products.add(product);
    }

    public void applyMakeup(MakeupApplication application) {
        products.forEach(application::apply);
    }
}