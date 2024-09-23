package beechat.util;

import beechat.command.*;
import beechat.exception.BeeException;

/**
 * Parses user input and converts it into a command to be executed.
 * Reads the user's inputs and creates the appropriate Command objects.
 */
public class Parser {

    /**
     * Parses the user input into a specific Command object.
     *
     * @param fullCommand The full user input string to parse.
     * @return A Command object corresponding to the user's input.
     * @throws Exception If the user input has an invalid command word.
     */
    public static Command parse(String fullCommand) throws Exception {
        String[] words = fullCommand.split(" ", 2);
        String commandWord = words[0];

        switch (commandWord) {
            case "bye":
                return new LeaveCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(Integer.parseInt(words[1]));
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(words[1]));
            case "delete":
                return new DeleteCommand(Integer.parseInt(words[1]));
            case "find":
                return new FindCommand(words[1]);
            case "help":
                return new HelpCommand();
            case "todo":
                return new AddCommand(fullCommand);
            case "deadline":
                return new AddCommand(fullCommand);
            case "event":
                return new AddCommand(fullCommand);
            default:
                throw new BeeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
