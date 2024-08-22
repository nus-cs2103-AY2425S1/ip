/**
 * Base exception class for Fishman bot custom exception
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
         *
         * @param command The invalid command entered.
         */
        public InvalidCommandException(String command) {
            super(MESSAGE + command);
        }
    }

    /**
     * The exception thrown when a command is missing arguments.
     */
    public static class MissingArgumentException extends FishmanException {
        private static final String MESSAGE_MARK = "Mark command requires an index";
        private static final String MESSAGE_UNMARK = "Unmark command requires an index";
        private static final String MESSAGE_TODO = "Todo command requires a description";
        private static final String MESSAGE_DEADLINE = "Deadline command requires a description and a /by date";
        private static final String MESSAGE_EVENT = "Event command requires a description, /from, and /to dates";

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
                case "mark" -> MESSAGE_MARK;
                case "unmark" -> MESSAGE_UNMARK;
                case "todo" -> MESSAGE_TODO;
                case "deadline" -> MESSAGE_DEADLINE;
                case "event" -> MESSAGE_EVENT;
                default -> "Missing argument for command";
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
        private static final String MESSAGE = "The list is empty. Unable to mark or unmark tasks.";

        /**
         * Constructs a new EmptyListException with message.
         */
        public EmptyListException() {
            super(MESSAGE);
        }
    }

}

