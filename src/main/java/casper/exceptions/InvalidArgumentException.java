package casper.exceptions;

/**
 * Thrown to indicate that an invalid argument was provided for a specific command.
 * This exception provides a suggested solution listing the valid arguments.
 */
public class InvalidArgumentException extends ExceptionWithSolution {
    /**
     * Constructs an InvalidArgumentException with a specified command, argument, and a list of valid arguments.
     *
     * @param command The command that received the invalid argument.
     * @param argument The invalid argument provided to the command.
     * @param validArguments A string listing valid arguments for the command.
     */
    public InvalidArgumentException(String command, String keyword, String argument, String validArguments) {
        super(String.format("`%s` is an invalid argument for `/%s` of %s.", argument, keyword, command),
                String.format("Valid arguments: %s", validArguments));
    }
}
