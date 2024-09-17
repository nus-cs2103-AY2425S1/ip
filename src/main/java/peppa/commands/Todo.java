package peppa.commands;

import peppa.data.TaskList;
import peppa.storage.Storage;
import peppa.tasks.Task;
import peppa.tasks.TodoTask;
import peppa.ui.Ui;

/**
 * Class representing the todo command.
 */
public class Todo extends Command {
    private final String description;
    public Todo(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        Task t = new TodoTask(description);
        list.add(t);
        return addTask(list, t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
