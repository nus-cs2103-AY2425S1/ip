package commands;

import task.TaskList;

public abstract class Command {
    public abstract String execute(TaskList taskList);
}
