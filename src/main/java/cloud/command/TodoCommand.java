package cloud.command;

import cloud.task.Todo;
import cloud.util.Storage;
import cloud.util.TaskList;
import cloud.util.Ui;

/**
 * Represents a command to add a todo task.
 * A <code>TodoCommand</code> object adds a todo task with a description to the task list.
 */
public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        storage.saveData(tasks);
        return ui.showAddedTask(tasks);
    }
}
