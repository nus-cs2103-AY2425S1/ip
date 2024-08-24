package Spongebob.command;

import Spongebob.storage.Storage;
import Spongebob.storage.TaskList;
import Spongebob.Ui;
import Spongebob.task.Task;

/**
 * Basic Command class for executing tasks
 */

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public abstract boolean isExit();

    public abstract String[] getArgs();
}
