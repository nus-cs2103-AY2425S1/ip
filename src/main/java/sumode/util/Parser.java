package sumode.util;

import sumode.exception.MissingDetailsException;
import sumode.exception.WrongSyntaxForCommandException;

/**
 * In charge of parsing jobs in SumoDE.
 * <p>
 * All methods are static so no initialisation required.
 */
public class Parser {

    /**
     * Returns an array of String of size 2 with first String being command and
     * second String being additional details following the command.
     * <p>
     * If no additional details are given, the second String will be an empty String.
     *
     * @param input a line of user input to chatbot
     * @return a String array of size 2
     */
    public static String[] splitCommandAndAction(String input) {
        int spaceLocation = input.indexOf(" ");
        String commandString;
        String item;

        if (spaceLocation == -1) {
            commandString = input;
            item = "";
        } else {
            commandString = input.substring(0, spaceLocation);
            item = input.substring(spaceLocation + 1);
        }
        return new String[] {commandString, item};
    }

    /**
     * Returns an array of String of size 2 with first String being task details and
     * second String being deadline to meet
     * <p>
     * Second String may of may not be yyyy-mm-dd format
     * @param item input of user that come after command
     * @return a String array of size 2
     * @throws WrongSyntaxForCommandException thrown when Wrong Syntax for command is given
     */
    public static String[] parseDeadline(String item) throws WrongSyntaxForCommandException, MissingDetailsException {
        int spaceLocation = item.indexOf(" /by ");
        if (spaceLocation == -1) {
            throw new WrongSyntaxForCommandException(Command.DEADLINE);
        }
        String name = item.substring(0, spaceLocation).trim();
        String due = item.substring(spaceLocation + 5).trim();

        if (name.isEmpty() | due.isEmpty()) {
            throw new MissingDetailsException(Command.DEADLINE);
        }
        return new String[] {name, due};
    }

    /**
     * Returns an array of String of size 3 with first String being task details,
     * second String being start date and
     * third String being end date
     * <p>
     * Second/Third String may of may not be yyyy-mm-dd format
     * @param item input of user that come after command
     * @return a String array of size 3
     * @throws WrongSyntaxForCommandException thrown when Wrong Syntax for command is given
     */
    public static String[] parseEvent(String item) throws WrongSyntaxForCommandException, MissingDetailsException {
        int fromLocation = item.indexOf(" /from ");
        int toLocation = item.indexOf(" /to ");
        String name;
        String start;
        String end;
        if (fromLocation == -1 || toLocation == -1) {
            throw new WrongSyntaxForCommandException(Command.EVENT);
        }
        if (fromLocation < toLocation) {
            name = item.substring(0, fromLocation).trim();
            start = item.substring(fromLocation + 7, toLocation).trim();
            end = item.substring(toLocation + 5).trim();
        } else {
            name = item.substring(0, toLocation).trim();
            end = item.substring(toLocation + 5, fromLocation).trim();
            start = item.substring(fromLocation + 7).trim();
        }
        if (name.isEmpty() | start.isEmpty() | end.isEmpty()) {
            throw new MissingDetailsException(Command.EVENT);
        }
        return new String[] {name, start, end};
    }
}
