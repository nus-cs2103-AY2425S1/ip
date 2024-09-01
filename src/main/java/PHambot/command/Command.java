package PHambot.command;

import PHambot.task.TaskList;

public abstract class Command {
    public abstract boolean executeCommand();
    protected static TaskList taskList;

    public static void setUserData(TaskList tasks) {
        taskList = tasks;
    }
    public void setTaskList(TaskList taskList) {
        Command.taskList = taskList;
    }
}
