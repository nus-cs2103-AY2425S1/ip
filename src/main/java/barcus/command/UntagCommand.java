package barcus.command;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.tasklist.TaskList;

/**
 * Command to remove a tag from a task at a specified position.
 */
public class UntagCommand extends Command {
    private int pos;
    private String tag;

    /**
     * Constructs an UntagCommand to remove a tag from a task at the specified position.
     *
     * @param pos the position of the task to untag, using 1-based indexing
     * @param tag the tag to remove from the task
     */
    public UntagCommand(int pos, String tag) {
        this.pos = pos;
        this.tag = tag;
        this.output = "";
    }

    /**
     * Executes the command by removing the specified tag from the task at the given position in the task list.
     *
     * @param tasks the task list containing the task to be untagged
     * @param storage the storage object (not used in this command)
     * @throws BarcusException if the specified position is out of range
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws BarcusException {
        if (!(pos > 0 && pos <= tasks.getLength())) {
            throw new BarcusException("please choose a number between 1 and " + tasks.getLength());
        }

        tasks.untagTask(pos - 1, tag);
        output += "Sureee, have untagged task:\n";
        output += tasks.getTaskString(pos - 1) + "\n";

    }

    /**
     * Returns whether this command causes the application to exit.
     *
     * @return false, as untagging a task does not cause the application to exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
