package king.commands;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Creates a DeleteCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be deleted, provided as a string.
     */
    public DeleteCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex) - 1; // Convert to zero-based index
    }

    /**
     * Executes the delete command by removing the specified task from the task list,
     * saving the updated task list to storage, and displaying the updated task list.
     *
     * @param tasks The task list from which the task will be removed.
     * @param ui The user interface to display messages and the updated task list.
     * @param storage The storage to save the updated task list.
     * @throws KingException If the task index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KingException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.remove(taskIndex);
            storage.save(tasks.getTaskList());
            return ui.showTaskList(tasks);
        } else {
            return ui.showErrorAsString(new KingException("Enter a valid task number!"));
        }
    }

    /**
     * Indicates whether this command signals the application to exit.
     *
     * @return {@code false} as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
