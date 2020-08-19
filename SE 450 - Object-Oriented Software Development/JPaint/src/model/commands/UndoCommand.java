package model.commands;

// Model package imports
import model.interfaces.IShapeCommand;
import model.trackers.CommandHistory;

public class UndoCommand implements IShapeCommand {

    @Override
    public void run(){
        CommandHistory.undo();
    }
}
