public class PurrfessorDipsyException extends Exception {
    private final ErrorType errorType;
    public enum ErrorType {
        UNKNOWN_COMMAND,
        INVALID_MARK,
        INVALID_TODO,
        INVALID_DEADLINE,
        INVALID_EVENT,
        INVALID_MARK_INDEX
    }

    public PurrfessorDipsyException(ErrorType errorType) {
        super();
        this.errorType = errorType;
    }

    @Override
    public String getMessage() {
        return switch (errorType) {
            case INVALID_TODO -> "'todo' command requires a description.\nUsage: todo <description>";
            case INVALID_DEADLINE ->
                    "'deadline' command requires a 'by' date.\nUsage: deadline <description> /by <day/date/time>";
            case INVALID_EVENT ->
                    "'event' command requires a description, a '/from' time, and a '/to' time.\nUsage: event <description> /from <day/date/time> /to <day/date/time>";
            case INVALID_MARK ->
                    "'mark' or 'unmark' command requires a valid index.\nUsage: mark <index> or unmark <index>";
            case INVALID_MARK_INDEX ->
                    "index given in 'mark <index>' lies outside of the table's valid range.";
            default -> "Unrecognized command.\n\n" + getUsage();
        };
    }

    private static String getUsage() {
        return "Usage: <command> [options]\n" +
                "\nCommands:\n" +
                "  todo <description>                            Create a new todo item\n" +
                "  deadline <description> /by <date>             Create a new task with a deadline\n" +
                "  event <description> /from <start> /to <end>   Create a new event with start and end times\n" +
                "  mark <index>                                  Mark the task at the specified index as done\n" +
                "  unmark <index>                                Unmark the task at the specified index\n" +
                "  list                                          List all tasks\n" +
                "  bye                                           Exit the program\n" +
                "\nExamples:\n" +
                "  todo Buy Ciao Churru for Dipsy\n" +
                "  deadline Submit Dipsy's food order /by 21-08-2024\n" +
                "  event Play with Dipsy /from 21-08-2024 10:00 /to 21-08-2024 12:00\n" +
                "  mark 1";
    }
}
