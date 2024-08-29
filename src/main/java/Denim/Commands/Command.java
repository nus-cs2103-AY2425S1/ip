package denim.commands;

import denim.TaskList;
import denim.storage.TaskIo;

public abstract class Command {
    public abstract CommandResult execute(TaskList taskList, TaskIo taskIO);

    public abstract boolean isExit();

}
