package friday.command;

import java.io.IOException;

import friday.task.Task;
import friday.util.Storage;
import friday.util.TaskList;
import friday.util.Ui;

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

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (index >= 0 && index < tasks.getSize()) {
            Task task = tasks.deleteTask(index);
            try {
                storage.save(tasks.getTasks());
            } catch (IOException e) {
                return ui.showError("An error occurred while saving the task.");
            }
            return ui.showDeletedTask(task, tasks.getSize());
        } else {
            return ui.showError("Task not found.");
        }
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
