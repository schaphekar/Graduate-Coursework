package model.strategies;

// Model package imports
import model.interfaces.IDrawShape;
import model.shape.Shape;
import model.shape.ShapeColor;
import model.persistence.ColorMapSingleton;

// Java library imports
import java.awt.*;
import java.util.EnumMap;

public class EllipseFilledInOnly implements IDrawShape {

    // Only need primary color for this strategy
    private final Shape shape;
    private final ShapeColor primaryColor;
    private final Graphics2D graphics;

    public EllipseFilledInOnly(Shape shape, Graphics2D graphics, ShapeColor primaryColor) {
        this.shape = shape;
        this.graphics = graphics;
        this.primaryColor = primaryColor;
    }

    @Override
    public Color EnumColorMap(ShapeColor shapeColor) {
        EnumMap<ShapeColor,Color> colorMap = new EnumMap<>(ShapeColor.class);
        ColorMapSingleton colorMapSingleton = ColorMapSingleton.getInstance(shapeColor,colorMap);
        return ColorMapSingleton.colorMap.get(shapeColor);
    }

    @Override
    public void draw() {
        // Draw filled-in ellipse
        Color primaryColorMapped = EnumColorMap(primaryColor);
        graphics.setColor(primaryColorMapped);
        graphics.fillOval(shape.getXMin(), shape.getYMin(), shape.getWidth(), shape.getHeight());
    }

}
