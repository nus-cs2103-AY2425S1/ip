package tuesday.util;

/**
 * Class that handles special exception for Tuesday chatbot
 */
public class TuesdayException extends Exception {
    private String errorMessage;

    /**
     * Constructor for TuesdayException
     *
     * @param errorMessage Error message to be displayed
     */
    public TuesdayException(String errorMessage) {
        this.errorMessage = errorMessage;

    }

    public String getMessage() {
        return this.errorMessage;
    }
}
