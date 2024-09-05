package barcus.command;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.tasklist.TaskList;
import barcus.ui.Ui;

/**
 * Command to unmark item at the position
 */
public class UnmarkCommand extends Command {
    private int pos;

    /**
     * Constructor
     * @param pos index of item to unmark
     */
    public UnmarkCommand(int pos) {
        this.pos = pos;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BarcusException {
        if (pos > 0 && pos <= tasks.getLength()) {
            //tasks.get(pos - 1).unmarkDone();
            tasks.unmarkTask(pos - 1);
            ui.talk("No prob, have marked as undone: " + tasks.getTaskString(pos - 1));
            output = "No prob, have marked as undone: " + tasks.getTaskString(pos - 1);
        } else {
            throw new BarcusException("please choose a number between 1 and " + tasks.getLength());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        return output;
    }
}
