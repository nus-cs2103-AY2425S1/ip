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
    private final int index;

    /**
     * Constructor for MarkCommand.
     *
     * @param index Index of task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws InvalidCommandArgumentException {
        try {
            Task task = tasks.markTask(index);
            return ui.printWithSeparator("Nice! I've marked this task as done:")
                    + ui.printWithSeparator(task.toString()) + ui.printWithSeparator(tasks.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandArgumentException("Please enter a valid task index!");
        }
    }
}
