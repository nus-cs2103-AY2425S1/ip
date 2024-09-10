package arts.command;

import arts.ArtsException;
import arts.task.Task;
import arts.task.TaskList;
import arts.util.Storage;
import arts.util.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private static final String INVALID_TASK_INDEX_ERROR_MESSAGE = "Invalid task index.";

    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private final String taskIndex;

    /**
     * Constructs a DeleteCommand with the specified task list, storage, UI, and task index.
     *
     * @param tasks The list of tasks.
     * @param storage The storage used to save tasks.
     * @param ui The user interface for displaying messages.
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(TaskList tasks, Storage storage, Ui ui, String taskIndex) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to delete a task. Parses the task index, removes the task
     * from the task list, saves the updated task list to storage, and displays a
     * confirmation message. Throws an exception if the task index is invalid.
     *
     * @throws ArtsException If the task index is invalid or cannot be parsed.
     */
    @Override
    public String execute() throws ArtsException {
        try {
            int index = Integer.parseInt(taskIndex) - 1;
            Task task = tasks.removeTask(index);
            storage.save(tasks.getTasks());
            return String.format("Noted. I've removed this task:\n %s\nNow you have %d %s in the list.",
                    task, tasks.size(), tasks.size() == 1 ? "task" : "tasks");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            // Handle invalid task index or parsing error
            throw new ArtsException(INVALID_TASK_INDEX_ERROR_MESSAGE);
        }
    }
}
