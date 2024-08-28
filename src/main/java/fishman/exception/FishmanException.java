package fishman.exception;

/**
 * Represents the base exception class for Fishman bot custom exception
 */
public class FishmanException extends Exception {

    /**
     * Constructs a new FishmanException with no message.
     */
    public FishmanException() {
    }

    /**
     * Constructs a new FishmanException with the specified message
     *
     * @param message The detail message.
     */
    public FishmanException(String message) {
        super(message);
    }

    /**
     * The exception thrown when an invalid command is entered.
     */
    public static class InvalidCommandException extends FishmanException {
        private static final String MESSAGE = "Please enter a valid command such as 'list' or 'bye' :(";

        /**
         * Constructs a new InvalidCommandException with the invalid command.
         */
        public InvalidCommandException() {
            super(MESSAGE);
        }
    }

    /**
     * The exception thrown when a command is missing arguments.
     */
    public static class MissingArgumentException extends FishmanException {
        private static final String MESSAGE_DEADLINE = "Deadline command requires a description or a /by date";
        private static final String MESSAGE_EVENT = "Event command requires a description or /from and /to dates";


        /**
         * Constructs a new InvalidCommandException with a message specific to the command type.
         *
         * @param commandType The type of command that is missing arguments.
         */
        public MissingArgumentException(String commandType) {
            super(getMessageForCommandType(commandType));
        }

        /**
         * The method to return the corresponding message for the command type.
         *
         * @param commandType The type of command that is missing arguments.
         * @return The error message of the command type.
         */
        private static String getMessageForCommandType(String commandType) {
            return switch (commandType) {
                case "deadline" -> MESSAGE_DEADLINE;
                case "event" -> MESSAGE_EVENT;
                default -> "Missing argument for " + commandType + " command.";
            };
        }
    }

    /**
     * The exception thrown when a string cannot be parsed as number.
     */
    public static class NumberFormatException extends FishmanException {
        private static final String MESSAGE = "Invalid number format: ";

        /**
         * Constructs a new NumberFormatException with the invalid input.
         *
         * @param message The invalid input that could not be parsed.
         */
        public NumberFormatException(String message) {
            super(MESSAGE + message);
        }
    }

    /**
     * The exception thrown when trying to perform command on an empty Task List.
     */
    public static class EmptyListException extends FishmanException {
        private static final String MESSAGE = "The list is empty. Unable to perform operation.";

        /**
         * Constructs a new EmptyListException with message.
         */
        public EmptyListException() {
            super(MESSAGE);
        }
    }

    /**
     * The exception thrown when an index is out of bounds for a given Task List.
     */
    public static class IndexOutOfBoundsException extends FishmanException {
        private static final String MESSAGE = "The index provided is out of bounds for the task list.";

        /**
         * Constructs a new IndexOutOfBoundsException with message.
         *
         * @param index The index provided by the user that is out of bounds.
         */
        public IndexOutOfBoundsException(int index) {
            super(MESSAGE + " Index provided: " + index);
        }
    }

    /**
     *  The exception thrown when a line in the data file contains invalid information.
     */
    public static class InvalidArgumentsException extends FishmanException {
        private static final String MESSAGE = "Insufficient/Extra information retrieved from line: ";

        /**
         * Constructs a new InvalidArgumentException with message.
         *
         * @param line The line containing the invalid information.
         */
        public InvalidArgumentsException(String line) {
            super(MESSAGE + line + " Skipping.....\n" );
        }
    }

    /**
     * The exception thrown when the DateTime format provided is wrong.
     */
    public static class InvalidDateFormatException extends FishmanException {
        private static final String MESSAGE = "Invalid Date format here: ";

        /**
         * Constructs a new InvalidDateFormatException with message.
         *
         * @param dateTimeStr The datetime argument that is in the wrong format.
         */
        public InvalidDateFormatException(String dateTimeStr) {
            super(MESSAGE + dateTimeStr);
        }
    }

}

