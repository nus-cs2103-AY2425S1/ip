package nether;

/**
 * Represents a custom exception for the Nether application.
 *
 * By using this custom exception, the application can provide more meaningful error
 * messages to the user and handle exceptions.
 *
 */

public class NetherException extends RuntimeException {
    public NetherException(String message) {
        super(message);
    }
}
