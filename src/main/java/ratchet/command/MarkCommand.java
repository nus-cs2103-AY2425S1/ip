package ratchet.command;

import ratchet.exception.InvalidCommandArgumentException;
import ratchet.storage.Storage;
import ratchet.task.Task;
import ratchet.task.TaskList;
import ratchet.ui.Ui;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final Integer[] indexes;

    /**
     * Constructor for MarkCommand.
     *
     * @param indexes Index of task to be marked.
     */
    public MarkCommand(Integer[] indexes) {
        this.indexes = indexes;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws InvalidCommandArgumentException {
        StringBuilder res = new StringBuilder("Nice! I've marked these task as done:");
        try {
            for (int index : indexes) {
                Task task = tasks.markTask(index);
                res.append(ui.printWithSeparator(task.toString()));
            }
            return res + ui.printWithSeparator(tasks.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandArgumentException("Please enter a valid task index!");
        }
    }
}
