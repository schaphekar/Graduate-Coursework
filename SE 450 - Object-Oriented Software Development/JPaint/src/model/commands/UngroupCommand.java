package model.commands;

// Model package imports
import model.interfaces.IShapeCommand;
import model.interfaces.IUndoable;
import model.shape.Shape;

// Java lib imports
import java.util.List;

public class UngroupCommand implements IShapeCommand, IUndoable {
    public List<Shape> selectedShapesList;
    public List<Shape> groupedShapesList;

    public UngroupCommand(List<Shape> selectedShapesList, List<Shape> groupedShapesList){
        this.selectedShapesList = selectedShapesList;
        this.groupedShapesList = groupedShapesList;
    }

    @Override
    public void run() {
        ungroup(selectedShapesList);

    }

    public void ungroup(List<Shape> groupedShapesList) {
        groupedShapesList.clear();
    }

    public void undo() {

    }

    public void redo() {

    }

}