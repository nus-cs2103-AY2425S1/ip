package joe.command;

import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException;
    public boolean isExit() {
        return false;
    }

}
