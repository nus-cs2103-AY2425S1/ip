package bobby.ui;

/**
 * The Parser class is responsible for interpreting and processing user input.
 * It determines the type of action requested by the user and provides the appropriate
 * response based on the input.
 */
public class Parser {
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
     *      if the input does not match any ActionType.
     */
    public static Bobby.ActionType getActionType(String input) {
        try {
            String[] stringArr = input.split(" ", 2);
            return Bobby.ActionType.valueOf(stringArr[0]);
        } catch (Exception e) {
            return Bobby.ActionType.valueOf("retry");
        }
    }
    /**
     * Extracts the description part from the user's input string.
     * This method assumes the input is composed of an action followed by a description.
     *
     * @param input The user's input string, which may contain both an action and a description.
     * @return The description part of the input, or null if there is no description.
     */
    public static String getDesc(String input) {
        try {
            String[] stringArr = input.split(" ", 2);
            return stringArr[1];
        } catch (Exception e) {
            return null;
        }
    }
}
