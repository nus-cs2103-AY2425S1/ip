package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import task.Todo;

/**
 * Represents a command to add a ToDo task to the TaskList.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a TodoCommand with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the TodoCommand by adding a ToDo task to the TaskList, saving it to storage,
     * and displaying the task details to the user.
     *
     * @param tasks The TaskList to add the ToDo task to.
     * @param storage The Storage to save the ToDo task to.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Todo todo = tasks.addTodo(this.description);
        storage.appendToFile(todo.toFile());
        return Ui.showAddTodo(tasks);
    }
}
