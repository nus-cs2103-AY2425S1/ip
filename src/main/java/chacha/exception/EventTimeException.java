package chacha.exception;

/**
 * Represents a custom exception in response to wrong event command format.
 */
public class EventTimeException extends Exception {
    private static final String START_TIME_MSG = "Please type start time in the form of \'from ...\'.";
    private static final String END_TIME_MSG = "Please type end time in the form of \'to ...\'.";
    private static final String DEADLINE_MSG = "Please type deadline in the form of \'by ...\'.";
    private String msg;

    /**
     * Constructs a new EventTimeException with a specified error message
     */
    public EventTimeException(String type) {
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

    @Override
    public String toString() {
        return this.msg;
    }
}
