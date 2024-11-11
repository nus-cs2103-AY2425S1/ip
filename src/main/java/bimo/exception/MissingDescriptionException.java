package bimo.exception;

/**
 * Represents an exception for missing task description.
 */
public class MissingDescriptionException extends BimoException {
    /**
     * Instantiates an MissingDescriptionException.
     */
    public MissingDescriptionException() {
        super("Please provide a description for you task.");
    }
}
