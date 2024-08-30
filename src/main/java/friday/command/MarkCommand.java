package friday.command;

import java.io.IOException;

import friday.task.Task;
import friday.util.Storage;
import friday.util.TaskList;
import friday.util.Ui;

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

    @Override
    public boolean isExit() {
        return false;
    }
}
