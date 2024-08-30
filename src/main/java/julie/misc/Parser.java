package julie.misc;
import julie.command.*;
import julie.exception.InvalidCommandException;
import julie.exception.JulieException;

public class Parser {
    public enum CommandToken {
        MARK, UNMARK, LIST, DELETE,
        DEADLINE, TODO, EVENT,
        BYE;

        public static CommandToken fromString(String s) throws InvalidCommandException {
            try {
                return CommandToken.valueOf(getFirstWord(s).toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new InvalidCommandException(s);
            }
        }
    }
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
     * Public method that extracts the first word from a string and returns it in uppercase
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
