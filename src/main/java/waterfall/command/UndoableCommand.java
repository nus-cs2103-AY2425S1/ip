package waterfall.command;

import waterfall.Storage;
import waterfall.task.TaskList;

/**
 * Represents a Command object that can be undone.
 *
 * @author Wai Hong
 */
public abstract class UndoableCommand extends Command {
    public abstract void undo(TaskList tasks, Storage storage) throws Exception;
    public void addToUndoList(UndoableCommand command) {
        UndoCommand.addToUndoList(command);
    }
}
