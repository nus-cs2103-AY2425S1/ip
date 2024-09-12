package waterfall.command;

import java.io.IOException;

import waterfall.Storage;
import waterfall.Ui;
import waterfall.WaterfallException;
import waterfall.task.TaskList;

/**
 * Represents the <code>Command</code> object to unmark a <code>Task</code>
 * in the data storage and alerts the user.
 *
 * @author Wai Hong
 */

public class UnmarkCommand extends UndoableCommand {
    private final int index;

    /**
     * Constructs a command object to unmark task at specified index.
     * @param index index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WaterfallException, IOException {
        if (!tasks.checkIndex(index)) {
            throw new WaterfallException("Why are you trying to edit a waterfall waterfall.task that does not exist?");
        }
        tasks.setDone(index, false);
        storage.updateTask(tasks.getTasks());
        addToUndoList(this);
        return ui.showUnmarkMessage(tasks.getTask(index));
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void undo(TaskList tasks, Storage storage) throws IOException {
        tasks.setDone(index, true);
        storage.updateTask(tasks.getTasks());
    }
}
