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
    private int index;
    private boolean asDone;

    /**
     * Initialise MarkCommand to be executed. Accepts
     * a task index and a status to mark as.
     *
     * @param index of task to mark
     * @param asDone status
     */
    public MarkCommand(int index, boolean asDone) {
        this.index = index;
        this.asDone = asDone;
    }

    @Override
    public void execute(TaskList tasks, Gui gui, Storage storage) {
        assert gui != null;
        assert tasks != null;
        try {
            if (asDone) {
                gui.addMessage(tasks.markDone(index));
            } else {
                gui.addMessage(tasks.unmarkDone(index));
            }
        } catch (InvalidInputException e) {
            gui.addMessage(e.toString());
        }
    }
}
