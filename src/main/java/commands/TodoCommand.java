package commands;

import storage.Storage;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public Task task;

    public TodoCommand(String description) {
        this.task = new Todo(description);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.showTaskAdded(task, taskList.size());
        storage.saveTasks(taskList);
    }
}
