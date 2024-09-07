package nugget.command;

import nugget.*;
import nugget.exception.InvalidTaskNumberException;
import nugget.exception.NuggetException;

/**
 * Represents a command to mark a task as completed in the task list.
 */
public class MarkTaskCommand implements Command {
    private int index;

    /**
     * Constructs a MarkTaskCommand with the specified index of the task to be marked.
     *
     * @param index The index of the task to be marked as completed.
     */
    public MarkTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark task command, marking the specified task as completed.
     *
     * @param tasks The list of tasks where the task will be marked as completed.
     * @param ui The user interface that displays messages to the user.
     * @param storage The storage that handles the saving of tasks.
     * @throws NuggetException If the provided task index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NuggetException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        tasks.markTask(index);
        storage.saveTasks(tasks.getTasks());
        ui.showMarkedTask(tasks.getTask(index));
    }
}
