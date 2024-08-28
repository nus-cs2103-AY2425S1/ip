package mira.command;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command to exit the application.
     *
     * @return An exit message.
     */
    @Override
    public String execute() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Checks whether the given command is an instance of {@code ExitCommand}.
     *
     * @param command The command to check.
     * @return {@code true} if the command is an {@code ExitCommand}; {@code false} otherwise.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
