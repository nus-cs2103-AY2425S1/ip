package bro.command;

import bro.storage.Storage;
import bro.task.Task;
import bro.task.TaskList;
import bro.ui.UI;

public class DeleteCommand implements Command {

    private final TaskList taskList;
    private final int taskId;
    private final Storage storage;

    public DeleteCommand(TaskList taskList, int taskId, Storage storage) {
        this.taskList = taskList;
        this.taskId = taskId;
        this.storage = storage;
    }

    public String execute(UI ui) {
        Task task = this.taskList.deleteTask(taskId);
        String response = ui.showDeleteTaskSuccess(task);
        assert !response.isEmpty();
        this.storage.writeToStorage(this.taskList.getTasks());
        return response;
    }

    public boolean isExit() {
        return false;
    }
}
