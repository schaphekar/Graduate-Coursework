package model.shape;

// Java lib imports
import java.awt.Point;

public class Shape {

    // Shape coordinates
    public Point startPoint;
    public Point endPoint;
    public int xMin, xMax, yMin, yMax;

    // Shape attributes
    public ShapeType shapeType;
    public ShapeColor primaryColor;
    public ShapeColor secondaryColor;
    public ShapeShadingType shadingType;

    // For triangles
    public int height, width;

    public Shape(ShapeType shapeType, Point startPoint, Point endPoint, ShapeColor primaryColor, ShapeColor secondaryColor, ShapeShadingType shadingType) {

        // This instance of each shape attribute
        this.shapeType = shapeType;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.shadingType = shadingType;

        // Determine min and max coordinates for draw() and fill() arguments
        xMin = Math.min(startPoint.x, endPoint.x);
        yMin = Math.min(startPoint.y, endPoint.y);
        xMax = Math.max(startPoint.x, endPoint.x);
        yMax = Math.max(startPoint.y, endPoint.y);

        // Height and width parameters for ellipse and rectangle
        width = xMax - xMin;
        height = yMax - yMin;
    }

    public boolean containsPoints(int x1, int y1, int x2, int y2){

        // Invisible box made by L -> R click-and-drag action encompasses the shape
        if (x1 <= startPoint.x && y1 <= startPoint.y && x2 >= endPoint.x && y2 >= endPoint.y) {
            return true;
        }

        // Invisible box made by R -> L click-and-drag encompasses the shape drawn L -> R
        if (x1 >= startPoint.x && y1 >= startPoint.y && x2 <= endPoint.x && y2 <= endPoint.y) {
            return true;
        }

        // Invisible box made by R -> L click-and-drag encompasses the shape drawn R -> L
        if (x1 >= startPoint.x && y1 <= startPoint.y && x2 <= endPoint.x && y2 >= endPoint.y) {
            return true;
        }

        // Case where clicking inside shape drawn R -> L
        if (x1 <= startPoint.x && y1 >= startPoint.y && x2 >= endPoint.x && y2 <= endPoint.y) {
            return true;
        }

        else {
            return false; }
    }

    // Callable methods for the various strategy classes to call in order to draw shapes
    public int getXMax(){
        return xMax;
    }
    public int getXMin(){
        return xMin;
    }
    public int getYMax() {
        return yMax;
    }
    public int getYMin(){
        return yMin;
    }

    // Callable methods to get height/width for drawing a triangle
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

}