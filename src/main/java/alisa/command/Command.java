package alisa.command;

import alisa.AlisaException;
import alisa.Storage;
import alisa.task.TaskList;
import alisa.Ui;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws AlisaException;

    public abstract boolean isExit();

}
