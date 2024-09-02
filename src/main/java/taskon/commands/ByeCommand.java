package taskon.commands;

import taskon.storage.Storage;
import taskon.task.TaskList;
import taskon.ui.Ui;

/**
 * Represents a command to exit the application.
 * <p>
 * This command is used to terminate the application when the user inputs the "bye" command.
 * </p>
 */
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    /**
     * Executes the exit command.
     * <p>
     * This method triggers the UI to exit the application.
     * </p>
     *
     * @param taskList The list of tasks managed by the application.
     * @param ui       The user interface that handles output and user interactions.
     * @param storage  The storage that handles data persistence.
     * @return A string message that ends the application.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.exit();
    }


    /**
     * Checks if this command signifies an exit operation.
     *
     * @return true since this command is used to exit the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
