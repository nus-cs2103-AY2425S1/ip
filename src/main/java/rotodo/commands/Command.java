package rotodo.commands;

import rotodo.processes.Storage;
import rotodo.processes.Ui;
import rotodo.tasklist.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tl, Ui ui, Storage st);
    
    public boolean isExit() {
        return false;
    }
}
