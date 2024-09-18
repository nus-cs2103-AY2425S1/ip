package skywalker.command;

import java.io.IOException;

import skywalker.storage.Storage;
import skywalker.task.TaskList;
import skywalker.ui.Ui;


/**
 * This gives a template for the commands
 */
public abstract class Command {
    /**
     * Executes the command. This method is implemented by
     * subclasses to define specific execution behavior
     *
     * @param tasks   The task list on which the command will operate.
     * @param ui      The UI object used to interact with the user.
     * @param storage The storage object used to save or load the task list.
     * @throws IOException If an I/O error occurs during command execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
    public boolean isExit() {
        return false;
    }
}
