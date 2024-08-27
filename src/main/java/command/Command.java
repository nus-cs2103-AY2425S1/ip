package command;

import exception.DarwinException;
import task.TaskManager;
import ui.Ui;

public abstract class Command {
    private String args;
    public abstract void execute(TaskManager taskManager, Ui ui) throws DarwinException;
    public boolean isExit() {
        return false;
    }
}
