package friday.command;

import friday.Storage;
import friday.TaskList;
import friday.Ui;

/**
 * Represents a command that indicates an invalid user input or an unrecognized command.
 * Inherits from the Command class and provides functionality to display an error message.
 */
public class InvalidCommand extends Command {
    private String message;

    /**
     * Constructs an InvalidCommand with a specified error message.
     *
     * @param message The error message to be displayed to the user.
     */
    public InvalidCommand(String message) {
        this.message = message;
    }

    /**
     * Executes the command to display the error message to the user.
     * This method does not modify the task list or storage.
     *
     * @param tasks  The TaskList is not used in this command.
     * @param ui     The Ui object for displaying the error message to the user.
     * @param storage The Storage object is not used in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(message);
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false as this command does not terminate the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}