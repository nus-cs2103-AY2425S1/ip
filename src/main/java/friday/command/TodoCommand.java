package friday.command;

import friday.task.Task;
import friday.task.TaskList;
import friday.task.Todo;
import friday.util.FridayException;
import friday.util.Storage;
import friday.util.Ui;

import java.io.IOException;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, FridayException {
        Task task = new Todo(description);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getSize());
        storage.saveTasks(tasks.getTasks());
    }
}
