package pebble;

/**
 * Throws exception when task number given is invalid
 */
class InvalidTaskException extends Exception {
    public InvalidTaskException() {
        super("Invalid task found. Please contact administrator.");
    }
}
