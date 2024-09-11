package arts.command;

import arts.ArtsException;
import arts.task.Task;
import arts.task.TaskList;
import arts.util.Storage;
import arts.util.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand implements Command {
    private static final String INVALID_TASK_INDEX_ERROR_MESSAGE = "Invalid task index.";

    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private final String taskIndex;

    /**
     * Constructs a MarkCommand with the specified task list, storage, UI, and task index.
     *
     * @param tasks The list of tasks.
     * @param storage The storage used to save tasks.
     * @param ui The user interface for displaying messages.
     * @param taskIndex The index of the task to be marked as done.
     */
    public MarkCommand(TaskList tasks, Storage storage, Ui ui, String taskIndex) {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";
        assert ui != null : "Ui cannot be null";
        assert taskIndex != null && !taskIndex.trim().isEmpty() : "Task index cannot be null or empty";

        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to mark a task as done. Parses the task index, marks the task
     * as done, saves the updated task list to storage, and displays a confirmation message.
     * Throws an exception if the task index is invalid.
     *
     * @throws ArtsException If the task index is invalid or cannot be parsed.
     */
    @Override
    public String execute() throws ArtsException {
        try {
            int index = Integer.parseInt(taskIndex) - 1;
            assert index >= 0 && index < tasks.size() : "Index must be within the valid range";

            Task task = tasks.getTask(index);
            assert task != null : "Task should not be null";

            task.markAsDone();
            storage.save(tasks.getTasks());

            return String.format("Nice! I've marked this task as done:\n %s", task);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            // Handle invalid task index or parsing error
            throw new ArtsException(INVALID_TASK_INDEX_ERROR_MESSAGE);
        }
    }
}
