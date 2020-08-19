package FactoryDemo;

public class ShapeFactory {
    private ShapeFactory() { }

    public static IShape getCircle() {
        return new Circle(1);
    }

    public static IShape getRectangle() {
        return new Rectangle(4);
    }

    public static IShape getTriangle(boolean isEquilateral) {
        return new Triangle(3, isEquilateral);
    }
}
