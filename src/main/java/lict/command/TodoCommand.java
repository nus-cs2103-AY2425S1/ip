package lict.command;

import lict.LictException;
import lict.Storage;
import lict.TaskList;
import lict.Ui;
import lict.task.Task;
import lict.task.Todo;


/**
 * The {@code TodoCommand} class is responsible for handling the addition of a new todo task.
 * It creates a new {@code Todo} object and adds it to the task list, then updates the UI and storage.
 */
public class TodoCommand extends Command {
    private String description;

    /**
     * Constructs a {@code TodoCommand} with the specified task description.
     *
     * @param description The description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a new todo task.
     *
     * @param tasks    The task list to add the new todo task to.
     * @param ui       The UI to update with the added task.
     * @param storage  The storage to save the updated task list to.
     * @throws LictException If the description is empty or there is an issue saving the task.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LictException {
        if (description.isEmpty()) {
            throw new LictException(
                    """
                    OOPS!!! The description of a todo cannot be empty.
                    Please ensure that your input is in the format: todo {description}""");
        }
        Task newTask = new Todo(description);
        tasks.addTask(newTask);
        ui.hasAddedTask(newTask, tasks.size());
        storage.saveTasks(tasks);
    }
}
