package model.commands;

// Model package imports
import model.interfaces.IShapeCommand;
import model.interfaces.IUndoable;
import model.trackers.DrawShapeList;
import model.trackers.CommandHistory;
import model.shape.Shape;

// Java lib imports
import java.util.List;

public class DeleteCommand implements IShapeCommand, IUndoable {

    public DrawShapeList drawShapeList;
    public List<Shape> selectedShapesList;
    public List<Shape> deletedShapesList;

    public DeleteCommand(DrawShapeList drawShapeList, List<Shape> selectedShapesList, List<Shape> deletedShapesList) {
        this.drawShapeList = drawShapeList;
        this.selectedShapesList = selectedShapesList;
        this.deletedShapesList = deletedShapesList;
    }

    @Override
    public void run() {
        // Delete the selected shapes
        delete(selectedShapesList);
        System.out.println(deletedShapesList);

        // Repaint the canvas
        drawShapeList.drawShape.paintCanvas.repaint();
        drawShapeList.drawShape.draw(drawShapeList.masterShapeList);
        CommandHistory.add(this);
    }

    private void delete(List<Shape> selectedShapesList){

        // Add all of the selected shapes to the copied shapes list
        deletedShapesList.addAll(selectedShapesList);

        // Remove from the master list if the shape was selected
        for (Shape shape : selectedShapesList) {

            if (drawShapeList.masterShapeList.contains(shape)) {
                drawShapeList.masterShapeList.remove(shape);
                System.out.println("Shape " + shape + " deleted!");
            }
        }

        // Clear list of selected shapes since operation is complete
        selectedShapesList.clear();
    }

    @Override
    public void undo() {

        // If at least one shape has been deleted, retrieve from deleted shapes list
        if (deletedShapesList.size() > 0) {
            System.out.println("Undid previous delete!");
            drawShapeList.masterShapeList.addAll(deletedShapesList);
            drawShapeList.drawShape.paintCanvas.repaint();
            drawShapeList.drawShape.draw(drawShapeList.masterShapeList);
        }

        else {
            System.out.println("No shapes have been deleted to undo!");
        }
    }

    @Override
    public void redo () {

        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("Previously deleted shapes to re-delete: " + deletedShapesList);
        System.out.println("-----------------------------------------");
        System.out.println();

        for (Shape shape: deletedShapesList) {
            if (drawShapeList.masterShapeList.contains(shape)) {
                drawShapeList.masterShapeList.remove(shape);
                System.out.println("Deleted shape " + shape);

            }
        }

        System.out.println("Redid previous delete!");
        drawShapeList.drawShape.paintCanvas.repaint();
        drawShapeList.drawShape.draw(drawShapeList.masterShapeList);
    }

}