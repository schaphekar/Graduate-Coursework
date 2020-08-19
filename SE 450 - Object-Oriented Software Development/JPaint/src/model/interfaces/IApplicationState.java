package model.interfaces;

// Model package imports
import model.shape.ShapeColor;
import model.shape.ShapeShadingType;
import model.shape.ShapeType;
import model.shape.StartAndEndPointMode;

public interface IApplicationState {

    // Shape drawing settings
    void setActiveShape();
    void setActivePrimaryColor();
    void setActiveSecondaryColor();
    void setActiveShadingType();
    void setActiveStartAndEndPointMode();

    // Commands
    void CopyCommand();
    void PasteCommand();
    void DeleteCommand();
    void UndoCommand();
    void RedoCommand();
    void GroupCommand();
    void UngroupCommand();

    // Shape attributes
    ShapeType getActiveShapeType();
    ShapeColor getActivePrimaryColor();
    ShapeColor getActiveSecondaryColor();
    ShapeShadingType getActiveShapeShadingType();
    StartAndEndPointMode getActiveStartAndEndPointMode();

}
