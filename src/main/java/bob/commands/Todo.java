package bob.commands;

import bob.data.TaskList;
import bob.storage.Storage;
import bob.tasks.Task;
import bob.tasks.TodoTask;
import bob.ui.Ui;

/**
 * Class representing the todo command.
 */
public class Todo extends Command {
    private String description;
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
