package reminderebot;

import reminderebot.commands.*;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Parser is a utility class to parse inputs
 */
public class Parser {
    /**
     * Checks if the input is valid and returns the correct command.
     * @param input
     * @return Commands
     * @throws ReminderebotException
     */
    public static Command parse(String input) throws ReminderebotException {
        Scanner scan = new Scanner(input.trim());
        String command = scan.next().toUpperCase();
        if (getEnums().contains(command)) { // if the input contains a command
            switch (Commands.valueOf(command)) {
            case BYE:
                return new ByeCommand();
                // unreachable
            case LIST:
                return new ListCommand();
                // unreachable
            case MARK: // if the mark command lacks required arguments
                String[] markInfo = input.split(" ");
                if (scan.hasNextInt() && markInfo.length==2) { // mark has necessary requirements
                    // input is valid
                    int idx = scan.nextInt();
                    return new MarkCommand(idx);
                } else {
                    throw new ReminderebotException("Mark items by selecting their position.\n" +
                            "Syntax: mark <int>");
                }
                // unreachable
            case UNMARK: // if the unmark command lacks required arguments
                String[] unmarkInfo = input.split(" ");
                if (scan.hasNextInt() && unmarkInfo.length==2) { // mark has necessary requirements
                    // input is valid
                    int idx = scan.nextInt();
                    return new UnmarkCommand(idx);
                } else {
                    throw new ReminderebotException("Unmark items by selecting their position.\n" +
                            "Syntax: unmark <int>");
                }
                // unreachable
            case DELETE: // if the delete command lacks required arguments
                String[] deleteInfo = input.split(" ");
                if (scan.hasNextInt() && deleteInfo.length==2) { // mark has necessary requirements
                    // input is valid
                    int idx = scan.nextInt();
                    return new DeleteCommand(idx);
                } else {
                    throw new ReminderebotException("Delete items by selecting their position.\n" +
                            "Syntax: delete <int>");
                }
                // unreachable
            case TODO: // if the to-do command lacks required arguments
                String[] todoInfo = input.split(" ");
                if (todoInfo.length < 2) {
                    throw new ReminderebotException("The description of a todo cannot be empty.\n" +
                            "Syntax: todo <taskname>");
                }
                String strToDo = scan.nextLine();
                return new ToDoCommand(strToDo);
                // unreachable
            case DEADLINE: // if the deadline command lacks required arguments
                String[] dlInfo = input.split(" ");
                String[] dlBy = input.split("/by ");
                if (dlInfo.length < 2 || dlBy.length < 2) {
                    throw new ReminderebotException("The description of a deadline cannot be empty.\n" +
                            "Syntax: deadline <taskname> /by <duedate>");
                }
                String strDeadline = scan.nextLine();
                return new DeadlineCommand(strDeadline);
                // unreachable
            case EVENT: // if the event command lacks required arguments
                String[] eventInfo = input.split(" ");
                String[] eventFrom = input.split("/from ");
                String[] eventTo = input.split("/to ");
                if (eventInfo.length < 2 || eventFrom.length < 2 || eventTo.length < 2) {
                    throw new ReminderebotException("The description of a event cannot be empty.\n" +
                            "Syntax: event <name> /from <datetime> /to <datetime>");
                }
                String strEvent = scan.nextLine();
                return new EventCommand(strEvent);
                // unreachable
            case FIND: // if the find command lacks required arguments
                String[] findInfo = input.split(" ");
                if (scan.hasNext() && findInfo.length==2) { // mark has necessary requirements
                    // input is valid
                    String keyword = scan.next();
                    return new FindCommand(keyword);
                } else {
                    throw new ReminderebotException("Find items by entering a keyword.\n" +
                            "Syntax: find <keyword>");
                }
            }
        }
        // exception: command not found
        throw new ReminderebotException("I'm sorry, but I don't know what that means. :(\n" +
                    "Please enter a command below:\n" +
                    " bye\n list\n mark <int>\n unmark <int>\n find <keyword>\n todo <taskname>\n" +
                    " deadline <taskname> /by <duedate>\n event <name> /from <datetime> /to <datetime>");
    }

    /**
     * Helper function to get all enum values as a HashSet of Strings
     * @return a hashset of enum values
     */
    public static HashSet<String> getEnums() {
        HashSet<String> values = new HashSet<String>();
        for (Commands c : Commands.values()) {
            values.add(c.name());
        }
        return values;
    }
}
