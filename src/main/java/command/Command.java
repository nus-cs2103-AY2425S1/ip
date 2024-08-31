package command;

import task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList list);
}
