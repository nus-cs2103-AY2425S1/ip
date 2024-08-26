package friday.command;

import friday.Storage;
import friday.task.Task;
import friday.TaskList;
import friday.Ui;

import java.io.IOException;

/**
 * Represents a command to mark a task as not done.
 * Inherits from the Command class and provides functionality to update the status of a task to not done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark a task as not done.
     * This method updates the task's status and then attempts to save the updated task list.
     *
     * @param tasks  The TaskList containing all tasks where the task's status will be updated.
     * @param ui     The Ui object for providing feedback to the user about the task status change.
     * @param storage The Storage object for saving the updated task list to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.unmarkTaskAsDone(index);
        if (task != null) {
            ui.showUnmarkedTask(task);
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