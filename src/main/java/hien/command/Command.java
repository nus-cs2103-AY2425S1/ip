package hien.command;

import hien.exception.HienException;
import hien.main.Storage;
import hien.main.TaskList;
import hien.ui.UI;

public abstract class Command {
    private boolean isExit = false;
    public Command(boolean isExit) {
        this.isExit = isExit;
    }
    public abstract String execute(TaskList tasks, UI ui, Storage storage) throws HienException;
    public boolean isExit() {
        return isExit;
    }

}