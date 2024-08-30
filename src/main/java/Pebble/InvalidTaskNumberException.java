package pebble;

// Custom exception for unknown commands (unused)
public class InvalidTaskNumberException extends Exception {
        public InvalidTaskNumberException() {
            super("Invalid task number.");
        }
}
