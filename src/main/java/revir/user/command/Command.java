package revir.user.command;

import java.io.IOException;
import revir.tasks.TaskList;
import revir.user.Ui;

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
