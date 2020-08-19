package model.commands;

// Model imports
import model.shape.Shape;
import model.trackers.CommandLists;
import model.interfaces.IShapeCommand;

// Java lib imports
import java.awt.*;
import java.util.List;

public class SelectCommand implements IShapeCommand {

    public Point startPoint;
    public Point endPoint;
    public CommandLists commandLists;

    // Start and end point coordinates of shapes
    private int x1, y1, x2, y2;

    public SelectCommand(Point startPoint, Point endPoint, CommandLists commandLists) {
        this.commandLists = commandLists;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public void run() {

        // Run select method
        select(commandLists.drawShapeList.masterShapeList);

        // Add to drawShape
        commandLists.drawShapeList.drawShape.draw(commandLists.drawShapeList.masterShapeList);
    }

    public void select(List<Shape> masterShapeList){

        // Start and end point coordinates of shapes
        x1 = startPoint.x;
        y1 = startPoint.y;
        x2 = endPoint.x;
        y2 = endPoint.y;

        // For every shape in the master list, select if within invisible box
        for (Shape shape: masterShapeList){
            if (shape.containsPoints(x1, y1, x2, y2)) {
                commandLists.selectedShapesList.add(shape);
            }
        }

        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println(commandLists.selectedShapesList.size() + " Shapes " + commandLists.selectedShapesList + " are currently selected!");
        System.out.println("-----------------------------------------");
        System.out.println();
    }
}