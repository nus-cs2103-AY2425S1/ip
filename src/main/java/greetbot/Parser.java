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
        String[] command = input.split(" ", 2);
        command[0] = command[0].toUpperCase();
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

    public static String[] parseEvent(String input) {
        String[] args = input.trim().split("/");

        args[0] = args[0].trim();
        args[1] = args[1].trim().substring(5);
        args[2] = args[2].trim().substring(3);
        return args;
    }

    public static String[] parseDeadline(String input) {
        String[] args = input.trim().split("/");
        args[0] = args[0].trim();
        args[1] = args[1].trim().substring(3);
        return args;
    }

}