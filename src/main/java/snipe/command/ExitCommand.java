package snipe.command;

import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.core.TaskList;
import snipe.util.Ui;

import java.io.IOException;

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
     * Executes the exit command by returning a closing message to the user.
     * This method provides feedback to the user indicating that the application is terminating.
     *
     * @param tasks          The {@link TaskList}, not used in this command.
     * @param ui             The {@link Ui} instance used to display messages to the user.
     * @param storage        The {@link Storage} instance, not used in this command.
     * @param archiveTasks   The {@link TaskList}, not used in this command.
     * @param archiveStorage The {@link Storage} instance, not used in this command.
     * @return A closing message indicating that the application is terminating.
     * @throws SnipeException If an application-specific error occurs during execution.
     */
    @Override
    public String getResponse(
            TaskList tasks,
            Ui ui,
            Storage storage,
            TaskList archiveTasks,
            Storage archiveStorage
    ) throws SnipeException {
        return "Bye. Hope to see you again soon!";
    }

}
