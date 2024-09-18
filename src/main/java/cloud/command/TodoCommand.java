package cloud.command;

import cloud.task.Todo;
import cloud.util.Storage;
import cloud.util.TaskList;
import cloud.util.Ui;

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
