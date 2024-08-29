package src.commands;

import src.Storage;
import src.TaskList;
import src.Ui;

public abstract class Command {

    private boolean isExit = true;

    public Command(boolean isActive) {
        this.isExit = isExit;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return isExit;
    }
}
