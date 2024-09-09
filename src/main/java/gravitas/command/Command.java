package gravitas.command;

import gravitas.exception.DukeException;
import gravitas.storage.Storage;
import gravitas.tasklist.TaskList;

/**
 * Abstract class for commands.
 */
public abstract class Command {

    private boolean isExit = false;

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract String executeCommand(TaskList taskList, Storage storage) throws DukeException;
}
