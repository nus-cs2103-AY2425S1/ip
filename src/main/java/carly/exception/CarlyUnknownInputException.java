package carly.exception;

/**
 * Exception thrown when an unknown or invalid command is encountered.
 * This exception is used when the user inputs a command that does not match the commands in enum Commands.
 */
public class CarlyUnknownInputException extends CarlyException {

    /**
     * Constructs a new CarlyUnknownIInputException with a detailed error message.
     * The message specifies the invalid input provided by the user and instructs them to enter a valid command.
     *
     * @param msg The invalid command input that caused the exception.
     */
    public CarlyUnknownInputException(String action, String msg) {
        super(action.isEmpty()
                ? "No input detected. Please type in something!!"
                : "OOPS!! " + msg + "\nPlease ensure that you enter a valid command.");
    }

}
