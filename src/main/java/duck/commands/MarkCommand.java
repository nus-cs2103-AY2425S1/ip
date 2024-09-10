package duck.commands;

import duck.common.Utils;
import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.storage.Storage;
import duck.ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 * When executed, it marks the specified task as completed and updates the storage.
 */
public class MarkCommand extends Command {

    private static final String ERROR_MESSAGE_MARK_COMMAND = """
            Update tasks with correct format please >:( Quack Quack!
            mark/unmark {index of task to update}
            """;

    /**
     * Constructs a MarkCommand with the specified message.
     *
     * @param message The message associated with the command, which should contain the task index to mark as done.
     */
    public MarkCommand(String message) {
        super(message);
    }

    /**
     * Executes the command by marking the specified task as done.
     *
     * @param tasks The list of tasks from which a task will be marked as done.
     * @param storage The storage system where the updated task list will be saved.
     * @param ui The user interface (not used in this command).
     * @throws DuckException If the input format is incorrect, the task index is invalid, or the index is out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        super.execute(tasks, storage, ui);

        if (!Utils.isCorrectUpdateFormat(message)) {
            throw new DuckException(ERROR_MESSAGE_MARK_COMMAND);
        }

        int taskIndex = Utils.getTaskIndex(message);
        tasks.updateTaskStatus(taskIndex, true);
        storage.writeTasks(tasks);
    }

    /**
     * Determines whether the command signifies an exit operation.
     *
     * @return false, as the MarkCommand does not signify an exit operation.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
