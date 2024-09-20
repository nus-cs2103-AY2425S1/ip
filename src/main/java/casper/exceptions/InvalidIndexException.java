package casper.exceptions;

/**
 * Thrown to indicate that an invalid index was provided.
 * This exception provides a suggested solution for providing a valid numerical index.
 */
public class InvalidIndexException extends ExceptionWithSolution {
    /**
     * Constructs a new {@code InvalidIndexException} with a specified command.
     * The exception message indicates that the index is invalid, and the solution provides
     * guidance on providing a valid numerical index for the given command.
     *
     * @param command The command associated with the invalid index.
     */
    public InvalidIndexException(String command) {
        super("Invalid index.",
                String.format("Please provide a numerical index to `%s`. "
                        + "You may use the `list` command to see all casper.tasks.", command));
    }
}
