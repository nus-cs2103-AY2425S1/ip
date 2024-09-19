package winner;

/**
 * Represents a Parser to handle the parsing of user inputs and directs the appropriate actions to be taken
 * based on the command given by the user.
 */
public class Parser {

    public static Command parseInput(String input) {
        if (input.matches("(?i)hi|hello")) {
            return new Command(CommandType.HI_AGAIN, input);
        } else if (input.matches("(?i)\\bhelp\\b")) {
            return new Command(CommandType.HELP, input);
        } else if (input.matches("(?i).*\\btodo\\b.*")) {
            return new Command(CommandType.TODO, input);
        } else if (input.matches("(?i).*\\bdeadline\\b.*")) {
            return new Command(CommandType.DEADLINE, input);
        } else if (input.matches("(?i).*\\bevent\\b.*")) {
            return new Command(CommandType.EVENT, input);
        } else if (input.matches("(?i).*\\blist\\b.*")) {
            return new Command(CommandType.LIST, input);
        } else if (input.matches("(?i).*\\bmark\\b.*")) {
            return new Command(CommandType.MARK, input);
        } else if (input.matches("(?i).*\\bunmark\\b.*")) {
            return new Command(CommandType.UNMARK, input);
        } else if (input.matches("(?i).*\\bdelete\\b.*")) {
            return new Command(CommandType.DELETE, input);
        } else if (input.matches("(?i).*\\bfind\\b.*")) {
            return new Command(CommandType.FIND, input);
        } else if (input.matches("(?i).*bye.*")) {
            return new Command(CommandType.BYE, input);
        }
        return new Command(CommandType.UNKNOWN, input);
    }

}
