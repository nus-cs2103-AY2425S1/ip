package friday.command;

import friday.util.Storage;
import friday.util.TaskList;
import friday.util.Ui;

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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(message);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
