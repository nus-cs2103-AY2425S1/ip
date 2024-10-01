package lict;

/**
 * The {@code LictException} class represents a custom exception that is thrown within the Lict application.
 * It extends the {@code Exception} class and provides constructors for creating exceptions
 * with or without a custom message.
 */
public class LictException extends Exception {

    public LictException() {
        super();
    }

    public LictException(String message) {
        super(message);
    }

}
