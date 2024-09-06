package rotodo.commands;

import rotodo.exception.InvalidInputException;
import rotodo.processes.Storage;
import rotodo.processes.Gui;
import rotodo.tasklist.TaskList;

/**
 * The DeleteCommand class encapsulates the specific
 * type of Command that executes a delete action.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int i) {
        idx = i;
    }

    @Override
    public void execute(TaskList tl, Gui ui, Storage st) {
        try {
            ui.addMessage(tl.deleteTask(idx));
        } catch (InvalidInputException e) {
            ui.addMessage(e.toString());
        }
    }
}
