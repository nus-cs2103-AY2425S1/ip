package blitz;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import exception.BlitzCommandDoesNotExistException;
import exception.BlitzException;
import exception.BlitzInvalidParameterMoreThanOneException;
import exception.BlitzInvalidParameterRegexException;
import exception.BlitzNoParameterException;
import exception.BlitzProhibitedCharacterException;

/**
 * Handles parsing of command String and convert it into corresponding Command Object.
 */
public class Parser {
    private static final Pattern LIST_PATTERN = Pattern.compile("^list\\s*$");
    private static final Pattern BYE_PATTERN = Pattern.compile("^bye\\s*$");
    private static final Pattern DEADLINE_PATTERN = Pattern.compile(
            "^.+ /by (19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) (0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$");
    private static final Pattern EVENT_PATTERN = Pattern.compile(
            "^.+ /from (19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) (0[0-9]|1[0-9]|2[0-3])[0-5][0-9]"
            + " /to (19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) (0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$");

    /**
     * Parses command in String and return a Command object.
     *
     * @param command Command String to be parsed. This String should follow specific formats.
     * @return The Command object corresponding to the parsed command String.
     * @throws BlitzException If the command String is invalid or command does not exist.
     */
    public static Command parse(String command) throws BlitzException {
        if (isListCommand(command)) {
            return new ListCommand("list");
        }
        if (isByeCommand(command)) {
            return new ByeCommand("bye");
        }

        String[] commandParts = command.split(" ", 2);
        String commandName = commandParts[0];

        if (!Instruction.isValidCommand(commandName)) {
            throw new BlitzCommandDoesNotExistException();
        }

        if (commandParts.length == 1) {
            throw new BlitzNoParameterException();
        }

        String commandBody = commandParts[1];

        if (commandBody.isBlank()) {
            throw new BlitzNoParameterException();
        }

        return parseCommands(command, commandName, commandBody);
    }

    /**
     * Checks if the given input (String) matches the given pattern (Pattern).
     *
     * @param pattern The regex pattern to be used for matching.
     * @param input The input String to be checked against the regex pattern.
     * @return True if input String matches the regex pattern, false otherwise.
     */
    private static boolean isRegexMatched(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);

        return matcher.find();
    }

    /**
     * Checks if the given command (String) matches the pattern for the 'list' command.
     *
     * @param command The String to be checked.
     * @return True if the pattern matches, false otherwise.
     */
    private static boolean isListCommand(String command) {
        return isRegexMatched(LIST_PATTERN, command);
    }

    /**
     * Checks if the given command (String) matches the pattern for the 'bye' command.
     *
     * @param command The String to be checked.
     * @return True if the pattern matches, false otherwise.
     */
    private static boolean isByeCommand(String command) {
        return isRegexMatched(BYE_PATTERN, command);
    }

    /**
     * Parses the given command string and creates appropriate Command object.
     *
     * @param command The full command string.
     * @param commandName The name of the command.
     * @param commandBody The parameter of the command.
     * @return A Command object corresponding to the specified commandName.
     * @throws BlitzException If error occurs during the parsing process or commandName does not match any existing
     *     command.
     */
    private static Command parseCommands(String command, String commandName, String commandBody) throws BlitzException {
        switch (commandName) {
        case "mark":
            return parseMarkCommand(command, commandBody);
        case "unmark":
            return parseUnmarkCommand(command, commandBody);
        case "todo":
            return parseTodoCommand(command, commandBody);
        case "deadline":
            return parseDeadlineCommand(command, commandBody);
        case "event":
            return parseEventCommand(command, commandBody);
        case "delete":
            return parseDeleteCommand(command, commandBody);
        case "find":
            return parseFindCommand(command, commandBody);
        default:
            throw new BlitzCommandDoesNotExistException();
        }
    }

    /**
     * Parses the "mark" command and returns a corresponding MarkCommand object.
     *
     * @param command The full command string.
     * @param commandBody The parameter of the command.
     * @return A corresponding MarkCommand object.
     * @throws BlitzException If there is more than one parameter.
     */
    private static Command parseMarkCommand(String command, String commandBody) throws BlitzException {
        String[] markParameters = commandBody.split(" ");

        if (markParameters.length > 1) {
            throw new BlitzInvalidParameterMoreThanOneException("mark [Index]");
        }

        assert markParameters.length == 1 : "Mark command should have exactly one parameter";

        String markIndex = markParameters[0];

        return new MarkCommand(command, markIndex);
    }

    /**
     * Parses the "unmark" command and returns a corresponding UnmarkCommand object.
     *
     * @param command The full command string.
     * @param commandBody The parameter of the command.
     * @return A corresponding UnmarkCommand object.
     * @throws BlitzException If there is more than one parameter.
     */
    private static Command parseUnmarkCommand(String command, String commandBody) throws BlitzException {
        String[] unmarkParameters = commandBody.split(" ");

        if (unmarkParameters.length > 1) {
            throw new BlitzInvalidParameterMoreThanOneException("unmark [Index]");
        }

        assert unmarkParameters.length == 1 : "Unmark command should have exactly one parameter";

        String unmarkIndex = unmarkParameters[0];

        return new UnmarkCommand(command, unmarkIndex);
    }

    /**
     * Parses the "todo" command and returns a corresponding TodoCommand object.
     *
     * @param command The full command string.
     * @param commandBody The parameter of the command.
     * @return A corresponding TodoCommand object.
     * @throws BlitzException If description contains the special characters "::".
     */
    private static Command parseTodoCommand(String command, String commandBody) throws BlitzException {
        String todoDescription = commandBody;

        if (todoDescription.contains("::")) {
            throw new BlitzProhibitedCharacterException("::");
        }

        return new TodoCommand(command, todoDescription);
    }

    /**
     * Parses the "deadline" command and returns a corresponding DeadlineCommand object.
     *
     * @param command The full command string.
     * @param commandBody The parameter of the command.
     * @return A corresponding DeadlineCommand object.
     * @throws BlitzException If the command string does not match the expected pattern or description contains the
     *     special characters "::".
     */
    private static Command parseDeadlineCommand(String command, String commandBody) throws BlitzException {
        if (!isRegexMatched(DEADLINE_PATTERN, command)) {
            throw new BlitzInvalidParameterRegexException("deadline [Task description] /by [yyyy-mm-dd hhmm]");
        }

        String[] deadlineParameters = commandBody.split(" /by ");

        assert deadlineParameters.length == 2
                : "Deadline command should have exactly two parameters separated by '/by'";

        String deadlineDescription = deadlineParameters[0];
        String byParameterValue = deadlineParameters[1];

        if (deadlineDescription.contains("::")) {
            throw new BlitzProhibitedCharacterException("::");
        }

        return new DeadlineCommand(command, deadlineDescription, byParameterValue);
    }

    /**
     * Parses the "event" command and returns a corresponding EventCommand object.
     *
     * @param command The full command string.
     * @param commandBody The parameter of the command.
     * @return A corresponding EventCommand object.
     * @throws BlitzException If the command string does not match the expected pattern  or description contains the
     *     special characters "::".
     */
    private static Command parseEventCommand(String command, String commandBody) throws BlitzException {
        if (!isRegexMatched(EVENT_PATTERN, command)) {
            throw new BlitzInvalidParameterRegexException(
                    "event [Task description] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
        }

        String[] fromParameters = commandBody.split(" /from ");
        String[] toParameters = fromParameters[1].split(" /to ");

        assert fromParameters.length == 2
                : "Event command should have exactly two parameters separated by '/from'";
        assert toParameters.length == 2 : "Event command should have exactly two parameters separated by '/to'";

        String eventDescription = fromParameters[0];
        String fromParameterValue = toParameters[0];
        String toParameterValue = toParameters[1];

        if (eventDescription.contains("::")) {
            throw new BlitzProhibitedCharacterException("::");
        }

        return new EventCommand(command, eventDescription, fromParameterValue, toParameterValue);
    }

    /**
     * Parses the "delete" command and returns a corresponding DeleteCommand object.
     *
     * @param command The full command string.
     * @param commandBody The parameter of the command.
     * @return A corresponding DeleteCommand object.
     * @throws BlitzException If there is more than one parameter.
     */
    private static Command parseDeleteCommand(String command, String commandBody) throws BlitzException {
        String[] deleteParameters = commandBody.split(" ");

        if (deleteParameters.length > 1) {
            throw new BlitzInvalidParameterMoreThanOneException("delete [Index]");
        }

        assert deleteParameters.length == 1 : "Delete command should have exactly one parameter";

        String deleteIndex = deleteParameters[0];

        return new DeleteCommand(command, deleteIndex);
    }

    /**
     * Parses the "find" command and returns a corresponding FindCommand object.
     *
     * @param command The full command string.
     * @param commandBody The parameter of the command.
     * @return A corresponding FindCommand object.
     */
    private static Command parseFindCommand(String command, String commandBody) {
        String keywordsToFind = commandBody;

        return new FindCommand(command, keywordsToFind);
    }
}
