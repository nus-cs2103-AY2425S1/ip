package samson.command;

import samson.Storage;
import samson.Ui;
import samson.task.TaskList;

/**
 * The <code> ExitCommand </code> class represents a command that exits the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command by displaying the goodbye message to the user.
     *
     * @param taskList The list of tasks (not used in this command).
     * @param ui       The UI object used to display the goodbye message.
     * @param storage  The storage object (not used in this command).
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.goodbyeMessage();
    }

    /**
     * Indicates whether this command should cause the application to exit.
     *
     * @return true because the ExitCommand triggers an exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
