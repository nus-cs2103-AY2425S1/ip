package bobby.utils;

import bobby.exception.BobbyException;
import bobby.exception.UnknownCommandException;

/**
 * The Parser class deals with the input of the user.
 */
public class Parser {

    /**
     * Parses the user's input and returns the correct Command.
     *
     * @param userInput Input of the user.
     * @return Command after parsing the user's input.
     * @throws UnknownCommandException If the user does not input a valid command.
     */
    public static Command parse(String userInput) throws UnknownCommandException {
        String[] splitInput = userInput.split(" ");
        if (splitInput[0].equalsIgnoreCase("bye")) {
            return Command.BYE;
        }
        if (splitInput[0].equalsIgnoreCase("list")) {
            return Command.LIST;
        }
        if (splitInput[0].equalsIgnoreCase("todo")) {
            return Command.TODO;
        }
        if (splitInput[0].equalsIgnoreCase("deadline")) {
            return Command.DEADLINE;
        }
        if (splitInput[0].equalsIgnoreCase("event")) {
            return Command.EVENT;
        }
        if (splitInput[0].equalsIgnoreCase("mark")) {
            return Command.MARK;
        }
        if (splitInput[0].equalsIgnoreCase("unmark")) {
            return Command.UNMARK;
        }
        if (splitInput[0].equalsIgnoreCase("delete")) {
            return Command.DELETE;
        }
        if (splitInput[0].equalsIgnoreCase("find")) {
            return Command.FIND;
        }
        if (splitInput[0].equalsIgnoreCase(("archive"))) {
            return Command.ARCHIVE;
        }
        if (splitInput[0].equalsIgnoreCase("listarchive")) {
            return Command.LISTARCHIVE;
        }
        throw new UnknownCommandException();
    }

    /**
     * Parses user's input and return the task number.
     *
     * @param input Input of the user.
     * @param commandLength Length of the Command.
     * @return The task number.
     * @throws BobbyException If the user does not provide a number.
     */
    public static int parseNumber(String input, int commandLength) throws BobbyException {
        try {
            String inputWithoutCommand = input.substring(commandLength).trim();
            return Integer.parseInt(inputWithoutCommand);
        } catch (NumberFormatException e) {
            throw new BobbyException("My apologies. I cannot decipher what is that number!");
        }
    }
}
