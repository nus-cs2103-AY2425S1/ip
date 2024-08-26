package friday.command;

import friday.Storage;
import friday.TaskList;
import friday.Ui;

/**
 * Represents a command to exit the application.
 * Inherits from the Command class and implements the execute method to display a goodbye message.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to terminate the application.
     * Displays a goodbye message to the user.
     *
     * @param tasks  The TaskList is not used in this command.
     * @param ui     The Ui object for displaying messages to the user.
     * @param storage The Storage object is not used in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayGoodbye();
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return true as this command terminates the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}