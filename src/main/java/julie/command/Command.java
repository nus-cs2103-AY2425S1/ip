package julie.command;

import java.util.List;

import julie.exception.JulieException;
import julie.misc.Storage;
import julie.task.Task;

/**
 * An abstract class that encapsulates the commands functions for the application.
 */
public abstract class Command {
    /** The boolean that determines whether the app continues running. */
    public boolean isExit = false;
    /** The string to be formatted. */
    final String commandString;
    /**
     * The public constructor for a command.
     * @param commandString The string to be formatted.
     */
    public Command(String commandString) {
        this.commandString = commandString;
    }

    /**
     * The public method that runs the command.
     *
     * @param taskList The taskList to be updated if applicable.
     * @param storage
     * @return The string representation of a successful commandd.
     * @throws JulieException if the command cannot be executed.
     */
    public abstract String run(List<Task> taskList, Storage storage) throws JulieException;
}
