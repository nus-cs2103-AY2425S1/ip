package User.Command;

import java.io.IOException;

import Tasks.TaskList;
import User.Ui;

abstract public class Command {
    private boolean isExit = false;

    Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(Ui ui, TaskList taskList) throws IOException;

    boolean isExit() {
        return this.isExit;
    }
}
