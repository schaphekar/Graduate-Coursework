package model.strategies;

// Model package imports
import model.shape.Shape;
import model.shape.ShapeColor;
import model.interfaces.IDrawShape;
import model.persistence.ColorMapSingleton;

// Java lib imports
import java.awt.*;
import java.util.EnumMap;

public class EllipseOutlineAndFilledIn implements IDrawShape {

    // Need both primary and secondary colors for this strategy
    private final Shape shape;
    private final ShapeColor primaryColor;
    private final ShapeColor secondaryColor;
    private final Graphics2D graphics;

    public EllipseOutlineAndFilledIn(Shape shape, ShapeColor primaryColor, ShapeColor secondaryColor, Graphics2D graphics) {
        this.shape = shape;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
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
        // First fill the ellipse with the primary color
        Color primaryColorMapped = EnumColorMap(primaryColor);
        graphics.setColor(primaryColorMapped);
        graphics.fillOval(shape.getXMin(), shape.getYMin(), shape.getWidth(), shape.getHeight());

        // Then enclose the ellipse with secondary color outline
        Color secondaryColorMapped = EnumColorMap(secondaryColor);
        graphics.setColor(secondaryColorMapped);
        graphics.drawOval(shape.getXMin(), shape.getYMin(), shape.getWidth(), shape.getHeight());
    }

}