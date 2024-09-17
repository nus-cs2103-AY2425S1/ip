package bangmang.command;

import bangmang.storage.Storage;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;
import bangmang.exception.InvalidCommandException;
import bangmang.exception.InvalidTaskFormatException;
import bangmang.tasks.Task;

/**
 * Represents a command to mark a specific task as done.
 */

public class MarkTaskCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a MarkTaskCommand with the specified task index.
     *
     * @param i The task number of the task in the list to be marked.
     */
    public MarkTaskCommand(int i) {
        this.taskIndex = i - 1;
    }

    /**
     * Executes the command to mark the specified task as done, saves the updated task list, and provides feedback.
     *
     * @param tasks The list of tasks where the specified task will be marked.
     * @param ui The UI instance for providing feedback.
     * @param storage The storage instance to save the updated task list.
     * @return A string indicating the task has been marked as done.
     * @throws InvalidCommandException If the task index is invalid or any other error occurs.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        try {
            Task task = tasks.get(this.taskIndex);
            tasks.markTask(this.taskIndex);
            storage.save(tasks.getTasks());
            return ui.showMarkedTask(task);

        } catch (InvalidTaskFormatException e) {
            throw new InvalidCommandException("Alamak, task number out of range. Please provide a valid task number.");
        }
    }

    /**
     * Returns false as this command does not terminate the application.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
