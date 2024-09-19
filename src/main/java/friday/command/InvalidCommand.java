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
        assert message != null && !message.isEmpty() : "Message should not be null or empty";
        this.message = message;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        return ui.showError(message);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
