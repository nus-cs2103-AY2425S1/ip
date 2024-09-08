package noosy.commands;

import noosy.exception.NoosyException;
import noosy.storage.Storage;
import noosy.task.Task;
import noosy.task.TaskList;
import noosy.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 * This command deletes a task and updates the user interface and storage.
 */
public class DeleteCommand extends Command {

    /**
     * The index of the task in the task list to be deleted.
     */
    private final int index;

    /**
     * Constructs an DeleteCommand with the specified task index.
     *
     * @param index the index of the task to be deleted in the task list
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete the task at the specified index.
     * It removes the task from the list of tasks, displays this information to the user,
     * and saves the updated task list.
     *
     * @param tasks the task list containing the task to be deleted
     * @param ui the user interface to display feedback to the user
     * @param storage the storage system to save the updated task list
     * @throws NoosyException if the specified task index is out of bounds or unavailable
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoosyException {
        try {
            Task toDelete = tasks.get(this.index);
            tasks.remove(toDelete);
            ui.showDeleted(toDelete);
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new NoosyException("This task number is unavailable at the moment. \n " +
                    "Please try again later.");
        }
    }
}
