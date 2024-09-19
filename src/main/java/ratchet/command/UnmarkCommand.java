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
    private final Integer[] indexes;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param indexes Index of task to be unmarked.
     */
    public UnmarkCommand(Integer[] indexes) {
        this.indexes = indexes;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws InvalidCommandArgumentException {
        StringBuilder res = new StringBuilder("OK, I've marked these task as not done yet:");
        try {
            for (int index : indexes) {
                Task task = tasks.unmarkTask(index);
                res.append(ui.printWithSeparator(task.toString()));
            }
            return res.toString() + tasks.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandArgumentException("Please enter a valid task index!");
        }
    }
}
