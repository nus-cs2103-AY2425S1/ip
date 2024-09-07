package command;

import exception.KukiShinobuException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an abstract command that can be executed.
 * A command takes in a TaskList, Ui, and Storage to modify any of them as necessary.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param taskList The task list containing tasks to be modified.
     * @param ui       The user interface object for displaying output.
     * @param storage  The storage object for saving and loading tasks.
     * @throws KukiShinobuException If an error occurs during command execution.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws KukiShinobuException;

    /**
     * Returns whether the command is an exit command.
     *
     * @return False by default, as most commands do not terminate the program.
     */
    public boolean isExit() {
        return false;
    }
}
