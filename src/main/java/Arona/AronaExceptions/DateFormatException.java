package arona.AronaExceptions;

public class DateFormatException extends AronaException {
    /**
     * Constructor for the exceptions class of Arona, encapsulates all possible user errors that are non-fatal and can
     * be handled by simply ignoring the user input and sending an error message
     */
    public DateFormatException() {
        super("Please input your date in yyyy-mm-dd format.");
    }
}