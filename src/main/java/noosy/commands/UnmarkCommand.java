package noosy.commands;

import noosy.exception.NoosyException;
import noosy.storage.Storage;
import noosy.task.Task;
import noosy.task.TaskList;
import noosy.ui.Ui;

/**
 * Represents a command to unmark a task as not done in the task list.
 * This command undoes the "done" status of a task at a specific index in the task list.
 */
public class UnmarkCommand extends Command {

    /**
     * The index of the task in the task list to be unmarked.
     */
    private final int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index the index of the task to be unmarked in the task list
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to unmark the task at the specified index.
     * It updates the task's status to "undone", displays this information to the user,
     * and saves the updated task list.
     *
     * @param tasks the task list containing the task to be unmarked
     * @param ui the user interface to display feedback to the user
     * @param storage the storage system to save the updated task list
     * @throws NoosyException if the specified task index is out of bounds or unavailable
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoosyException {
        try {
            Task undoneTask = tasks.get(this.index);
            undoneTask.unDone();
            ui.showUndone(undoneTask);
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new NoosyException("This task number is unavailable at the moment. \n " +
                    "Please try again later.");
        }
    }
}
