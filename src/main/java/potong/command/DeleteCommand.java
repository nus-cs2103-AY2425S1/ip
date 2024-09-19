package potong.command;

import potong.Storage;
import potong.TaskList;
import potong.Ui;
import potong.exceptions.IllegalInputPotongException;

/**
 * Represent the command for deleting tasks.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Create a delete command.
     *
     * @param command Index of task to be deleted.
     */
    public DeleteCommand(String command) {
        super(command);
        this.index = Integer.valueOf(command);
    }

    /**
     * Delete the task from the list of tasks.
     *
     * @param tasks List of tasks at hand.
     * @param storage Storage class for loading and saving.
     * @param ui Ui class for printing output.
     * @return String representation of the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        assert tasks != null;
        try {
            return tasks.deleteTask(this.index);
        } catch (IllegalInputPotongException e) {
            return e.getMessage();
        }
    }
}
