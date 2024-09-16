package nixy.command;

import nixy.exceptions.NixyException;
import nixy.ui.Ui;

/**
 * UndoCommand is a command that undoes the previous command.
 */
public class UndoCommand implements Command {
    private Ui ui;
    private UndoableCommand previousCommand;

    /**
     * Constructs an UndoCommand object.
     *
     * @param ui The Ui object to interact with the user.
     */
    public UndoCommand(Ui ui, UndoableCommand previousCommand) {
        this.ui = ui;
        this.previousCommand = previousCommand;
    }

    /**
     * Returns the type of the UndoCommand.
     *
     * @return CommandType.UNDO
     */
    @Override
    public CommandType getType() {
        return CommandType.UNDO;
    }

    /**
     * Executes the UndoCommand by undoing the previous command.
     */
    @Override
    public void execute() {
        if (previousCommand == null) {
            throw new NixyException("No previous undoable command to undo.");
        }
        previousCommand.undo();
        previousCommand = null;
        ui.showUndoMessage();
    }
}
