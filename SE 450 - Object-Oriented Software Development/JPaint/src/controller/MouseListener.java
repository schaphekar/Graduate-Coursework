package controller;

// Model imports
import model.interfaces.IShapeCommand;
import model.shape.ShapeType;
import model.shape.StartAndEndPointMode;
import model.trackers.CommandLists;
import model.commands.DrawCommand;
import model.commands.MoveCommand;
import model.commands.SelectCommand;

// Java UI/graphics + mouse events
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {

    // Shape attributes
    public Point startPoint;
    public Point endPoint;
    public ShapeType shapeType;
    public CommandLists commandLists;

    public MouseListener(CommandLists commandLists) {
        this.commandLists = commandLists;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        // Get coordinates at beginning of click-and-drag action
        startPoint = new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        // Get coordinates at end of click-and-drag action
        endPoint = new Point(e.getX(), e.getY());

        // Get the shape type from application state
        shapeType = commandLists.appState.getActiveShapeType();

        // Shape command initialization, holds null until paint mode is determined
        IShapeCommand shapeCommand = null;

        // Determine paint mode based on active start and end point mode dropdown selection
        if (commandLists.appState.getActiveStartAndEndPointMode() == StartAndEndPointMode.DRAW) {
            shapeCommand = new DrawCommand(startPoint, endPoint, shapeType, commandLists);
        }

        if (commandLists.appState.getActiveStartAndEndPointMode() == StartAndEndPointMode.SELECT) {
            shapeCommand = new SelectCommand(startPoint, endPoint, commandLists);
        }

        if (commandLists.appState.getActiveStartAndEndPointMode() == StartAndEndPointMode.MOVE){
            shapeCommand = new MoveCommand(startPoint, endPoint, shapeType, commandLists);
        }

        // Run the selected command
        shapeCommand.run();
    }
}
