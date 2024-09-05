package barcus.command;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.tasklist.TaskList;
import barcus.ui.Ui;

/**
 * Command to mark item at pos
 */
public class MarkCommand extends Command {
    private int pos;

    /**
     * Constructor
     * @param pos index of item to mark
     */
    public MarkCommand(int pos) {
        this.pos = pos;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BarcusException {
        if (pos > 0 && pos <= tasks.getLength()) {
            // tasks.get(pos - 1).markDone();
            tasks.markTask(pos - 1);
            ui.talk("Good job! Have marked as done: " + tasks.getTaskString(pos - 1));
            output = "Good job! Have marked as done: " + tasks.getTaskString(pos - 1);
        } else {
            //ui.showError("please choose a number between 1 and " + tasks.getLength());
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
