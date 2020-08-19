package model.shape;

// Model imports
import model.interfaces.IDrawShape;
import model.strategies.*;

// View imports
import view.gui.PaintCanvas;

// Java lib imports
import java.awt.*;
import java.util.List;

public class DrawShape implements IDrawShape {

    public IDrawShape shapeStrategy;
    public PaintCanvas paintCanvas;

    public DrawShape(PaintCanvas paintCanvas, IDrawShape shapeStrategy){
        this.paintCanvas = paintCanvas;
        this.shapeStrategy = shapeStrategy;
    }

    public void draw(List<model.shape.Shape> masterShapeList) {
        Graphics2D graphics = paintCanvas.getGraphics2D();

        for(Shape shape: masterShapeList){

            // Drawing strategy for ellipses
            if (shape.shapeType.toString().equals("ELLIPSE")) {
                if (shape.shadingType.toString().equals("OUTLINE")) {
                    shapeStrategy = new EllipseOutlineOnly(shape, shape.primaryColor, graphics);
                }

                if (shape.shadingType.toString().equals("FILLED_IN")) {
                    shapeStrategy = new EllipseFilledInOnly(shape, graphics, shape.primaryColor);
                }

                if (shape.shadingType.toString().equals("OUTLINE_AND_FILLED_IN")) {
                    shapeStrategy = new EllipseOutlineAndFilledIn(shape, shape.primaryColor, shape.secondaryColor, graphics);
                }
            }

            // Drawing strategy for rectangles
            if (shape.shapeType.toString().equals("RECTANGLE")) {
                if (shape.shadingType.toString().equals("OUTLINE")) {
                    shapeStrategy = new RectangleOutlineOnly(shape, shape.primaryColor, graphics);
                }

                if (shape.shadingType.toString().equals("FILLED_IN")) {
                    shapeStrategy = new RectangleFilledInOnly(shape, shape.primaryColor, graphics);
                }

                if (shape.shadingType.toString().equals("OUTLINE_AND_FILLED_IN")) {
                    shapeStrategy = new RectangleOutlineAndFilledIn(shape, shape.primaryColor, shape.secondaryColor, graphics);
                }
            }

            // Drawing strategy for triangles
            if (shape.shapeType.toString().equals("TRIANGLE")) {
                if (shape.shadingType.toString().equals("OUTLINE")) {
                    shapeStrategy = new TriangleOutlineOnly(shape, shape.primaryColor, graphics);
                }

                if (shape.shadingType.toString().equals("FILLED_IN")) {
                    shapeStrategy = new TriangleFilledInOnly(shape, shape.primaryColor, graphics);
                }

                if (shape.shadingType.toString().equals("OUTLINE_AND_FILLED_IN")) {
                    shapeStrategy = new TriangleOutlineAndFilledIn(shape, shape.primaryColor, shape.secondaryColor, graphics);
                }
            }

            // Draw the shape
            shapeStrategy.draw();
        }

        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("Master shape list contains: " + masterShapeList + " in it.");
        System.out.println("Master shape list has length " + masterShapeList.size());
        System.out.println("-----------------------------------------");
        System.out.println();
    }

    @Override
    public Color EnumColorMap(ShapeColor shapeColor) {
        return null;
    }

    @Override
    public void draw() {

    }
}