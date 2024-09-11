package greetbot;

/**
 * A class which parses commands or arguments of a command.
 */
public class Parser {

    /**
     * Returns a string array which contains a keyword and its arguments.
     * @param input The input from user keyboard.
     * @return
     */
    public static String[] parseCommand(String input) {
        final int keywordPosition = 0;
        final int splitCommandToKeywordAndArgumentPart = 2;
        String[] command = input.split(" ", splitCommandToKeywordAndArgumentPart);
        command[keywordPosition] = command[keywordPosition].toUpperCase();
        return command;
    }

    /**
     * Returns a integer which indicates the position in task list.
     * @param input The argument for mark/unmark/delete keyword.
     * @return The position to mark/unmark/delete.
     */
    public static int parseMarkUnmarkDelete(String input) {
        return Integer.parseInt(input.trim());
    }

    public static String parseTodo(String input) {
        return input.trim();
    }

    /**
     * Returns parsed result for input about event
     * @param input arguments for event keyword
     * @return parsed strings for event keyword
     */
    public static String[] parseEvent(String input) {
        final int descriptionPosition = 0;
        final int fromTimePosition = 1;
        final int toTimePosition = 2;
        final int fromAndSpaceTotalCharacters = 5;
        final int toAndSpaceTotalCharacters = 3;
        String[] args = input.trim().split("/");
        args[descriptionPosition] = args[descriptionPosition].trim();
        args[fromTimePosition] = args[fromTimePosition].trim().substring(fromAndSpaceTotalCharacters);
        args[toTimePosition] = args[toTimePosition].trim().substring(toAndSpaceTotalCharacters);
        return args;
    }

    /**
     * Returns parsed result for input about event
     * @param input arguments for deadline keyword
     * @return parsed strings for event keyword
     */
    public static String[] parseDeadline(String input) {
        final int descriptionPosition = 0;
        final int byTimePosition = 1;
        final int byAndSpaceTotalCharacters = 3;
        String[] args = input.trim().split("/");
        args[descriptionPosition] = args[descriptionPosition].trim();
        args[byTimePosition] = args[byTimePosition].trim().substring(byAndSpaceTotalCharacters);
        return args;
    }

    public static String parseFind(String input) {
        return input.trim();
    }

}
