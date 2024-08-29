package waterfall.command;

import java.io.IOException;

import waterfall.Storage;
import waterfall.Ui;
import waterfall.WaterfallException;
import waterfall.task.*;

/**
 * Represents the <code>Command</code> object to unmark a <code>Task</code>
 * in the data storage and alerts the user.
 *
 * @author Wai Hong
 */

public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs a command object to unmark task at specified index.
     * @param index
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws WaterfallException, IOException {
        if (!tasks.checkIndex(index)) {
            throw new WaterfallException("Why are you trying to edit a waterfall waterfall.task that does not exist?");
        }
        tasks.setDone(index, false);
        storage.updateTask(tasks.getTasks());
        ui.showUnmarkMessage(tasks.getTask(index));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
