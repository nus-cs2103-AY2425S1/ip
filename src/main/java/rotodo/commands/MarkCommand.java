package rotodo.commands;

import rotodo.exception.InvalidInputException;
import rotodo.processes.Storage;
import rotodo.processes.Ui;
import rotodo.tasklist.TaskList;

/**
 * The MarkCommand class encapsulates the specific
 * type of Command that (un)marks task done status.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class MarkCommand extends Command {
    private int idx;
    private boolean asStatus;

    /**
     * Initialise MarkCommand to be executed. Accepts
     * a task index and a status to mark as.
     *
     * @param i task index
     * @param as status
     */
    public MarkCommand(int i, boolean as) {
        idx = i;
        asStatus = as;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage st) {
        try {
            if (asStatus) {
                ui.addMessage(tl.markDone(idx));
            } else {
                ui.addMessage(tl.unmarkDone(idx));
            }
        } catch (InvalidInputException e) {
            ui.addMessage(e.toString());
        }
    }
}
