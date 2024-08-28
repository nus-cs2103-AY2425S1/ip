package bro.command;

import bro.task.Task;
import bro.task.TaskList;
import bro.ui.UI;

public class DeleteCommand implements Command {

    private final TaskList taskList;
    private final int taskId;

    public DeleteCommand(TaskList taskList, int taskId) {
        this.taskList = taskList;
        this.taskId = taskId;
    }

    public void execute(UI ui) {
        Task task = this.taskList.deleteTask(taskId);
        ui.showDeleteTaskSuccess(task);
    }

    public boolean isExit() {
        return false;
    }
}
