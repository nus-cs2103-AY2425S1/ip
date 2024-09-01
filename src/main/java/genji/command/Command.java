package genji.command;

import genji.task.TaskList;
import genji.Ui;
import genji.Storage;
public abstract class Command {
    public abstract void execute(TaskList list, Ui ui, Storage s);
    public abstract boolean isExit();
}
