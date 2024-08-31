package spongebob.command;


import spongebob.storage.Storage;
import spongebob.storage.TaskList;
import spongebob.ui.Ui;


/**
 * Basic Command class for executing tasks
 */

public abstract class Command {

    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

    public abstract boolean isExit();

    public abstract String[] getArgs();
}
