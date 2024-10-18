package beeboo.exception;

/**
 * Represents an exception that is thrown when an invalid date format is provided.
 * This exception is used to indicate that the date format for creating tasks is incorrect.
 */
public class InvalidDateException extends BeeBooExceptions {

    /**
     * Constructs an InvalidDateException with a specific error message.
     *
     * @param error The error message associated with this exception.
     */
    public InvalidDateException(String error) {
        super(error);
    }

    /**
     * Returns a string representation of this exception, providing instructions on the correct date format.
     *
     * @return A message indicating the correct format for event and deadline tasks.
     */
    @Override
    public String toString() {
        return "Your date commands are wrong. Here are the instructions on how to create tasks:\n"
                + "event [eventName] /from [yyyy-mm-dd] [hh-mm]/to [time]\n"
                + "deadline [deadlineName] /by [yyyy-mm-dd] [hh-mm]";
    }
}
