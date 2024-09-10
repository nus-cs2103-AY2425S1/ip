package commands;

import exceptions.DownyException;
import storage.Storage;
import tasks.TaskList;
import tasks.Todo;
import ui.Ui;

/**
 * The {@code TodoCommand} class represents a command that, when executed, adds a new
 * Todo task to the task list. The command also updates the storage and user interface
 * to reflect the addition of the new task.
 */
public class TodoCommand implements Command {

    private final String taskDescription;

    /**
     * Constructs a new {@code TodoCommand} with the specified task description.
     *
     * @param taskDescription The description of the task to be added.
     */
    public TodoCommand(String taskDescription) {
        assert taskDescription != null : "Task description cannot be null.";
        this.taskDescription = taskDescription;
    }

    /**
     * Executes the Todo command by adding a new Todo task to the task list, saving it
     * to storage, and displaying the added task via the user interface.
     *
     * @param storage The storage handler used for saving the new Todo task.
     * @param tasks   The list of tasks currently in memory.
     * @param ui      The UI handler used for interacting with the user.
     * @throws DownyException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws DownyException {
        Todo t = tasks.addTodo(taskDescription);
        storage.writeTodoToFile(t);
        return ui.displayTaskAdded(t, tasks.getSize());
    }

    /**
     * Returns the description of the task associated with this command.
     *
     * @return The task description.
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }
}
