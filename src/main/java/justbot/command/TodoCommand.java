package justbot.command;

import justbot.task.Todo;
import justbot.task.TaskList;
import justbot.ui.Ui;
import justbot.storage.Storage;

public class TodoCommand extends Command {
    private Todo todoTask;
    public TodoCommand(String description) {
        this.todoTask = new Todo(description.trim());
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.todoTask);
        ui.addTaskMessage(taskList, todoTask);
        storage.saveTasks(taskList);
    }
}
