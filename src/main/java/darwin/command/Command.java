package darwin.command;

import darwin.exception.DarwinException;
import darwin.task.TaskManager;
import darwin.ui.Ui;

public abstract class Command {
    private String args;
    public abstract void execute(TaskManager taskManager, Ui ui) throws DarwinException;
    public boolean isExit() {
        return false;
    }
}
