package bangmang.command;

import bangmang.storage.Storage;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;
import bangmang.exception.InvalidCommandException;
import bangmang.tasks.Todo;

/**
 * Represents a command to add a todo task.
 */

public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Constructs an AddTodoCommand with the specified description.
     *
     * @param description A description of the todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        Todo todo = new Todo(description);
        tasks.add(todo);
        storage.save(tasks.getTasks());
        return ui.showAddedNewTask(todo, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
