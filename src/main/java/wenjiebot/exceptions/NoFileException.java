package wenjiebot.exceptions;

/**
 * Represents an exception that is thrown when the required file cannot be found.
 * This exception provides a custom error message to indicate that the file is missing.
 */
public class NoFileException extends WenJieException {

    /**
     * Constructs a NoFileException with a default error message.
     */
    public NoFileException() {
        super("test");
    }

    /**
     * Returns a custom error message that indicates the file is missing.
     *
     * @return the custom error message string
     */
    @Override
    public String getMessage() {
        return "eh siao liao, I can't find the file to put my lobang in sia";
    }
}
