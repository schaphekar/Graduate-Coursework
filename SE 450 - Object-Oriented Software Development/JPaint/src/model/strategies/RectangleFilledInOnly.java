package model.strategies;

// Model package imports
import model.interfaces.IDrawShape;
import model.shape.Shape;
import model.shape.ShapeColor;
import model.persistence.ColorMapSingleton;

// Java lip imports
import java.awt.*;
import java.util.EnumMap;

public class RectangleFilledInOnly implements IDrawShape {

    // Only need primary color for this strategy
    private final ShapeColor primaryColor;
    private final Graphics2D graphics;
    private final Shape shape;

    public RectangleFilledInOnly(Shape shape, ShapeColor primaryColor, Graphics2D graphics) {
        this.shape = shape;
        this.primaryColor = primaryColor;
        this.graphics = graphics;
    }

    @Override
    public Color EnumColorMap(ShapeColor shapeColor) {
        EnumMap<ShapeColor,Color> colorMap = new EnumMap<>(ShapeColor.class);
        ColorMapSingleton colorMapSingleton = ColorMapSingleton.getInstance(shapeColor,colorMap);
        return ColorMapSingleton.colorMap.get(shapeColor);
    }

    @Override
    public void draw() {
        // Draw filled rectangle
        Color primaryColorMapped = EnumColorMap(primaryColor);
        graphics.setColor(primaryColorMapped);
        graphics.fillRect(shape.getXMin(), shape.getYMin(), shape.getWidth(), shape.getHeight());
    }

}