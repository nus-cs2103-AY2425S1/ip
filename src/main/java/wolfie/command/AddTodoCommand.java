package wolfie.command;

import java.io.IOException;

import wolfie.exception.WolfieException;
import wolfie.task.Task;
import wolfie.task.TaskList;
import wolfie.task.Todo;
import wolfie.util.Storage;
import wolfie.util.Ui;

/**
 * Represents a command to add a todos task to the task list.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Constructs an AddTodoCommand object to add a todos task to the task list.
     *
     * @param description The description of the todos task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the add todos command to add a todos task to the task list.
     *
     * @param tasks The task list to add the task to.
     * @param ui The user interface to display messages.
     * @param storage The storage to save the task list to.
     * @return The message to show the user.
     * @throws IOException If there is an error saving the task list.
     * @throws WolfieException If the description is empty.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, WolfieException {
        if (description.isEmpty()) {
            throw new WolfieException("The description of a todo cannot be empty.");
        }
        Task task = new Todo(description, false);
        boolean isAdded = tasks.add(task);
        if (isAdded) {
            storage.save(tasks);
            return ui.showTaskAdded(task, tasks.size());
        } else {
            return "Task already exists in the list. " + task.getDescription();
        }
    }
}
