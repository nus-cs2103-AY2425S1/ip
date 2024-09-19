package potong.command;

import potong.Storage;
import potong.TaskList;
import potong.Ui;
import potong.exceptions.IllegalInputPotongException;

/**
 * Represents the command to tag a task.
 */
public class UntagCommand extends Command {
    private int index;
    /**
     * Initialise a tag command
     *
     * @param command The general description of any command.
     */
    public UntagCommand(String command) {
        super(command);
        index = Integer.valueOf(command);
    }

    /**
     * Untags the command based on the given index.
     *
     * @param tasks List of tasks at hand.
     * @param storage Storage class for loading and saving.
     * @param ui Ui class for printing output.
     * @return String representation of untag.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            return tasks.untagTask(this.index);
        } catch (IllegalInputPotongException e) {
            return e.getMessage();
        }
    }
}
