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
            default -> "Unknown invalid command. Please try again." ;
        };
    }



}
