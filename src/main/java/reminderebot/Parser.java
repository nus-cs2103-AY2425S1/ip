package reminderebot;

import java.util.HashSet;
import java.util.Scanner;

import reminderebot.commands.ByeCommand;
import reminderebot.commands.Command;
import reminderebot.commands.DeadlineCommand;
import reminderebot.commands.DeleteCommand;
import reminderebot.commands.EventCommand;
import reminderebot.commands.FindCommand;
import reminderebot.commands.HelpCommand;
import reminderebot.commands.ListCommand;
import reminderebot.commands.MarkCommand;
import reminderebot.commands.ToDoCommand;
import reminderebot.commands.UnmarkCommand;

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

        // if the input does not contain a command
        if (!getEnums().contains(command)) {
            throw new ReminderebotException("I'm sorry, but I don't know what that means. :(\n"
                    + "Please enter a command below:\n"
                    + " help\n bye\n list\n mark <int>\n unmark <int>\n find <keyword>\n todo <taskname>\n"
                    + " deadline <taskname> /by <duedate>\n event <name> /from <datetime> /to <datetime>");
        }

        switch (Commands.valueOf(command)) {
        case BYE:
            return new ByeCommand();
            // unreachable
        case LIST:
            return new ListCommand();
            // unreachable
        case MARK: {
            String[] markInfo = input.split(" ");
            boolean invalidIndex = !scan.hasNextInt();
            boolean invalidLength = markInfo.length != 2;
            if (invalidIndex || invalidLength) {
                throw new ReminderebotException("Mark items by selecting their position.\n"
                        + "Syntax: mark <int>");
            }
            int idx = scan.nextInt();
            return new MarkCommand(idx);
        }
            // unreachable
        case UNMARK: {
            String[] unmarkInfo = input.split(" ");
            boolean invalidIndex = !scan.hasNextInt();
            boolean invalidLength = unmarkInfo.length != 2;
            if (invalidIndex || invalidLength) {
                throw new ReminderebotException("Unmark items by selecting their position.\n"
                        + "Syntax: unmark <int>");
            }
            int idx = scan.nextInt();
            return new UnmarkCommand(idx);
        }
            // unreachable
        case DELETE: {
            String[] deleteInfo = input.split(" ");
            boolean invalidIndex = !scan.hasNextInt();
            boolean invalidLength = deleteInfo.length != 2;
            if (invalidIndex || invalidLength) {
                throw new ReminderebotException("Delete items by selecting their position.\n"
                        + "Syntax: delete <int>");
            }
            int idx = scan.nextInt();
            return new DeleteCommand(idx);
        }
            // unreachable
        case TODO: {
            String[] todoInfo = input.split(" ");
            boolean invalidLength = todoInfo.length < 2;
            if (invalidLength) {
                throw new ReminderebotException("The description of a todo cannot be empty.\n"
                        + "Syntax: todo <taskname>");
            }
            String strToDo = scan.nextLine();
            return new ToDoCommand(strToDo);
        }
            // unreachable
        case DEADLINE: {
            String[] dlInfo = input.split(" ");
            String[] dlBy = input.split("/by ");
            boolean invalidInfoLength = dlInfo.length < 2;
            boolean invalidByLength = dlBy.length < 2;
            if (invalidInfoLength || invalidByLength) {
                throw new ReminderebotException("The description of a deadline cannot be empty.\n"
                        + "Syntax: deadline <taskname> /by <duedate>");
            }
            String strDeadline = scan.nextLine();
            return new DeadlineCommand(strDeadline);
        }
            // unreachable
        case EVENT: {
            String[] eventInfo = input.split(" ");
            String[] eventFrom = input.split("/from ");
            String[] eventTo = input.split("/to ");
            boolean invalidInfoLength = eventInfo.length < 2;
            boolean invalidFromLength = eventFrom.length < 2;
            boolean invalidToLength = eventTo.length < 2;
            if (invalidInfoLength || invalidFromLength || invalidToLength) {
                throw new ReminderebotException("The description of a event cannot be empty.\n"
                        + "Syntax: event <name> /from <datetime> /to <datetime>");
            }
            String strEvent = scan.nextLine();
            return new EventCommand(strEvent);
        }
            // unreachable
        case FIND: {
            String[] findInfo = input.split(" ");
            boolean invalidInfo = !scan.hasNext();
            boolean invalidLength = findInfo.length != 2;
            if (invalidInfo || invalidLength) {
                throw new ReminderebotException("Find items by entering a keyword.\n"
                        + "Syntax: find <keyword>");
            }
            String keyword = scan.next();
            return new FindCommand(keyword);
        }
        case HELP:
            return new HelpCommand();
            // unreachable
        default:
            throw new RuntimeException("This should not happen");
        }
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
