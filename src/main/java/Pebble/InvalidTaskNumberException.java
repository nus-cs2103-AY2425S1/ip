package pebble;

// Custom exception for unknown commands (unused)

/**
 * Throws exception when task number given is invalid
 */
public class InvalidTaskNumberException extends Exception {
    public InvalidTaskNumberException() {
        super("Invalid task number.");
    }
}
