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
    public abstract void execute(TaskList tl, Ui ui, Storage st);

    public boolean isExit() {
        return false;
    }
}
