package blitz;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import command.Command;
import command.CommandBye;
import command.CommandDeadline;
import command.CommandDelete;
import command.CommandEvent;
import command.CommandFind;
import command.CommandList;
import command.CommandMark;
import command.CommandTodo;
import command.CommandUnmark;

import exception.BlitzCommandDoesNotExistException;
import exception.BlitzException;
import exception.BlitzInvalidParameterMoreThanOneException;
import exception.BlitzInvalidParameterRegexException;
import exception.BlitzNoParameterException;

/**
 * Responsibles to parse command String and convert it into corresponding Command Object.
 */
public class Parser {
    /**
     * Parses command in String and return a Command object.
     *
     * @param command Command String to be parsed. This String should follow specific formats.
     * @return The Command object corresponding to the parsed command String.
     * @throws BlitzException If the command String is invalid or command does not exist.
     */
    public static Command parse(String command) throws BlitzException {
        if (isRegexMatched("^list\\s*$", command)) {
            return new CommandList("list");
        } else if (isRegexMatched("^bye\\s*$", command)) {
            return new CommandBye("bye");
        } else {
            String[] commandParts = command.split(" ", 2);
            String commandName = commandParts[0];

            if (!Instruction.isValidCommand(commandName)) {
                throw new BlitzCommandDoesNotExistException();
            }

            if (commandParts.length == 1 || commandParts[1].isBlank()) {
                throw new BlitzNoParameterException();
            }

            switch (commandName) {
            case "mark":
                String[] markParameters = commandParts[1].split(" ");

                if (markParameters.length > 1) {
                    throw new BlitzInvalidParameterMoreThanOneException("mark [Integer]");
                }

                assert markParameters.length == 1 : "Mark command should have exactly one parameter";

                return new CommandMark(command, markParameters[0]);
            case "unmark":
                String[] unmarkParameters = commandParts[1].split(" ");

                if (unmarkParameters.length > 1) {
                    throw new BlitzInvalidParameterMoreThanOneException("unmark [Integer]");
                }

                assert unmarkParameters.length == 1 : "Unmark command should have exactly one parameter";

                return new CommandUnmark(command, unmarkParameters[0]);
            case "todo":
                assert commandParts.length == 2 : "Todo command should have exactly one parameter";

                return new CommandTodo(command, commandParts[1]);
            case "deadline":
                if (!isRegexMatched(
                        "^.+ \\/by (19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])"
                                + " (0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$",
                        command)) {
                    throw new BlitzInvalidParameterRegexException("deadline [Task name] /by [yyyy-mm-dd hhmm]");
                }

                String[] deadlineParameters = commandParts[1].split(" /by ");

                assert deadlineParameters.length == 2 : "Deadline command should have exactly two parameters separated by '/by'";

                return new CommandDeadline(command, deadlineParameters[0], deadlineParameters[1]);
            case "event":
                if (!isRegexMatched(
                        "^.+ \\/from (19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) "
                                + "(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]"
                                + " \\/to (19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) "
                                + "(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$",
                        command)) {
                    throw new BlitzInvalidParameterRegexException(
                            "event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
                }

                String[] fromParameters = commandParts[1].split(" /from ");
                String[] toParameters = fromParameters[1].split(" /to ");

                assert fromParameters.length == 2 : "Event command should have exactly two parameters separated by '/from'";
                assert toParameters.length == 2 : "Event command should have exactly two parameters separated by '/to'";

                return new CommandEvent(command, fromParameters[0], toParameters[0], toParameters[1]);
            case "delete":
                String[] deleteParameters = commandParts[1].split(" ");

                if (deleteParameters.length > 1) {
                    throw new BlitzInvalidParameterMoreThanOneException("Delete [Integer]");
                }

                assert deleteParameters.length == 1 : "Delete command should have exactly one parameter";

                return new CommandDelete(command, deleteParameters[0]);
            case "find":
                assert commandParts.length == 2 : "Find command should have exactly one parameter";

                return new CommandFind(command, commandParts[1]);
            default:
                throw new BlitzCommandDoesNotExistException();
            }
        }
    }

    /**
     * Checks if given String (inp) matches the given regex pattern String (reg).
     *
     * @param reg The regex pattern to be used for matching.
     * @param inp The input String to be checked against the regex pattern.
     * @return True if input String matches the regex pattern, false otherwise.
     */
    private static boolean isRegexMatched(String reg, String inp) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(inp);

        return matcher.find();
    }
}
