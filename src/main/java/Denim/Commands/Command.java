package Denim.Commands;

import Denim.Storage.TaskIO;
import Denim.TaskList;

public abstract class Command {
    public abstract CommandResult execute(TaskList taskList, TaskIO taskIO);

    public abstract boolean isExit();

}
