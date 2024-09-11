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
        private static final String MESSAGE_UPDATE_DEADLINE= "Update command for Deadline tasks must "
                + "have the format: update index /by date.";
        private static final String MESSAGE_UPDATE_EVENT= "Update command for Event Tasks"
                + "must have the format: update index /from date /to date.";



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
                case "updateDeadline" -> MESSAGE_UPDATE_DEADLINE;
                case "updateEvent" -> MESSAGE_UPDATE_EVENT;
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
            assert message != null && !message.isEmpty() : "Number format exception message should "
                    + "not be null or empty";
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
            assert index >= 0 : "Index provided should be non negative";
        }
    }

    /**
     *  The exception thrown when a line in the data file contains invalid information.
     */
    public static class InvalidArgumentsException extends FishmanException {
        private static final String MESSAGE_PREFIX = "Error: ";
        private static final String MESSAGE_SUFFIX = " Skipping.....\n";

        /**
         * Constructs a new InvalidArgumentsException with a message based on the type of error.
         *
         * @param errorType The type of error (e.g., "INVALID_EVENT", "INVALID_DEADLINE").
         * @param line      The line containing the invalid information.
         */
        public InvalidArgumentsException(ErrorType errorType, String line) {
            super(MESSAGE_PREFIX + getErrorMessage(errorType, line) + MESSAGE_SUFFIX);
            assert line != null && !line.isEmpty() : "Line should not be null or empty";
        }

        private static String getErrorMessage(ErrorType errorType, String line) {
            switch (errorType) {
            case INVALID_TODO:
                return "Invalid ToDo arguments in line: " + line;
            case INVALID_DEADLINE:
                return "Invalid Deadline arguments in line: " + line;
            case INVALID_EVENT:
                return "Invalid Event arguments in line: " + line;
            case INVALID_TASK_TYPE:
                return "Unknown task type in line: " + line;
            case INVALID_IS_DONE:
                return "Invalid isDone value (must be 'true' or 'false') in line: " + line;
            case INVALID_ARGUMENTS:
                return "Missing arguments in line: " + line;
            case INVALID_DATE_FORMAT:
                return "Invalid date format in line: " + line;
            default:
                return "Unknown error in line: " + line;
            }
        }

        /**
         * The possible error types when loading the data file.
         */
        public enum ErrorType {
            INVALID_TODO,
            INVALID_DEADLINE,
            INVALID_EVENT,
            INVALID_TASK_TYPE,
            INVALID_IS_DONE,
            INVALID_ARGUMENTS,
            INVALID_DATE_FORMAT
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
            assert dateTimeStr != null && !dateTimeStr.isEmpty() : "DateTime string should not be null or empty";
        }
    }

    public static class InvalidUpdateTypeException extends FishmanException {
        private static final String MESSAGE = "Invalid task type to be updated.";

        /**
         * Constructs a new InvalidUpdateTypeException with message.
         */
        public InvalidUpdateTypeException() {
            super(MESSAGE);
        }
    }

}

