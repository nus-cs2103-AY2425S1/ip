package pebble;

/**
 * Throws exception when task number given is invalid
 */
public class InvalidTaskNumberException extends Exception {
    public InvalidTaskNumberException() {
        super("Invalid task number.");
    }
}
