package model.commands;

// Model package imports
import model.interfaces.IShapeCommand;
import model.trackers.CommandHistory;

public class RedoCommand implements IShapeCommand {
    @Override
    public void run() {
        CommandHistory.redo();
    }
}