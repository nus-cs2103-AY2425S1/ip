package blitz;

/* My import */
import command.Command;
import command.CommandBye;
import command.CommandDeadline;
import command.CommandDelete;
import command.CommandEvent;
import command.CommandList;
import command.CommandMark;
import command.CommandTodo;
import command.CommandUnmark;

import exception.BlitzCommandDoesNotExistException;
import exception.BlitzException;
import exception.BlitzInvalidParameterMoreThanOneException;
import exception.BlitzInvalidParameterRegexException;
import exception.BlitzNoParameterException;

/* System import */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            String[] cont = command.split(" ", 2);
            String inst = cont[0];

            if (!Instruction.isCommandExist(inst)) {
                throw new BlitzCommandDoesNotExistException();
            }

            if (cont.length == 1 || cont[1].isBlank()) {
                throw new BlitzNoParameterException();
            }

            switch (inst) {
            case "mark":
                String[] markParam = cont[1].split(" ");

                if (markParam.length > 1) {
                    throw new BlitzInvalidParameterMoreThanOneException("mark [Integer]");
                }

                return new CommandMark(command, markParam[0]);
            case "unmark":
                String[] unmarkParam = cont[1].split(" ");

                if (unmarkParam.length > 1) {
                    throw new BlitzInvalidParameterMoreThanOneException("unmark [Integer]");
                }

                return new CommandUnmark(command, unmarkParam[0]);
            case "todo":
                return new CommandTodo(command, cont[1]);
            case "deadline":
                if (!isRegexMatched(
                        "^.+ \\/by (19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) (0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$", command)) {
                    throw new BlitzInvalidParameterRegexException("deadline [Task name] /by [yyyy-mm-dd hhmm]");
                }

                String[] deadlineParams = cont[1].split(" /by ");

                return new CommandDeadline(command, deadlineParams);
            case "event":
                if (!isRegexMatched(
                        "^.+ \\/from (19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) (0[0-9]|1[0-9]|2[0-3])[0-5][0-9]" +
                                " \\/to (19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) (0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$", command)) {
                    throw new BlitzInvalidParameterRegexException("event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
                }

                String[] param1 = cont[1].split(" /from ");
                String[] param2 = param1[1].split(" /to ");
                String[] eventParams = {param1[0], param2[0], param2[1]};

                return new CommandEvent(command, eventParams);
            case "delete":
                String[] deleteParam = cont[1].split(" ");

                if (deleteParam.length > 1) {
                    throw new BlitzInvalidParameterMoreThanOneException("Delete [Integer]");
                }

                return new CommandDelete(command, deleteParam[0]);
            case "find":
                return new CommandFind(command, cont[1]);
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
