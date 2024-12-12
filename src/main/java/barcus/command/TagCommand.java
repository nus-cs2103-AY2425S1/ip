package barcus.command;

import barcus.exception.BarcusException;
import barcus.storage.Storage;
import barcus.tasklist.TaskList;

/**
 * Command to add a tag to a task at a specified position.
 */
public class TagCommand extends Command {
    private int pos;
    private String tag;

    /**
     * Constructs a TagCommand to tag a task at the specified position.
     *
     * @param pos the position of the task to tag, using 1-based indexing
     * @param tag the tag to add to the task
     */
    public TagCommand(int pos, String tag) {
        this.pos = pos;
        this.tag = tag;
        this.output = "";
    }

    /**
     * Executes the command by adding a tag to the task at the specified position in the task list.
     *
     * @param tasks the task list containing the task to be tagged
     * @param storage the storage object (not used in this command)
     * @throws BarcusException if the specified position is out of range
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws BarcusException {
        if (!(pos > 0 && pos <= tasks.getLength())) {
            throw new BarcusException("please choose a number between 1 and " + tasks.getLength());
        }

        tasks.tagTask(pos - 1, tag);
        output += "Cool beans, have tagged task:\n";
        output += tasks.getTaskString(pos - 1) + "\n";

    }

    /**
     * Returns whether this command causes the application to exit.
     *
     * @return false, as tagging a task does not cause the application to exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
