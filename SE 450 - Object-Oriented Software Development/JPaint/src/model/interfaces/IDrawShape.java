package model.interfaces;

import model.shape.ShapeColor;
import java.awt.*;

public interface IDrawShape {
    Color EnumColorMap(ShapeColor shapeColor);
    void draw();
}
