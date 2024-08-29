package moody.commands;

import moody.exceptions.InvalidCommandException;
import moody.storage.Storage;
import moody.tasks.Task;
import moody.tasks.TaskList;
import moody.tasks.Todo;
import moody.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to add a todo task to the task list.
 * This command takes a description from the user input, creates a new Todo task,
 * and then adds it to the task list. The task list is then saved to storage.
 */
public class AddTodoCommand extends Command {
    protected String description;

    /**
     * Creates an AddTodoCommand to add a Todo task with the specified user input.
     * The user input should contain the description of the todo task after the command keyword.
     *
     * @param userInput The user input containing the command keyword and task description.
     */
    public AddTodoCommand(String userInput) {
        this.description = userInput.substring(5).trim();
    }

    /**
     * Executes the command by adding a new Todo task to the task list,
     * showing the task added message, and saving the updated task list to storage.
     * If the description is empty, an InvalidCommandException is thrown.
     *
     * @param tasks The task list to which the new Todo task will be added.
     * @param ui The user interface for showing messages.
     * @param storage The storage where the updated task list will be saved.
     * @throws InvalidCommandException If the description of the todo task is missing.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        if (description.isEmpty()) {
            throw new InvalidCommandException("Error: Missing task description\n\nPlease use the following format: todo <description>");
        }
        Task task = new Todo(description);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        try {
            storage.save(tasks.toArrayList());
        } catch (IOException e) {
            ui.showError("Error: Unable to save tasks to file");
        }
    }
}
