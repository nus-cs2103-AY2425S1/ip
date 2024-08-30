package rotodo.commands;

import rotodo.processes.Storage;
import rotodo.processes.Ui;
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
     * @param tl tasklist
     * @param ui 
     * @param st storage
     */
    public abstract void execute(TaskList tl, Ui ui, Storage st);

    public boolean isExit() {
        return false;
    }
}
