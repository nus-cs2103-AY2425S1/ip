package mittens.commands;

import mittens.storage.Storage;
import mittens.task.TaskList;
import mittens.ui.Ui;

/**
 * Represents a command that is obtained by parsing the user input.
 */
public abstract class Command {
    
    /** Whether the program should exit upon execution */
    protected boolean isExit;

    public Command() {
        this.isExit = false;
    }

    /**
     * Executes the command by interacting with the task list, UI, and storage.
     * 
     * @param tasks The running program's task list
     * @param ui The running program's UI
     * @param storage The running program's storage
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return this.isExit;
    }
}
