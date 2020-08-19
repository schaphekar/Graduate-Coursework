package model.strategies;

// Model package imports
import model.shape.Shape;
import model.shape.ShapeColor;
import model.interfaces.IDrawShape;
import model.persistence.ColorMapSingleton;

// Java lib imports
import java.awt.*;
import java.util.EnumMap;

public class EllipseOutlineOnly implements IDrawShape {

    // Only need primary color for this strategy
    private final Shape shape;
    private final ShapeColor primaryColor;
    private final Graphics2D graphics;

    public EllipseOutlineOnly(Shape shape, ShapeColor primaryColor, Graphics2D graphics) {
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
        // Draw ellipse outline
        Color primaryColorMapped = EnumColorMap(primaryColor);
        graphics.setColor(primaryColorMapped);
        graphics.drawOval(shape.getXMin(), shape.getYMin(), shape.getWidth(), shape.getHeight());
    }

}
