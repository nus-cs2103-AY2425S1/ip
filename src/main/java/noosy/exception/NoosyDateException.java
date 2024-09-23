package noosy.exception;

/**
 * Represents a custom date formatting exception for the Noosy task management chatbot.
 * This exception is thrown when specific errors occur within the Noosy application.
 */
public class NoosyDateException extends NoosyException {

    /**
     * Constructs a new NoosyException with the date formatting message.
     *
     */
    public NoosyDateException() {
        super("Uh oh! Date should be in the format: yyyy-MM-dd");
    }
}
