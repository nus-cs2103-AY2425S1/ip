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
    private static final String TASK_INDEX_NOT_A_NUMBER_ERROR_MESSAGE = "Task index must be a number.";
    private static final String TASK_INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE = "Task index is out of bounds.";
    private static final String FILE_SAVE_ERROR_MESSAGE = "Failed to save tasks to storage.";

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
        this.taskIndex = taskIndex.trim();
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
        int index;
        try {
            index = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e) {
            throw new ArtsException(TASK_INDEX_NOT_A_NUMBER_ERROR_MESSAGE);
        }

        if (index < 0 || index >= tasks.size()) {
            throw new ArtsException(TASK_INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE);
        }

        Task task = tasks.getTask(index);
        if (task == null) {
            throw new ArtsException("Task at the given index does not exist.");
        }

        task.markAsDone();

        try {
            storage.save(tasks.getTasks());
        } catch (Exception e) {
            throw new ArtsException(FILE_SAVE_ERROR_MESSAGE + " " + e.getMessage());
        }

        return String.format("Victory! 🌟 I've marked this task as complete:\n🎉 %s 🎉\n"
                + "You've leveled up, champion! Keep conquering those tasks! 🚀", task);
    }
}
