package carly.exception;

/**
 * Exception thrown when an unknown or invalid command is encountered.
 * This exception is used when the user inputs a command that does not match the commands in enum Commands.
 */
public class CarlyUnknownIInputException extends CarlyException {

    /**
     * Constructs a new CarlyUnknownIInputException with a detailed error message.
     * The message specifies the invalid input provided by the user and instructs them to enter a valid command.
     *
     * @param action The invalid command input that caused the exception.
     */
    public CarlyUnknownIInputException(String action) {
        super("OOPS!! The input '" + action + "' is not valid. Please ensure that you enter a valid command.");
    }

}
