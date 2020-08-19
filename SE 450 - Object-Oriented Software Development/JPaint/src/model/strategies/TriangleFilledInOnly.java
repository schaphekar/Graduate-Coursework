package model.strategies;

// Model package imports
import model.persistence.ColorMapSingleton;
import model.shape.Shape;
import model.shape.ShapeColor;
import model.interfaces.IDrawShape;

// Java lib imports
import java.awt.*;
import java.util.EnumMap;

public class TriangleFilledInOnly implements IDrawShape {

    // Only need primary color for this strategy
    private final Shape shape;
    private final ShapeColor primaryColor;
    private final Graphics2D graphics;

    public TriangleFilledInOnly(Shape shape, ShapeColor primaryColor, Graphics2D graphics) {
        this.shape = shape;
        this.primaryColor = primaryColor;
        this.graphics = graphics;
    }

    @Override
    public Color EnumColorMap(ShapeColor shapeColor) {
        EnumMap<ShapeColor, Color> colorMap = new EnumMap<>(ShapeColor.class);
        ColorMapSingleton colorMapSingleton = ColorMapSingleton.getInstance(shapeColor,colorMap);
        return ColorMapSingleton.colorMap.get(shapeColor);
    }

    @Override
    public void draw() {
        // Consolidate coordinates into arrays to be used as arguments
        int[] x = {shape.getXMin(), shape.getXMax(), shape.getXMax()};
        int[] y = {shape.getYMin(), shape.getYMin(), shape.getYMax() };

        // Primary color for fill
        Color primaryColorMapped = EnumColorMap(primaryColor);

        // Draw filled-in triangle
        graphics.setColor(primaryColorMapped);
        graphics.fillPolygon(x, y, 3);
    }
}
