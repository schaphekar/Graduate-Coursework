package model.commands;

// Model package imports
import model.interfaces.IShapeCommand;
import model.interfaces.IUndoable;
import model.shape.Shape;
import model.trackers.CommandHistory;

// Java lib imports
import java.util.List;

public class GroupCommand implements IShapeCommand, IUndoable {

    public List<Shape> selectedShapesList;
    public List<Shape> groupedShapesList;

    public GroupCommand(List<Shape> selectedShapesList, List<Shape> groupedShapesList){
        this.selectedShapesList = selectedShapesList;
        this.groupedShapesList = groupedShapesList;
    }

    @Override
    public void run() {
        if (selectedShapesList.size() > 0) {
            group(selectedShapesList);
        }

        CommandHistory.add(this);

    }

    public void group(List<Shape> selectedShapesList) {
        // Add all of the selected shapes to the copied shapes list
        groupedShapesList.addAll(selectedShapesList);

        // Say which shapes are currently grouped
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("Shapes " + groupedShapesList + " are currently grouped!");
        System.out.println("-----------------------------------------");
        System.out.println();

    }

    public void undo() {
        // Remove all the shapes from the group
        groupedShapesList.clear();

        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("Undid shape grouping of " + selectedShapesList + " group!");
        System.out.println("-----------------------------------------");
        System.out.println();
    }

    public void redo() {
        // Add all of the selected shapes to the copied shapes list
        groupedShapesList.addAll(selectedShapesList);

        // Say which shapes are currently grouped
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("Shapes " + groupedShapesList + " were re-grouped!");
        System.out.println("-----------------------------------------");
        System.out.println();
    }
}
