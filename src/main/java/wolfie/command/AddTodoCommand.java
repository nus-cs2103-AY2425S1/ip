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

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, WolfieException {
        if (description.isEmpty()) {
            throw new WolfieException("The description of a todo cannot be empty.");
        }
        Task task = new Todo(description, false);
        tasks.add(task);
        storage.save(tasks);
        ui.showTaskAdded(task, tasks.size());
    }
}
