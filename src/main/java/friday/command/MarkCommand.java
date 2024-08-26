package friday.command;

import friday.Storage;
import friday.task.Task;
import friday.TaskList;
import friday.Ui;

import java.io.IOException;

/**
 * Represents a command to mark a task as done.
 * Inherits from the Command class and provides functionality to update the status of a specific task.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand with the specified index of the task to be marked as done.
     *
     * @param index The index of the task in the task list to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark the task at the specified index as done.
     * This method updates the task status, displays the updated task, and saves the changes to storage.
     *
     * @param tasks  The TaskList containing all tasks where the specified task will be marked.
     * @param ui     The Ui object for displaying the status of the updated task to the user.
     * @param storage The Storage object for saving the updated task list to the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.markTaskAsDone(index);
        if (task != null) {
            ui.showMarkedTask(task);
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