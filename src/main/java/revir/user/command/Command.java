package revir.user.command;

import java.io.IOException;

import revir.tasks.TaskList;
import revir.user.ui.Ui;

/**
 * Represents a command that can be executed by the user.
 * Subclasses of this class should implement the execute method to perform specific operations.
 */
public abstract class Command {
    private boolean isExit = false;

    Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the command.
     *
     * @param ui The user interface to interact with the user.
     * @param taskList The list of tasks to perform operations on.
     * @throws IOException If an I/O error occurs.
     */
    public abstract void execute(Ui ui, TaskList taskList) throws IOException;

    public boolean isExit() {
        return this.isExit;
    }
}
