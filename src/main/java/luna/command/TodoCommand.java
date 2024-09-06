package luna.command;

import luna.Storage;
import luna.TaskList;
import luna.task.Todo;

/**
 * Represents a command to add task without deadline to list of tasks.
 */
public class TodoCommand extends Command {
    private final Todo todo;

    public TodoCommand(Todo todo) {
        this.todo = todo;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.addTask(todo, storage);
    }
}
