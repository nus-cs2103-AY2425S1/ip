package arts.command;

import arts.task.TaskList;
import arts.task.Todo;
import arts.util.Storage;
import arts.util.Ui;
import arts.ArtsException;

/**
 * Represents a command to add a todo task to the task list.
 */
public class AddTodoCommand implements Command {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private final String description;

    /**
     * Constructs an AddTodoCommand with the specified task list, storage, UI, and task description.
     *
     * @param tasks The list of tasks.
     * @param storage The storage used to save tasks.
     * @param ui The user interface for displaying messages.
     * @param description The description of the todo task to be added.
     */
    public AddTodoCommand(TaskList tasks, Storage storage, Ui ui, String description) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
        this.description = description;
    }

    /**
     * Executes the command to add a todo task. Checks the task description for validity,
     * adds the task to the task list, saves the updated task list to storage, and displays
     * a confirmation message.
     *
     * @throws ArtsException If the task description is empty or invalid.
     */
    @Override
    public void execute() throws ArtsException {
        if (description == null || description.trim().isEmpty()) {
            throw new ArtsException("The description of a todo cannot be empty.");
        }
        tasks.addTask(new Todo(description));
        storage.save(tasks.getTasks());
        ui.showMessage("Got it. I've added this task:\n " + tasks.getTask(tasks.size() - 1) +
                "\nNow you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
    }
}
