package rotodo.commands;

import rotodo.exception.InvalidInputException;
import rotodo.processes.Gui;
import rotodo.processes.Storage;
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
    private boolean asDone;

    /**
     * Initialise MarkCommand to be executed. Accepts
     * a task index and a status to mark as.
     *
     * @param i task index
     * @param as status
     */
    public MarkCommand(int i, boolean asDone) {
        idx = i;
        this.asDone = asDone;
    }

    @Override
    public void execute(TaskList tl, Gui ui, Storage st) {
        assert ui != null;
        assert tl != null;
        try {
            if (asDone) {
                ui.addMessage(tl.markDone(idx));
            } else {
                ui.addMessage(tl.unmarkDone(idx));
            }
        } catch (InvalidInputException e) {
            ui.addMessage(e.toString());
        }
    }
}
