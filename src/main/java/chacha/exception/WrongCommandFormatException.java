package chacha.exception;

/**
 * Represents a custom exception in response to wrong command format.
 */
public class WrongCommandFormatException extends Exception {
    private static final String START_TIME_MSG = "Please type start time in the form of \'from ...\'.";
    private static final String END_TIME_MSG = "Please type end time in the form of \'to ...\'.";
    private static final String DEADLINE_MSG = "Please type deadline in the form of \'by ...\'.";
    private String msg;

    /**
     * Constructs a new EventTimeException with the type of error, giving the corresponding
     * message.
     */
    public WrongCommandFormatException(String type) {
        switch (type) {
        case "start":
            this.msg = START_TIME_MSG;
            break;
        case "end":
            this.msg = END_TIME_MSG;
            break;
        case "deadline":
            this.msg = DEADLINE_MSG;
            break;
        default:
            this.msg = "";
        }
    }

    /**
     * Returns the error message.
     *
     * @return String representation of the message
     */
    @Override
    public String toString() {
        return this.msg;
    }
}
