package model.commands;

// Model package imports
import model.interfaces.IShapeCommand;
import model.interfaces.IUndoable;
import model.trackers.CommandHistory;
import model.trackers.DrawShapeList;
import model.shape.Shape;

// Java lib imports
import java.util.List;

public class PasteCommand implements IShapeCommand, IUndoable {

    public DrawShapeList drawShapeList;
    public List<Shape> copiedShapesList;

    public PasteCommand(List<Shape> copiedShapesList, DrawShapeList drawShapeList){
        this.copiedShapesList = copiedShapesList;
        this.drawShapeList = drawShapeList;
    }

    @Override
    public void run() {

        // Paste all selected shapes
        paste(copiedShapesList);
        System.out.println(copiedShapesList);

        // Redraw the canvas
        drawShapeList.drawShape.paintCanvas.repaint();
        drawShapeList.drawShape.draw(drawShapeList.masterShapeList);
        CommandHistory.add(this);
    }

    private void paste(List<Shape> copiedShapesList){

        // Pre-defined offset to ensure copy doesn't directly overlap original
        int offset = 100;

        for(Shape shape: copiedShapesList){

            // Paste the selected shapes' clones with an offset (left and upwards)
            shape.startPoint.x -= offset;
            shape.startPoint.y -= offset;
            shape.endPoint.x -= offset;
            shape.endPoint.y -= offset;

            // Create copied shape
            Shape copiedShape = new Shape(shape.shapeType, shape.startPoint, shape.endPoint, shape.primaryColor, shape.secondaryColor, shape.shadingType);

            // Add the new shape to the master list
            drawShapeList.masterShapeList.add(copiedShape);

        }

        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("Pasted " + copiedShapesList.size() + " shapes!");
        System.out.println("-----------------------------------------");
        System.out.println();
    }

    @Override
    public void undo() {

        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("Pasted " + copiedShapesList.size() + " shapes!");
        System.out.println("-----------------------------------------");
        System.out.println();

        // The number of shape copies is equal to the number of copied shapes, so remove from end of master list.
        for (int copysize = copiedShapesList.size(); copysize > 0; copysize = copysize - 1) {
            drawShapeList.masterShapeList.remove(drawShapeList.masterShapeList.size()-1);
        }

        // Redraw the canvas
        drawShapeList.drawShape.paintCanvas.repaint();
        drawShapeList.drawShape.draw(drawShapeList.masterShapeList);

    }

    @Override
    public void redo() {
        for(Shape shape: copiedShapesList){

            // Create copied shape
            Shape copiedShape = new Shape(shape.shapeType, shape.startPoint, shape.endPoint, shape.primaryColor, shape.secondaryColor, shape.shadingType);

            // Add the new shape to the master list
            drawShapeList.masterShapeList.add(copiedShape);
        }

        // Redraw the canvas
        drawShapeList.drawShape.paintCanvas.repaint();
        drawShapeList.drawShape.draw(drawShapeList.masterShapeList);

    }
}
