package bro.command;

import bro.storage.Storage;
import bro.task.Task;
import bro.task.TaskList;
import bro.ui.UI;

public class CreateCommand implements Command {

    private final TaskList taskList;
    private final Task task;
    private final Storage storage;

    public CreateCommand(TaskList taskList, Task task, Storage storage) {
        this.taskList = taskList;
        this.task = task;
        this.storage = storage;
    }

    public void execute(UI ui) {
        Task task = this.taskList.addTask(this.task);
        ui.showCreateTaskSuccess(task, this.taskList.getNumberOfTask());
        this.storage.writeToStorage(this.taskList.getTasks());
    }

    public boolean isExit() {
        return false;
    }
}
