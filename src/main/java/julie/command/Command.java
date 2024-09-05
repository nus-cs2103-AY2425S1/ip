package julie.command;
import julie.exception.JulieException;
import julie.task.Task;
import java.util.List;

/**
 * An abstract class that encapsulates the commands functions for the application.
 */
public abstract class Command {
    /** The boolean that determines whether the app continues running. */
    public boolean isExit = false;
    /** The string to be formatted. */
    public String commandString;
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
     * @throws JulieException if the command cannot be executed.
     */
    public abstract void run(List<Task> taskList) throws JulieException;
}
