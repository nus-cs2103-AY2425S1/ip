package cookie.parser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;

import cookie.command.ByeCommand;
import cookie.command.Command;
import cookie.command.DeadlineCommand;
import cookie.command.DeleteCommand;
import cookie.command.EventCommand;
import cookie.command.FindCommand;
import cookie.command.ListCommand;
import cookie.command.MarkCommand;
import cookie.command.SetAliasCommand;
import cookie.command.ToDoCommand;
import cookie.command.UnmarkCommand;
import cookie.command.ViewAliasCommand;
import cookie.exception.CookieException;

/**
 * Parses user input and converts it into corresponding {@code Command} objects.
 */
public class Parser {

    private static final Hashtable<String, String> commandAliasesMap = new Hashtable<>();

    private static final HashSet<String> validCommands = new HashSet<>(Arrays.asList(
            "bye",
            "list",
            "find",
            "delete",
            "mark",
            "unmark",
            "todo",
            "deadline",
            "event",
            "set",
            "alias"
    ));

    /**
     * Extracts the command keyword from the input string.
     *
     * @param input the input string containing the command
     * @return the command keyword
     */
    public String parseCommand(String input) {
        String[] splitInput = input.split(" ", 2);
        return splitInput[0];
    }

    /**
     * Extracts the description or arguments from the input string.
     *
     * @param input the input string containing the description
     * @return the description or arguments
     */
    public String parseDescription(String input) {
        String[] splitInput = input.split(" ", 2);
        return (splitInput.length > 1) ? splitInput[1].trim() : "";
    }

    /**
     * Parses an integer from the input string.
     *
     * @param input the input string containing the integer
     * @return the integer value
     * @throws NumberFormatException if the input is not a valid integer
     */
    public int parseInteger(String input) {
        return Integer.parseInt(input);
    }

    /**
     * Extracts deadline details from the input string.
     *
     * @param input the input string containing the deadline details
     * @return an array containing the task description and the deadline
     * @throws CookieException if the deadline format is invalid
     */
    public String[] parseDeadline(String input) throws CookieException {
        String[] deadlineDetails = input.split(" /by ", 2);
        if (deadlineDetails.length < 2 || deadlineDetails[0].isEmpty() || deadlineDetails[1].isEmpty()) {
            throw new CookieException("Deadlines must include a task todo and a due date \n"
                    + "[task] /by [deadline]");
        }
        return deadlineDetails;
    }

    /**
     * Extracts event details from the input string.
     *
     * @param input String to be parsed.
     * @return Array containing event details of the event task.
     * @throws CookieException if the event format is invalid
     */
    public String[] parseEvent(String input) throws CookieException {
        String[] eventDetails = input.split(" /from | /to ");

        if (eventDetails.length < 2 || eventDetails[0].isEmpty()
                || eventDetails[1].isEmpty() || eventDetails[2].isEmpty()) {
            throw new CookieException("Events must include a task, a start time and an end time \n"
                    + "[task] /from [start] /to [end]");
        }

        return eventDetails;
    }

    /**
     * Extracts the alias command details from the input string.
     *
     * @param input the input string containing the alias command details
     * @return an array containing the original command and its alias
     * @throws CookieException if the alias format is invalid
     */
    public String[] parseAlias(String input) throws CookieException {
        String[] aliasCommand = input.split(" ", 2);

        if (aliasCommand.length < 2 || aliasCommand[0].isEmpty() || aliasCommand[1].trim().isEmpty()) {
            throw new CookieException("To set an alias, there must be a command and it's alias\n"
                    + "(set [command] [alias])");
        }

        return aliasCommand;
    }

    /**
     * Resolves the actual command from a possible alias.
     *
     * @param command the command keyword or alias
     * @return the actual command keyword
     * @throws CookieException if the command or alias is invalid
     */
    private String resolveCommand(String command) throws CookieException {
        if (!validCommands.contains(command)) {
            String resolvedCommand = commandAliasesMap.get(command);
            if (resolvedCommand == null) {
                throw new CookieException("Cookie does not understand this command. I'm sorry!!");
            }
            return resolvedCommand;
        }
        return command;
    }

    /**
     * Parses the user's input string and converts it into a corresponding {@code Command} object.
     * The method identifies the command type (e.g., "bye", "list", "find", "delete", "mark", etc.)
     * and processes any additional details like task descriptions, deadlines, or event times,
     * converting the input into specific command objects. Throws a {@code CookieException} for
     * invalid inputs or missing required details.
     *
     * @param input the input string entered by the user, which represents a command and its arguments
     * @return the {@code Command} object corresponding to the user's input
     * @throws CookieException if the input command is invalid, unrecognized, or missing required arguments
     */
    public Command parseInputToCommand(String input) throws CookieException {
        String command = parseCommand(input);
        String description = parseDescription(input);

        command = resolveCommand(command);

        switch(command) {
        case "bye":
            return new ByeCommand();

        case "list":
            return new ListCommand();

        case "find":
            if (description.isEmpty()) {
                throw new CookieException("Cookie does not know which task to find. \n "
                        + "(Please enter a word after \"find\")");
            }
            return new FindCommand(description);

        case "delete":
            if (description.isEmpty()) {
                throw new CookieException("Cookie does not know which task to delete.\n"
                        + "(Please enter an integer after \"delete\")");
            }

            int deleteIndex = parseInteger(description);

            return new DeleteCommand(deleteIndex);

        case "mark":
            if (description.isEmpty()) {
                throw new CookieException("Cookie does not know which task to mark.\n "
                        + "(Please enter an integer after \"mark\")");
            }
            int markIndex = parseInteger(description);
            return new MarkCommand(markIndex);

        case "unmark":
            if (description.isEmpty()) {
                throw new CookieException("Cookie does not know which task to mark.\n "
                        + "(Please enter an integer after \"mark\")");
            }
            int unmarkIndex = parseInteger(description);
            return new UnmarkCommand(unmarkIndex);

        case "todo":
            if (description.isEmpty()) {
                throw new CookieException("Please enter a task for you to do.");
            }

            return new ToDoCommand(description);

        case "deadline":
            String[] deadlineDetails = parseDeadline(description);

            return new DeadlineCommand(deadlineDetails[0], deadlineDetails[1]);

        case "event":
            String[] eventDetails = parseEvent(description);

            return new EventCommand(eventDetails[0], eventDetails[1], eventDetails[2]);

        case "set":
            String[] aliasCommand = parseAlias(description);

            if (!validCommands.contains(aliasCommand[0])) {
                throw new CookieException("The command you want to set as an alias does not exist!\n"
                        + "Here are the available commands: bye, list, find, delete, mark, unmark,"
                        + " todo, deadline, event, alias and set");
            }

            return new SetAliasCommand(aliasCommand[0], aliasCommand[1], Parser.commandAliasesMap);

        case "alias":
            return new ViewAliasCommand(Parser.commandAliasesMap);

        default:
            throw new CookieException("Cookie does not understand this command. I'm sorry!!");
        }
    }
}
