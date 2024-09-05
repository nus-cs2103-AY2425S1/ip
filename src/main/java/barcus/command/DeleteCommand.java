package barcus.command;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.task.Task;
import barcus.tasklist.TaskList;
import barcus.ui.Ui;

/**
 * Command to delete item at pos
 */
public class DeleteCommand extends Command {
    private int pos;

    /**
     * Constructor
     * @param pos index of item to delete
     */
    public DeleteCommand(int pos) {
        this.pos = pos;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BarcusException {
        if (pos > 0 && pos <= tasks.getLength()) {
            //tasks.get(pos - 1).markDone();
            //talk("Good job! Have marked as done: " + tasks.get(pos - 1));
            Task temp = tasks.deleteTask(pos - 1);
            //curr--;
            ui.talk("Removed task: " + temp + "\nThere are "
                    + tasks.getLength() + " task(s) in the list.");
            output = "Removed task: " + temp + "\nThere are "
                    + tasks.getLength() + " task(s) in the list.";
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
