package model.strategies;

// Model package imports
import model.shape.Shape;
import model.shape.ShapeColor;
import model.interfaces.IDrawShape;
import model.persistence.ColorMapSingleton;

// Java lib imports
import java.awt.*;
import java.util.EnumMap;

public class TriangleOutlineOnly implements IDrawShape {

    // Only need primary color for this strategy
    private final ShapeColor primaryColor;
    private final Graphics2D graphics;
    private final Shape shape;

    public TriangleOutlineOnly(Shape shape, ShapeColor primaryColor, Graphics2D graphics) {
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

        // Primary color for outline
        Color primaryColorMapped = EnumColorMap(primaryColor);

        // Draw triangle outline
        graphics.setColor(primaryColorMapped);
        graphics.drawPolygon(x, y, 3);
    }
}
