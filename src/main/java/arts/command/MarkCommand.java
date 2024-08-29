package arts.command;

import arts.task.TaskList;
import arts.util.Storage;
import arts.util.Ui;
import arts.ArtsException;
import arts.task.Task;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand implements Command {
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
    public void execute() throws ArtsException {
        try {
            int index = Integer.parseInt(taskIndex) - 1;
            Task task = tasks.getTask(index);
            task.markAsDone();
            storage.save(tasks.getTasks());
            ui.showMessage("Nice! I've marked this task as done:\n " + task);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new ArtsException("Invalid task index.");
        }
    }
}
