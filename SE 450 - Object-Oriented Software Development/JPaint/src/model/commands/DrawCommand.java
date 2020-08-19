package model.commands;

// Model package imports
import model.interfaces.IShapeCommand;
import model.interfaces.IUndoable;
import model.shape.Shape;
import model.shape.ShapeType;
import model.trackers.CommandLists;
import model.trackers.CommandHistory;

// Java lib imports
import java.awt.*;

public class DrawCommand implements IShapeCommand, IUndoable {

    public Point startPoint;
    public Point endPoint;
    public ShapeType shapeType;
    public CommandLists commandLists;

    public DrawCommand(Point startPoint, Point endPoint, ShapeType shapeType, CommandLists commandLists) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.shapeType = shapeType;
        this.commandLists = commandLists;
    }

    @Override
    public void run() {
        // Run draw
        draw();

        // Add to command history for undo/redo
        CommandHistory.add(this);
    }

    public void draw() {
        // Create new instance of shape
        Shape shape = new Shape(shapeType, startPoint, endPoint, commandLists.appState.getActivePrimaryColor(), commandLists.appState.getActiveSecondaryColor(), commandLists.appState.getActiveShapeShadingType());

        // Add created shape to master and drawn shapes list
        commandLists.drawnShapesList.add(shape);
        commandLists.drawShapeList.masterShapeList.add(shape);

        // Draw the shape
        commandLists.drawShapeList.drawShape.draw(commandLists.drawShapeList.masterShapeList);
    }

    @Override
    public void undo(){

        // Remove most recently drawn shape from master list
        if (commandLists.drawnShapesList.size() > 0) {
            commandLists.drawShapeList.masterShapeList.remove(commandLists.drawShapeList.masterShapeList.size()-1);
            commandLists.drawShapeList.drawShape.paintCanvas.repaint();
            commandLists.drawShapeList.drawShape.draw(commandLists.drawShapeList.masterShapeList);

            // Print tracking lists to the console
            System.out.println();
            System.out.println("-----------------------------------------");
            System.out.println("Undid previous draw!");
            System.out.println("Drawn shapes: " + commandLists.drawnShapesList);
            System.out.println("Master list :" + commandLists.drawShapeList.masterShapeList);
            System.out.println("-----------------------------------------");
            System.out.println();
        }

        else {
            System.out.println();
            System.out.println("-----------------------------------------");
            System.out.println("No shapes drawn to undo!");
            System.out.println("-----------------------------------------");
            System.out.println();
        }
    }

    @Override
    public void redo(){

        // Re-add the undone shape to the master list
        commandLists.drawShapeList.masterShapeList.add(commandLists.drawnShapesList.get(commandLists.drawnShapesList.size()-1));

        // Repaint the canvas
        commandLists.drawShapeList.drawShape.paintCanvas.repaint();
        commandLists.drawShapeList.drawShape.draw(commandLists.drawShapeList.masterShapeList);

        // Print tracking lists to the console
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("Redid previous draw!");
        System.out.println("Drawn shapes: " + commandLists.drawnShapesList);
        System.out.println("Master list :" + commandLists.drawShapeList.masterShapeList);
        System.out.println("-----------------------------------------");
        System.out.println();
    }

}