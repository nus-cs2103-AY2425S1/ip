package potong.command;

import potong.Storage;
import potong.TaskList;
import potong.Ui;
import potong.exceptions.IllegalInputPotongException;

/**
 * Represents the command to mark or unmark a task.
 */
public class MarkCommand extends Command {

    private boolean isMarked;
    private int index;

    /**
     * Initialise the command with the index of the task.
     *
     * @param command Index of the task.
     * @param isMarked True to mark, False to unmark.
     */
    public MarkCommand(String command, boolean isMarked) {
        super(command);
        this.isMarked = isMarked;
        this.index = Integer.valueOf(command);
    }

    /**
     * Marks or unmarks the task in the list of tasks.
     *
     * @param tasks List of tasks at hand.
     * @param storage Storage class for loading and saving.
     * @param ui Ui class for printing output.
     * @return String representation of mark or unmark.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        assert tasks != null;
        try {
            if (isMarked) {
                return tasks.markTask(this.index);
            } else {
                return tasks.unmarkTask(this.index);
            }
        } catch (IllegalInputPotongException e) {
            return e.getMessage();
        }
    }
}
