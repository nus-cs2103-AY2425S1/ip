package bro.command;

import bro.task.Task;
import bro.task.TaskList;

public class CreateCommand implements Command {

    private final TaskList taskList;
    private final Task task;

    public CreateCommand(TaskList taskList, Task task) {
        this.taskList = taskList;
        this.task = task;
    }

    public void execute() {
        this.taskList.addTask(this.task);
    }

    public boolean isExit() {
        return false;
    }
}
