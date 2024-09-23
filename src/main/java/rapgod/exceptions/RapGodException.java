package rapgod.exceptions;

/**
 * A custom exception class for the RapGod application.
 * This class extends {@link Exception} and is used to represent specific error conditions
 * in the application with a custom message.
 */
public class RapGodException extends Exception {

    /**
     * Constructs a new {@code RapGodException} with the specified detail message.
     *
     * @param message The detail message for this exception.
     */
    public RapGodException(String message) {
        super(message);
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
