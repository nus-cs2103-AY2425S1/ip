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

    public String execute(UI ui) {
        Task task = this.taskList.addTask(this.task);
        String response = ui.showCreateTaskSuccess(task, this.taskList.getNumberOfTask());
        assert !response.isEmpty();
        this.storage.writeToStorage(this.taskList.getTasks());
        return response;
    }

    public boolean isExit() {
        return false;
    }
}
