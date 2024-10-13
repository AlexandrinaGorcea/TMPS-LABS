# SOLID Principles
## Author: Gorcea Alexandrina, FAF-223

## Objectives
* Demonstrate practical implementation of 2 SOLID principles


## Introduction
This project implements a Makeup Application System, demonstrating the application of two SOLID principles: Single Responsibility Principle (SRP) and Open-Closed Principle (OCP).

## Implementation

### Single Responsibility Principle (SRP)
Each class in the system has a single, well-defined responsibility:

- Product: Represents basic product attributes
- MakeupProduct: Extends Product with makeup-specific properties
- MakeupBag: Manages a collection of makeup products
- MakeupApplication: Defines the interface for applying makeup
- LipstickApplication and EyeshadowApplication: Implement specific makeup application techniques

### Open-Closed Principle (OCP)
The system is designed to be open for extension but closed for modification:

- New makeup product types can be added by extending the MakeupProduct class
- New application techniques can be implemented by creating new classes that implement the MakeupApplication interface
- The MakeupBag class can work with any new MakeupApplication without modifying its code



### Model Package

```java
public abstract class Product {
    // Base product attributes
}

public class MakeupProduct extends Product {
    public enum Category { LIPSTICK, EYESHADOW }
    private final Category category;
    // Constructor and getter
}
```

The `Product` class serves as a base for all products, while `MakeupProduct` extends it with makeup-specific properties.

### Service Package

```java
public interface MakeupApplication {
    void apply(MakeupProduct product);
}

public class LipstickApplication implements MakeupApplication {
    @Override
    public void apply(MakeupProduct product) {
        // Lipstick application logic
    }
}

public class EyeshadowApplication implements MakeupApplication {
    @Override
    public void apply(MakeupProduct product) {
        // Eyeshadow application logic
    }
}

public class MakeupBag {
    private final List<MakeupProduct> products = new ArrayList<>();

    public void addProduct(MakeupProduct product) {
        products.add(product);
    }

    public void applyMakeup(MakeupApplication application) {
        products.forEach(application::apply);
    }
}
```

The `MakeupApplication` interface defines the contract for makeup application techniques. `LipstickApplication` and `EyeshadowApplication` provide specific implementations. `MakeupBag` manages a collection of products and applies makeup using the provided application technique.

### Util Package

```java
public class MakeupFactory {
    public static MakeupProduct createLipstick(String name, double price) {
        return new MakeupProduct(name, price, MakeupProduct.Category.LIPSTICK);
    }

    public static MakeupProduct createEyeshadow(String name, double price) {
        return new MakeupProduct(name, price, MakeupProduct.Category.EYESHADOW);
    }
}
```

The `MakeupFactory` class demonstrates the Factory Method pattern, providing a centralized way to create `MakeupProduct` instances.

## Conclusions

To conclude , by incorporating SOLID principles like the Single Responsibility Principle (SRP) and Open/Closed Principle (OCP) into my project I have a cleaner, more maintainable code.
By applying SRP, I separated distinct responsibilities like product selection and application, making the code easier to manage and extend.
The use of OCP allowed the addition of new makeup products without altering existing code, fostering flexibility and scalability.