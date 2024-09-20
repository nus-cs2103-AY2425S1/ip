package casper.exceptions;

/**
 * Thrown to indicate that an invalid command was provided.
 * This exception provides a suggested solution listing the valid commands.
 */
public class InvalidCommandException extends ExceptionWithSolution {
    /**
     * Constructs a new {@code InvalidCommandException} with a specified invalid command.
     * The exception message indicates the command that is invalid, and the solution lists
     * the valid commands that can be used.
     *
     * @param command The invalid command that was provided.
     */
    public InvalidCommandException(String command) {
        super(String.format("`%s` is an invalid command.", command),
                "Valid commands: `list`, `bye`, `sort`, `mark`, "
                        + "`unmark`, `delete, `todo`, `deadline`, `event`.");
    }
}
