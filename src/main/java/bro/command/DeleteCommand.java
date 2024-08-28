package bro.command;

import bro.task.TaskList;

public class DeleteCommand implements Command {

    private final TaskList taskList;
    private final int taskId;

    public DeleteCommand(TaskList taskList, int taskId) {
        this.taskList = taskList;
        this.taskId = taskId;
    }

    public void execute() {
        this.taskList.deleteTask(taskId);
    }

    public boolean isExit() {
        return false;
    }
}
