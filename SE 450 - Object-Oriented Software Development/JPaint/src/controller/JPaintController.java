package controller;

// Model package imports
import model.commands.*;
import model.interfaces.IApplicationState;
import model.shape.Shape;
import model.trackers.DrawShapeList;

// View package imports
import view.EventName;
import view.gui.PaintCanvas;
import view.interfaces.IUiModule;

// Java lib imports
import java.util.List;

public class JPaintController implements IJPaintController {

    public PaintCanvas paintCanvas;
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    public DrawShapeList drawShapeList;
    public List<Shape> masterShapeList;
    public List<Shape> selectedShapesList;
    public List<Shape> copiedShapesList;
    public List<Shape> deletedShapesList;
    public List<Shape> groupedShapesList;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, PaintCanvas paintCanvas, DrawShapeList drawShapeList, List<Shape> masterShapeList, List<Shape> selectedShapesList, List<Shape> copiedShapesList, List<Shape> deletedShapesList, List <Shape> groupedShapesList) {

        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.paintCanvas = paintCanvas;
        this.drawShapeList = drawShapeList;
        this.masterShapeList = masterShapeList;
        this.selectedShapesList = selectedShapesList;
        this.copiedShapesList = copiedShapesList;
        this.deletedShapesList = deletedShapesList;
        this.groupedShapesList = groupedShapesList;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {

        // Shape attributes
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveStartAndEndPointMode());

        // Copy & paste
        uiModule.addEvent(EventName.COPY, () -> new CopyCommand(selectedShapesList, copiedShapesList).run());
        uiModule.addEvent(EventName.PASTE, () -> new PasteCommand(copiedShapesList, drawShapeList).run());

        // Delete
        uiModule.addEvent(EventName.DELETE, () -> new DeleteCommand(drawShapeList, selectedShapesList, deletedShapesList).run());

        // Undo & redo
        uiModule.addEvent(EventName.UNDO, () -> new UndoCommand().run());
        uiModule.addEvent(EventName.REDO, () -> new RedoCommand().run());

        // Group & ungroup
        uiModule.addEvent(EventName.GROUP, () -> new GroupCommand(selectedShapesList, groupedShapesList).run());
        uiModule.addEvent(EventName.UNGROUP, () -> new UngroupCommand(selectedShapesList, groupedShapesList).run());
    }
}