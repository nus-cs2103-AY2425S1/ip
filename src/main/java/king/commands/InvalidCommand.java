package king.commands;

import king.Storage;
import king.TaskList;
import king.ui.Ui;

/**
 * Represents a command that handles invalid user input.
 * This command displays an error message indicating that the provided command is invalid.
 */
public class InvalidCommand extends Command {

    /**
     * Executes the invalid command by displaying an error message to the user.
     *
     * @param tasks The task list (not used in this command).
     * @param ui The user interface to display the error message.
     * @param storage The storage (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Invalid command.";
    }

    /**
     * Indicates whether this command signals the application to exit.
     *
     * @return {@code false} as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
