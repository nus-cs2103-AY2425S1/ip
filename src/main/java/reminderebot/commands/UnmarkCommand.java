package reminderebot.commands;

import reminderebot.ReminderebotException;
import reminderebot.Storage;
import reminderebot.TaskList;
import reminderebot.Ui;


/**
 * The UnmarkCommand class represents a command to mark a task in tasklist as undone.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Create an Unmark command.
     * @param index
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks task as undone.
     * @param tasklist
     * @param ui
     * @param storage
     * @return String representing Unmark command
     * @throws ReminderebotException
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws ReminderebotException {
        if (index > tasklist.length() || index < 1) { // index is out of bounds
            throw new ReminderebotException("Item selected to be marked is not in list.\n"
                    + "Syntax: unmark <int>");
        }
        tasklist.unmarkTask(index);
        return ui.unmarkTask(tasklist.getTask(index - 1));
    }

    /**
     * Unmark command does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
