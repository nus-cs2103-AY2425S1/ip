package noosy.exception;

/**
 * Represents a custom date time formatting exception for the Noosy task management chatbot.
 * This exception is thrown when specific errors occur within the Noosy application.
 */
public class NoosyDateTimeException extends NoosyException {

    /**
     * Constructs a new NoosyException with the date time formatting message.
     *
     */
    public NoosyDateTimeException() {
        super("Uh oh! Date should be in the format: yyyy-MM-dd HHmm");
    }
}