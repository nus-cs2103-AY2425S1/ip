package bro.command;

import bro.task.TaskList;

public class ReadCommand implements Command {

    private final TaskList taskList;

    public ReadCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    public void execute() {
        taskList.printAllTasks();
    }

    public boolean isExit() {
        return false;
    }
}
