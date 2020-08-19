package model.commands;

// Model package imports
import model.interfaces.IShapeCommand;
import model.shape.Shape;

// Java lib imports
import java.util.List;

public class CopyCommand implements IShapeCommand {

    public List<Shape> selectedShapesList;
    public List<Shape> copiedShapesList;

    public CopyCommand(List<Shape> selectedShapesList, List<Shape> copiedShapesList){
        this.selectedShapesList = selectedShapesList;
        this.copiedShapesList = copiedShapesList;
    }

    @Override
    public void run() {
        if(selectedShapesList.size() > 0) {
            copy(selectedShapesList);
        }
    }

    private void copy(List<Shape> selectedShapesList){

        // Add all of the selected shapes to the copied shapes list
        copiedShapesList.addAll(selectedShapesList);

        // Clear list of selected shapes since operation is complete
        selectedShapesList.clear();

        // Print which shapes are copied currently to console
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("Shapes " + copiedShapesList + " are currently copied!");
        System.out.println("-----------------------------------------");
        System.out.println();
    }

}
