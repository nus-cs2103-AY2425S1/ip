import java.time.format.DateTimeFormatter;

public class Parser {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");



    public Parser() {}

    /**
     * Determines the action type based on the user's input string.
     *
     * @param input The string representing the action to perform.
     * @return The corresponding ActionType enum value, or null if the input does not match any ActionType.
     */
    static Bobby.ActionType getActionType(String input) {
        try {
            return Bobby.ActionType.valueOf(input);
        } catch (Exception e) {
            return Bobby.ActionType.retry;
        }
    }
}
