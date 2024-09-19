package Naega.Command;

import Naega.NaegaException;
import Naega.Storage.Storage;
import Naega.Task.Task;
import Naega.Task.TaskList;
import Naega.Ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Creates a DeleteCommand with the specified task index.
     *
     * @param taskIndex the index of the task to be deleted (1-based index)
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1;
    }

    /**
     * Executes the command by deleting the task at the specified index, updating the UI, and saving the tasks to storage.
     *
     * @param tasks   the task list from which the task will be deleted
     * @param ui      the UI component to display information to the user
     * @param storage the storage component to save the updated task list
     * @return
     * @throws NaegaException if the task index is invalid
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NaegaException {
        try {
            Task taskToDelete = tasks.getTask(taskIndex);
            tasks.deleteTask(taskIndex);
            storage.save(tasks.getTasks());
            return ui.showDeletedTask(taskToDelete, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new NaegaException("Invalid task number.");
        }
    }
}