import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static Command parse(String command) throws BlitzException {
        if (command.equals("list")) {
            return new CommandList(command);
        } else {
            String[] cont = command.split(" ", 2);
            String inst = cont[0];

            if (!Instruction.checkCommandExist(inst)) {
                throw new BlitzCommandDoesNotExistException();
            }

            if (cont.length == 1 || cont[1].isBlank()) {
                throw new BlitzNoParameterException();
            }

            switch (inst) {
            case "mark":
                String[] markParam = cont[1].split(" ");
                if (markParam.length > 1) {
                    throw new BlitzInvalidParameterMoreThanOneException("mark" + " [Integer]");
                }

                return new CommandMark(command, markParam[0]);
            case "unmark":
                String[] unmarkParam = cont[1].split(" ");
                if (unmarkParam.length > 1) {
                    throw new BlitzInvalidParameterMoreThanOneException("unmark" + " [Integer]");
                }

                return new CommandUnmark(command, unmarkParam[0]);
            case "todo":
                return new CommandTodo(command, cont[1]);
            case "deadline":
                if (!regexChecker(".+ \\/by (19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) (0[0-9]|1[0-9]|2[0-3])[0-5][0-9]", command)) {
                    throw new BlitzInvalidParameterRegexException("deadline [Task name] /by [yyyy-mm-dd hhmm]");
                }

                String[] param = cont[1].split(" /by ");

                if (param[0].contains("/by") || param[1].contains("/by")) {
                    throw new BlitzInvalidParameterRepeatedFlagException("/by", "deadline [Task name] /by [yyyy-mm-dd hhmm]");
                }

                if (param[1].isBlank()) {
                    throw new BlitzInvalidParameterMissingContentException("/by", "deadline [Task name] /by [yyyy-mm-dd hhmm]");
                } else if (param[0].isBlank()) {
                    throw new BlitzInvalidParameterMissingContentException("[Task name]", "deadline [Task name] /by [yyyy-mm-dd hhmm]");
                }

                return new CommandDeadline(command, param);
            case "event":
                if (!regexChecker(".+ \\/from (19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) (0[0-9]|1[0-9]|2[0-3])[0-5][0-9] \\/to (19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) (0[0-9]|1[0-9]|2[0-3])[0-5][0-9]", command)) {
                    throw new BlitzInvalidParameterRegexException("event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
                }

                String[] param1 = cont[1].split(" /from ");
                String[] param2 = param1[1].split(" /to ");

                if (param1[0].contains("/from") || param1[1].contains("/from")) {
                    throw new BlitzInvalidParameterRepeatedFlagException("/from", "event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
                } else if (param2[0].contains("/to") || param2[1].contains("/to")) {
                    throw new BlitzInvalidParameterRepeatedFlagException("/to", "event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
                } else if (param1[0].isBlank()) {
                    throw new BlitzInvalidParameterMissingContentException("[Task name]", "event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
                } else if (param2[0].isBlank()) {
                    throw new BlitzInvalidParameterMissingContentException("/from", "event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
                } else if (param2[1].isBlank()) {
                    throw new BlitzInvalidParameterMissingContentException("/to", "event [Task name] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]");
                }

                String[] params = {param1[0], param2[0], param2[1]};
                return new CommandEvent(command, params);
            case "delete":
                String[] deleteParam = cont[1].split(" ");
                if (deleteParam.length > 1) {
                    throw new BlitzInvalidParameterMoreThanOneException("Delete [Integer]");
                }
                return new CommandDelete(command, deleteParam[0]);
            default:
                throw new BlitzCommandDoesNotExistException();
            }
        }
    }

    private static boolean regexChecker(String reg, String inp) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(inp);

        return matcher.find();
    }
}
