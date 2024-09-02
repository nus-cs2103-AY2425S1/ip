package beeboo.exception;

/**
 * Represents an exception that is thrown when no Description is found in command.
 * This exception is used to indicate that the command has no Description and need to be re-entered
 */
public class NoDescriptionException extends BeeBooExceptions {
    /**
     * Constructs a NoDescriptionException with a specific error message.
     *
     * @param message The error message associated with this exception.
     */
    public NoDescriptionException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of this exception, indicating that no description was found in the command
     *
     * @return A message stating that there is no description in the command
     */
    @Override
    public String toString() {
        return "You didn't put in a description user. Here is a list of commands and descriptions\n"
                + "event [eventName] /from [time] /to [time]\n"
                + "deadline [deadlineName]/ by [time]\n "
                + "todo [todoName]\n"
                + "Please try again user";
    }
}
