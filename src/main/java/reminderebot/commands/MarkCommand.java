package reminderebot.commands;

import reminderebot.ReminderebotException;
import reminderebot.Storage;
import reminderebot.TaskList;
import reminderebot.Ui;

/**
 * The MarkCommand class represents a command to mark a task in tasklist as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Create a Mark command.
     * @param index
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks task as done.
     * @param tasklist
     * @param ui
     * @param storage
     * @return String representing Mark command
     * @throws ReminderebotException
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws ReminderebotException {
        if (index > tasklist.length() || index < 1) { // index is out of bounds
            throw new ReminderebotException("Item selected to be marked is not in list.\n"
                    + "Syntax: mark <int>");
        }
        tasklist.markTask(index);
        return ui.markTask(tasklist.getTask(index - 1));
    }

    /**
     * Mark command does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
