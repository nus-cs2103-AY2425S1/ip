package potong.command;

import potong.exceptions.PotongException;

import potong.Storage;
import potong.TaskList;
import potong.Ui;

/**
 * Represents the command to mark or unmark a task.
 */
public class MarkCommand extends Command {

    private boolean toMark;
    private int index;

    /**
     * Initialise the command with the index of the task.
     *
     * @param command Index of the task.
     * @param toMark True to mark, False to unmark.
     */
    public MarkCommand(String command, boolean toMark) {
        super(command);
        this.toMark = toMark;
        this.index = Integer.valueOf(command);
    }

    /**
     * Marks or unmarks the task in the list of tasks.
     *
     * @param tasks List of tasks at hand.
     * @param storage Storage class for loading and saving.
     * @param ui Ui class for printing output.
     * @return String representation of mark or unmark.
     * @throws PotongException If the index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws PotongException {
        if (toMark) {
            return tasks.mark(this.index);
        } else {
            return tasks.unmark(this.index);
        }
    }
}
