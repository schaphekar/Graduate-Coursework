package model.trackers;

// Model package imports
import model.interfaces.IApplicationState;
import model.shape.Shape;

// Java lib imports
import java.util.List;

public class CommandLists {

    public DrawShapeList drawShapeList;
    public model.shape.Shape shape;
    public IApplicationState appState;
    public List<model.shape.Shape> drawnShapesList;
    public List<model.shape.Shape> selectedShapesList;
    public List<model.shape.Shape> copiedShapesList;
    public List<model.shape.Shape> deletedShapesList;
    public List<model.shape.Shape> groupedShapesList;

    public CommandLists(IApplicationState appState, DrawShapeList drawShapeList, List<model.shape.Shape> drawnShapesList, List<Shape> selectedShapesList, List<Shape> copiedShapesList, List<Shape> deletedShapesList, List<Shape> groupedShapesList) {
        this.appState = appState;
        this.drawShapeList = drawShapeList;
        this.drawnShapesList = drawnShapesList;
        this.selectedShapesList = selectedShapesList;
        this.copiedShapesList = copiedShapesList;
        this.deletedShapesList = deletedShapesList;
        this.groupedShapesList = groupedShapesList;
    }
}