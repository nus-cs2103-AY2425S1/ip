package darkpool.command;

import darkpool.util.DarkpoolException;
import darkpool.util.Storage;
import darkpool.util.TaskList;
import darkpool.gui.Gui;


/**
 * Represents a command to unmark a task in the task list as not completed.
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be unmarked as not completed.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command, marking the task as not completed in the task list and updating the UI.
     *
     * @param taskList The task list containing the task to be unmarked.
     * @param gui       The UI to update.
     * @param storage  The storage (not used in this command).
     * @return
     * @throws DarkpoolException If the task index is out of range.
     */
    @Override
    public String execute(TaskList taskList, Gui gui, Storage storage) throws DarkpoolException {

        if (index < 0 || index >= taskList.getSize()) {
            throw new DarkpoolException("do you know how to count? the task number is out of range");
        }

        return gui.unmark(taskList.unmarkTask(index));
    }

}
