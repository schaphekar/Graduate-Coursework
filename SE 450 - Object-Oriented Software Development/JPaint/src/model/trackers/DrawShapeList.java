package model.trackers;

// Model imports
import model.shape.DrawShape;
import model.shape.Shape;

// Java util imports
import java.util.List;

public class DrawShapeList {
    public DrawShape drawShape;
    public List<Shape> masterShapeList;

    public DrawShapeList(DrawShape drawShape, List<Shape> masterShapeList) {
        this.drawShape = drawShape;
        this.masterShapeList = masterShapeList;
    }

}