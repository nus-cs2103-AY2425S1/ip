package command;

import storage.Storage;
import TaskList.TaskList;
import ui.Ui;

/**
 * Command that represents a break or exit command.
 * This command is used to signal the application to terminate or exit.
 *
 * @author jordanchan
 */
public class BreakCommand extends Command {

    /**
     * Constructs a BreakCommand with a default input of "bye".
     */
    public BreakCommand() {
        super("bye");
    }

    /**
     * Executes the BreakCommand. This implementation does nothing as the
     * main purpose of this command is to signal an exit.
     *
     * @param t  The task list to operate on (not used in this command).
     * @param s  The storage to use (not used in this command).
     * @param ui The user interface to interact with (not used in this command).
     */
    @Override
    public String execute(TaskList t, Storage s, Ui ui) {
        return ui.goodbyeMessage();
    }

    /**
     * Indicates that this command should cause the application to exit.
     *
     * @return True, indicating that the application should terminate.
     */
}

