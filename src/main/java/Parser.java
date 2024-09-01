import java.time.format.DateTimeFormatter;

/**
 * The Parser class is responsible for interpreting and processing user input.
 * It determines the type of action requested by the user and provides the appropriate
 * response based on the input.
 */
public class Parser {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs a Parser object.
     * There is no specific initialization required for this class.
     */
    public Parser() {}

    /**
     * Determines the action type based on the user's input string.
     *
     * @param input The string representing the action to perform.
     * @return The corresponding ActionType enum value, or ActionType.retry
     * if the input does not match any ActionType.
     */
    static Bobby.ActionType getActionType(String input) {
        try {
            return Bobby.ActionType.valueOf(input);
        } catch (Exception e) {
            return Bobby.ActionType.retry;
        }
    }
}
