package myapp.exception;

/**
 * The {@code RubyException} class represents custom exceptions specific to the Ruby chatbot application.
 * This class extends the {@code Exception} class to provide custom error handling within the application.
 */
public class RubyException extends Exception {

    /**
     * Constructs a new {@code RubyException} with the specified detail message.
     * The message provides information about the nature of the exception.
     *
     * @param message The detail message that describes the error.
     */
    public RubyException(String message) {
        super(message);
    }
}

