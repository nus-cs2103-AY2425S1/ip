package rapgod.exceptions;

/**
 * Exception thrown when no input is provided by the user.
 * This class extends {@link RuntimeException} and represents an error condition
 * where the user is expected to provide some input but does not.
 */
public class NoInputException extends RuntimeException {

    /**
     * Constructs a new {@code NoInputException} with a default detail message.
     * The default message indicates that no input was detected from the user.
     */
    public NoInputException() {
        super("I don't see your input anywhere. Are you sure you typed something?");
    }


    /**
     * Returns a string representation of this exception.
     * This method returns the message associated with this exception.
     *
     * @return The message of this exception.
     */
    @Override
    public String toString() {
        return this.getMessage();
    }

}
