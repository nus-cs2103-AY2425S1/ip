import java.util.ArrayList;

import luna.task.Task;
import luna.task.Todo;

public class TodoCommand extends Command {
    private final Todo todo;

    public TodoCommand(Todo todo) {
        this.todo = todo;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        ArrayList<Task> added = tasks.addTask(todo);
        storage.saveTasks(added);
    }
}
