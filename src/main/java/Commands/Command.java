package Commands;

import Task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList taskList);
}
