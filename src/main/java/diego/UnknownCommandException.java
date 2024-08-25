package diego;

/**
 * Represents an exception thrown when an unknown command is entered.
 */
public class UnknownCommandException extends DiegoException {

    /**
     * Constructs a new UnknownCommandException with a predefined error message.
     */
    public UnknownCommandException() {
        super(
                "The command you entered is not recognized.\n" +
                "Please use one of the following formats:\n" +
                " - todo sleep\n" +
                " - event project meeting /from Mon 2pm /to 4pm\n" +
                " - deadline return book /by Sunday\n" +
                "____________________________________________________________");
    }
}
