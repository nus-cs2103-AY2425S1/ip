package denim.commands;

import denim.TaskList;
import denim.storage.TaskIO;

public abstract class Command {
    public abstract CommandResult execute(TaskList taskList, TaskIO taskIO);

    public abstract boolean isExit();

}
