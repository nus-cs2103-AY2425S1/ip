package snipe.command;

import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.core.TaskList;
import snipe.util.Ui;

/**
 * The {@code ExitCommand} class represents a command to exit the application.
 * This command signals the termination of the program and provides a closing message to the user.
 */
public class ExitCommand extends Command{

    /**
     * Indicates that this command is an exit command.
     * Overrides the default behavior to return {@code true}.
     *
     * @return {@code true} because this command exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the exit command by displaying a closing message to the user.
     * The method provides feedback to the user that the application is terminating.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface used to display the closing message.
     * @param storage The storage object (not used in this command).
     * @throws SnipeException If an application-specific error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnipeException {
        String CLOSING_MESSAGE = "Bye. Hope to see you again soon!";
        ui.printWithLines(CLOSING_MESSAGE);
    }
}
