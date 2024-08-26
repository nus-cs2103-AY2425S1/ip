package friday.command;

import friday.Storage;
import friday.task.Task;
import friday.TaskList;
import friday.Ui;

import java.io.IOException;

/**
 * Represents a command to delete a task from the task list.
 * Inherits from the Command class and implements the execute method to remove a task by its index.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a DeleteCommand with the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete the task from the task list.
     * Removes the task at the specified index, shows the deleted task to the user, and saves the updated task list.
     * If the task is not found, an error message is displayed.
     *
     * @param tasks  The TaskList from which the task is to be deleted.
     * @param ui     The Ui object for displaying messages to the user.
     * @param storage The Storage object for saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.deleteTask(index);
        if (task != null) {
            ui.showDeletedTask(task, tasks.getSize());
            try {
                storage.save(tasks.getTasks());
            } catch (IOException e) {
                ui.showError("An error occurred while saving the task.");
            }
        } else {
            ui.showError("Task not found.");
        }
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false as this command does not terminate the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}