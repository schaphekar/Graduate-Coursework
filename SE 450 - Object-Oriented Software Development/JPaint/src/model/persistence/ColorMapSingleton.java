package model.persistence;

// Model package imports
import model.shape.ShapeColor;

// Java lib imports
import java.awt.Color;
import java.util.EnumMap;

public class ColorMapSingleton {

    public static EnumMap<ShapeColor, Color> colorMap;
    public ShapeColor shapeColor;

    // Private so that Singleton object can only be created inside Singleton class
    private ColorMapSingleton(ShapeColor shapeColor, EnumMap<ShapeColor, Color> colorMap) {

        this.shapeColor = shapeColor;
        ColorMapSingleton.colorMap = colorMap;
        ColorMapSingleton.colorMap.put(ShapeColor.BLACK, Color.BLACK);
        ColorMapSingleton.colorMap.put(ShapeColor.BLUE, Color.BLUE);
        ColorMapSingleton.colorMap.put(ShapeColor.CYAN, Color.CYAN);
        ColorMapSingleton.colorMap.put(ShapeColor.DARK_GRAY, Color.DARK_GRAY);
        ColorMapSingleton.colorMap.put(ShapeColor.GRAY, Color.GRAY);
        ColorMapSingleton.colorMap.put(ShapeColor.GREEN, Color.GREEN);
        ColorMapSingleton.colorMap.put(ShapeColor.LIGHT_GRAY, Color.LIGHT_GRAY);
        ColorMapSingleton.colorMap.put(ShapeColor.MAGENTA, Color.MAGENTA);
        ColorMapSingleton.colorMap.put(ShapeColor.ORANGE, Color.ORANGE);
        ColorMapSingleton.colorMap.put(ShapeColor.PINK, Color.PINK);
        ColorMapSingleton.colorMap.put(ShapeColor.RED, Color.RED);
        ColorMapSingleton.colorMap.put(ShapeColor.WHITE, Color.WHITE);
        ColorMapSingleton.colorMap.put(ShapeColor.YELLOW, Color.YELLOW);
    }

    public static ColorMapSingleton getInstance(ShapeColor shapeColor, EnumMap<ShapeColor, Color> colorMap){
        return new ColorMapSingleton(shapeColor, colorMap);
    }
}