package vinegar.command;

import vinegar.task.TaskList;
import vinegar.Validator;
import vinegar.VinegarException;
import vinegar.storage.Storage;
import vinegar.task.Task;
import vinegar.task.Todo;
import vinegar.ui.Ui;

import java.io.IOException;

/**
 * Command for adding a new Todo task.
 * <p>
 * The AddTodoCommand allows the user to add a new todo task to the task list. It checks
 * for valid input, creates the task, and saves it in the storage.
 */
public class AddTodoCommand extends Command {
    private String description;

    /**
     * Constructs an AddTodoCommand with the specified input parts.
     *
     * @param inputParts The command input, where inputParts[1] contains the task description.
     * @throws VinegarException If the input is invalid or missing a description.
     */
    public AddTodoCommand(String[] inputParts) throws VinegarException {
        Validator.validateParts(inputParts, 2, "Please specify the description of the todo task.");
        this.description = inputParts[1].trim();
    }

    /**
     * Executes the command to add a todo task to the task list.
     *
     * @param tasks   The task list to modify.
     * @param ui      The UI for displaying messages to the user.
     * @param storage The storage for saving the task.
     * @throws VinegarException If an error occurs during execution.
     * @throws IOException      If an error occurs while saving the task to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws VinegarException, IOException {
        Task todoTask = new Todo(description);
        tasks.addTask(todoTask);
        storage.save(tasks.getTasks());
        return ui.printTaskAdded(todoTask, tasks.size());
    }
}
