package main;

// Controller package imports
import controller.IJPaintController;
import controller.JPaintController;
import controller.MouseListener;

// Model package imports
import model.persistence.ApplicationState;
import model.shape.Shape;
import model.shape.DrawShape;
import model.trackers.DrawShapeList;
import model.trackers.CommandLists;

// View package imports
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

// Java lib imports
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        PaintCanvas paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);

        // Tracking lists, including a master shape list
        List<Shape> masterShapeList = new ArrayList<>();
        List<Shape> drawnShapesList = new ArrayList<>();
        List<Shape> selectedShapesList = new ArrayList<>();
        List<Shape> copiedShapesList = new ArrayList<>();
        List<Shape> deletedShapesList = new ArrayList<>();
        List<Shape> groupedShapesList = new ArrayList<>();

        // New instances of drawShapeList and controller
        DrawShapeList drawShapeList = new DrawShapeList(new DrawShape(paintCanvas, null), masterShapeList);
        IJPaintController controller = new JPaintController(uiModule, appState, paintCanvas, drawShapeList, masterShapeList, selectedShapesList, copiedShapesList, deletedShapesList, groupedShapesList);

        // Concrete factory interface, which takes in appState and DrawShapeList
        CommandLists commandLists = new CommandLists(appState, drawShapeList, drawnShapesList, selectedShapesList, copiedShapesList, deletedShapesList, groupedShapesList);

        // New instance of MouseListener, which takes in shape factory
        MouseListener mouselistener = new MouseListener(commandLists);
        paintCanvas.addMouseListener(mouselistener);

        // Complete controller configuration
        controller.setup();
    }
}