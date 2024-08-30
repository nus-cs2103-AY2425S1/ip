package julie.misc;

import julie.command.*;
import julie.exception.InvalidCommandException;
import julie.exception.JulieException;

/**
 * A class that encapsulates the methods used to parse user inputs to the Chat Bot.
 */
public class Parser {
    /** Private enum that encapsulates the valid commands allowed for user input */
    private enum CommandToken {
        MARK, UNMARK, LIST, DELETE,
        DEADLINE, TODO, EVENT,
        BYE;

        /**
         * Returns the corresponding enum when given a user input string.
         *
         * @param s The user input string.
         * @return The enum that corresponds to the user input.
         * @throws InvalidCommandException if the command is not supported by the Chat Bot.
         */
        public static CommandToken fromString(String s) throws InvalidCommandException {
            try {
                return CommandToken.valueOf(getFirstWord(s).toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new InvalidCommandException(s);
            }
        }
    }

    /**
     * Returns the corresponding command when given a user input string.
     *
     * @param input The user input string.
     * @return The command that corresponds with the user input.
     * @throws JulieException if the command is not supported by the Chat Bot.
     */
    public static Command parse(String input) throws JulieException {
        CommandToken cmd = CommandToken.fromString(input);
        return switch (cmd) {
            case BYE -> new ByeCommand(input);
            case LIST -> new ListCommand(input);
            case MARK -> new MarkCommand(input);
            case UNMARK -> new UnmarkCommand(input);
            case DELETE -> new DeleteCommand(input);
            case TODO -> new TodoCommand(input);
            case DEADLINE -> new DeadlineCommand(input);
            case EVENT -> new EventCommand(input);
        };
    }
    /**
     * Public method that extracts the first word from a string and returns it in uppercase.
     *
     * @param s the string to be extracted from
     * @return the first word in the line
     */
    public static String getFirstWord(String s) {
        int index = s.indexOf(' ');
        if (index > -1) { // Check if there is more than one word.
            return s.substring(0, index).trim(); // Extract first word.
        } else {
            return s; // Text is the first word itself.
        }
    }
}
