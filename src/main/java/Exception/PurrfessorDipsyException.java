package Exception;

public class PurrfessorDipsyException extends Exception {
    private final ErrorType errorType;
    public enum ErrorType {
        UNKNOWN_COMMAND,
        INVALID_TODO,
        INVALID_DEADLINE,
        INVALID_EVENT,
        INVALID_MARK_COMMAND,
        INVALID_MARK_INDEX,
        INVALID_DELETE_COMMAND,
        INVALID_DELETE_INDEX
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
            case INVALID_MARK_COMMAND ->
                    "'mark' or 'unmark' command requires a valid index.\nUsage: mark <index> or unmark <index>";
            case INVALID_MARK_INDEX ->
                    "index given in 'mark/unmark <index>' lies outside of the table's valid range.";
            case INVALID_DELETE_COMMAND ->
                    "'delete' command requires a valid index.\nUsage: delete <index>";
            case INVALID_DELETE_INDEX ->
                    "index given in 'delete <index>' lies outside of the table's valid range.";
            default -> "Unrecognized command.\n\n" + getUsage();
        };
    }

    private static String getUsage() {
        return "Usage: <command> [options]\n" +
                "\nCommands:\n" +
                "  todo <description>                            Create a new todo item\n" +
                "  deadline <description> /by <date>             Create a task with a deadline (yyyy-MM-dd)\n" +
                "  event <description> /from <start> /to <end>   Create an event with start and end dates\n" +
                "  mark <index>                                  Mark the task at the given index as done\n" +
                "  unmark <index>                                Unmark the task at the given index\n" +
                "  list                                          List all tasks\n" +
                "  list <date>                                   List tasks on the specified date\n" +
                "  bye                                           Exit the program\n" +
                "\nExamples:\n" +
                "  todo Buy treats for Dipsy\n" +
                "  deadline Submit report /by 2024-05-12\n" +
                "  event Conference /from 2024-08-12 /to 2024-08-14\n" +
                "  mark 1\n" +
                "\nDate format: yyyy-MM-dd\n";
    }

}
