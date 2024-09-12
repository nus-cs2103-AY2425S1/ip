package waterfall.command;

import java.util.Stack;

import waterfall.Storage;
import waterfall.Ui;
import waterfall.task.TaskList;

/**
 * Represents the undo <code>Command</code> object to revert the command.
 *
 * @author Wai Hong
 */
public class UndoCommand extends Command {
    private static Stack<UndoableCommand> undoStack = new Stack<>();

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (undoStack.isEmpty()) {
            return ui.getFailedUndoMessage();
        }
        try {
            UndoableCommand lastCommand = undoStack.pop();
            lastCommand.undo(tasks, storage);
            return ui.getUndoMessage(tasks);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds a task command to the stack of undo.
     * @param command Command to be added.
     */
    public static void addToUndoList(UndoableCommand command) {
        undoStack.push(command);
    }
}
