package bro.command;

import bro.task.Task;
import bro.task.TaskList;
import bro.ui.UI;

public class CreateCommand implements Command {

    private final TaskList taskList;
    private final Task task;

    public CreateCommand(TaskList taskList, Task task) {
        this.taskList = taskList;
        this.task = task;
    }

    public void execute(UI ui) {
        Task task = this.taskList.addTask(this.task);
        ui.showCreateTaskSuccess(task, this.taskList.getNumberOfTask());
    }

    public boolean isExit() {
        return false;
    }
}
