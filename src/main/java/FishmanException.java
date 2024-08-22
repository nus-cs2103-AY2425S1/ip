public class FishmanException extends Exception {

    public FishmanException() {
    }

    public FishmanException(String message) {
        super(message);
    }

    public static class InvalidCommandException extends FishmanException {
        private static final String MESSAGE = "Please enter a valid command such as 'list' or 'bye' :(";
        public InvalidCommandException(String command) {
            super(MESSAGE + command);
        }
    }

    public static class MissingArgumentException extends FishmanException {
        private static final String MESSAGE_MARK = "Mark command requires an index";
        private static final String MESSAGE_UNMARK = "Unmark command requires an index";
        private static final String MESSAGE_TODO = "Todo command requires a description";
        private static final String MESSAGE_DEADLINE = "Deadline command requires a description and a /by date";
        private static final String MESSAGE_EVENT = "Event command requires a description, /from, and /to dates";

        public MissingArgumentException(String commandType) {
            super(getMessageForCommandType(commandType));
        }

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

    public static class NumberFormatException extends FishmanException {
        private static final String MESSAGE = "Invalid number format: ";
        public NumberFormatException(String message) {
            super(MESSAGE + message);
        }
    }

    public static class EmptyListException extends FishmanException {
        private static final String MESSAGE = "The list is empty. Unable to mark or unmark tasks.";

        public EmptyListException() {
            super(MESSAGE);
        }
    }

}

