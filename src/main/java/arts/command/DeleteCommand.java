package arts.command;

import arts.task.TaskList;
import arts.task.Task;
import arts.util.Storage;
import arts.util.Ui;
import arts.ArtsException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
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
    public void execute() throws ArtsException {
        try {
            int index = Integer.parseInt(taskIndex) - 1;
            Task task = tasks.removeTask(index);
            storage.save(tasks.getTasks());
            ui.showMessage("Noted. I've removed this task:\n " + task +
                    "\nNow you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new ArtsException("Invalid task index.");
        }
    }
}
