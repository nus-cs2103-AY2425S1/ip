package rotodo.commands;

import rotodo.processes.Gui;
import rotodo.processes.Storage;
import rotodo.tasklist.TaskList;

/**
 * The Command class encapsulates a Command that
 * can be executed to perform an action.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public abstract class Command {
    /**
     * Executes the command based on initialised state. Action
     * will be applied to (one or more of) TaskList, Ui, and Storage.
     *
     * @param tasks list
     * @param gui
     * @param storage
     */
    public abstract void execute(TaskList tasks, Gui gui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
