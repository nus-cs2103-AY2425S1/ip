package ratchet.command;

import ratchet.exception.InvalidCommandArgumentException;
import ratchet.storage.Storage;
import ratchet.task.Task;
import ratchet.task.TaskList;
import ratchet.ui.Ui;

/**
 * Command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param index Index of task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws InvalidCommandArgumentException {
        try {
            Task task = tasks.unmarkTask(index);
            return ui.printWithSeparator("OK, I've marked this task as not done yet:")
                    + ui.printWithSeparator(task.toString()) + ui.printWithSeparator(tasks.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandArgumentException("Please enter a valid task index!");
        }
    }
}
