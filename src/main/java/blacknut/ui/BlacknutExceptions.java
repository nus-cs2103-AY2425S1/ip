package blacknut.ui;

/**
 * A collection of custom exceptions used in the Blacknut application.
 */
public class BlacknutExceptions {


    /**
     * Represents a generic exception in the Blacknut application.
     */
    public static class BlacknutException extends Exception {
        public BlacknutException(String message) {
            super(message);
        }
    }

    /**
     * Represents an exception for invalid commands.
     */
    public static class InvalidCommandException extends Exception {
        public InvalidCommandException(String message) {
            super(message);
        }
    }


    /**
     * Represents an exception for commands with an empty description.
     */
    public static class EmptyDescriptionException extends Exception {
        public EmptyDescriptionException(String message) {
            super(message);
        }
    }

    /**
     * Represents an exception for invalid task numbers.
     */
    public static class InvalidTaskNumberException extends Exception {
        public InvalidTaskNumberException(String message) {
            super(message);
        }
    }

    /**
     * Represents an exception for incorrectly formatted commands or input.
     */
    public static class IncorrectFormatException extends Exception {
        public IncorrectFormatException(String message) {
            super(message);
        }
    }
}
