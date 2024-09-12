package shrimp.exception;

import shrimp.utility.Parser;

/**
 * Represents an exception specific to the Shrimp application.
 * This class and its inner classes handle various error cases
 * that can occur within the application.
 */
public class ShrimpException extends Exception {

    //fields used by shrimp, formatted
    private static final String description = "description";
    private static final String by = "by";
    private static final String from = "from";
    private static final String to = "to";

    //the error code of the exception raised
    private final String errorCode;

    /**
     * Constructs a {@code ShrimpException} with the specified error message and code.
     *
     * @param errorMessage The detailed error message.
     * @param errorCode    The code representing the specific error.
     */
    ShrimpException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    /**
     * Retrieves the error code associated with this exception.
     *
     * @return The error code as a string.
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Thrown when an invalid command is provided to the Shrimp application. (ERR001)
     */
    public static class InvalidCommandException extends ShrimpException {
        private static final String errorCode = "ERR001";
        private static final String errorMessage = "I don't recognise that, can you try again?";

        /**
         * Constructs an {@code InvalidCommandException}.
         */
        public InvalidCommandException() {
            super(errorMessage, errorCode);
        }
    }

    /**
     * Thrown when a required argument is missing for a command. (ERR002)
     */
    public static class MissingArgumentException extends ShrimpException {
        private static final String errorCode = "ERR002";
        private static final String errorMessage_toDo = "I need a " + description + " to make a TODO...";
        private static final String errorMessage_mark = "You didn't indicate which task to mark...";
        private static final String errorMessage_unmark = "You didn't indicate which task to unmark...";
        private static final String errorMessage_deadline = "I need a " + description + " and a "
                + by + " to make a DEADLINE...";
        private static final String errorMessage_event = "I need a " + description + ", a " + from
                + " and a " + to + " to make an EVENT...";
        private static final String errorMessage_delete = "You didn't indicate which task to delete...";
        private static final String errorMessage_find = "I need something to search for...";
        private static final String errorMessage_default = "There seems to be an issue somewhere! :<";

        /**
         * Constructs a {@code MissingArgumentException} based on the command type.
         *
         * @param command The type of command that triggered the exception.
         */
        public MissingArgumentException(Parser.CommandType command) {
            super(switchErrorMessage(command), errorCode);
        }

        private static String switchErrorMessage(Parser.CommandType command) {
            return switch (command) {
            case ADD -> errorMessage_toDo;
            case MARK -> errorMessage_mark;
            case UNMARK -> errorMessage_unmark;
            case DEADLINE -> errorMessage_deadline;
            case EVENT -> errorMessage_event;
            case DELETE -> errorMessage_delete;
            case FIND -> errorMessage_find;
            default -> errorMessage_default;
            };
        }
    }

    /**
     * Thrown when an invalid number format is encountered. (ERR003)
     */
    public static class NumberFormatException extends ShrimpException {
        private static final String errorCode = "ERR003";
        private static final String errorMessage = "Your values seems wrong, maybe try again?";

        /**
         * Constructs a {@code NumberFormatException}.
         */
        public NumberFormatException() {
            super(errorMessage, errorCode);
        }

    }

    /**
     * Thrown when an operation is attempted on an empty task list. (ERR004)
     */
    public static class EmptyArrayException extends ShrimpException {
        private static final String errorCode = "ERR004";
        private static final String errorMessage = "There's no tasks for me to interact with...";

        /**
         * Constructs an {@code EmptyArrayException}.
         */
        public EmptyArrayException() {
            super(errorMessage, errorCode);
        }

    }

    /**
     * Thrown when an index is out of bounds for the task list.  (ERR005)
     */
    public static class ArrayIndexOutOfBoundException extends ShrimpException {
        private static final String errorCode = "ERR005";
        private static final String errorMessage = "I cannot find the task that you're looking for... ";

        /**
         * Constructs an {@code ArrayIndexOutOfBoundException}.
         */
        public ArrayIndexOutOfBoundException() {
            super(errorMessage, errorCode);
        }

    }

    /**
     * Thrown when an invalid date-time format is encountered. (ERR006)
     */
    public static class InvalidDateTimeException extends ShrimpException {
        private static final String errorCode = "ERR006";
        private static final String errorMessage = "The datetime format you inserted is wrong (DD/MM/YYYY)... ";

        /**
         * Constructs an {@code InvalidDateTimeException}.
         */
        public InvalidDateTimeException() {
            super(errorMessage, errorCode);
        }

    }
}
