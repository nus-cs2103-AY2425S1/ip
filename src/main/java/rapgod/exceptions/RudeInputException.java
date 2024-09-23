package rapgod.exceptions;

/**
 * Exception thrown when the user provides rude input.
 * This exception extends {@link RuntimeException} and provides a specific error message
 * for rude input scenarios.
 */
public class RudeInputException extends RuntimeException {

    /**
     * Constructs a new {@code RudeInputException} with a default message.
     * The default message is "That's a very rude thing to say!".
     */
    public RudeInputException() {
        super("That's a very rude thing to say!");
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
