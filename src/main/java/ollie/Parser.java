package ollie;

import ollie.exception.OllieException;
import ollie.exception.UnknownTaskTypeException;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses the task number from the user's command.
     *
     * @param userCommand  The command entered by the user.
     * @param prefixLength The length of the prefix to trim.
     * @return The parsed task number.
     * @throws OllieException If the task number is invalid.
     */
    public static int parseTaskNumber(String userCommand, int prefixLength) throws OllieException {
        try {
            return Integer.parseInt(userCommand.substring(prefixLength).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new OllieException("Please enter a valid task number! ☺");
        }
    }

    /**
     * Parses the keyword from the user's command.
     *
     * @param userCommand to find the keyword.
     * @return string with keyword.
     */
    public static String parseKeyword(String userCommand) throws OllieException {
        String[] words = userCommand.split(" ", 2);
        if (words.length < 2) {
            throw new OllieException("The find command requires a keyword.");
        }
        return words[1].trim();
    }

    /**
     * Parses the user's command and returns the appropriate action.
     *
     * @param userCommand The command entered by the user.
     * @return The type of command parsed.
     * @throws OllieException If the command is not recognized.
     */
    public static Command parse(String userCommand) throws OllieException {
        if (userCommand.equals("hello") || userCommand.equals("hi")) {
            return Command.GREETING;
        }
        if (userCommand.equals("bye")) {
            return Command.EXIT;
        }
        if (userCommand.equals("list")) {
            return Command.LIST;
        }
        if (userCommand.startsWith("mark ")) {
            return Command.MARK;
        }
        if (userCommand.startsWith("unmark ")) {
            return Command.UNMARK;
        }
        if (userCommand.startsWith("delete ")) {
            return Command.DELETE;
        }
        if (userCommand.startsWith("find ")) {
            return Command.FIND;
        }
        if (userCommand.startsWith("todo")) {
            return Command.TODO;
        }
        if (userCommand.startsWith("deadline")) {
            return Command.DEADLINE;
        }
        if (userCommand.startsWith("event")) {
            return Command.EVENT;
        }
        throw new UnknownTaskTypeException();
    }
}
