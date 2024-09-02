package ipman.parser;

import ipman.commands.Command;
import ipman.commands.CreateDeadlineCommand;
import ipman.commands.CreateEventCommand;
import ipman.commands.CreateToDoCommand;
import ipman.commands.DeleteCommand;
import ipman.commands.ExitCommand;
import ipman.commands.FindCommand;
import ipman.commands.ListCommand;
import ipman.commands.MarkCommand;
import ipman.commands.UnmarkCommand;

import java.time.LocalDate;
import java.util.StringJoiner;

public class Parser {
    private record Message(String keyword, String args) {}

    public static Command parseCommand(String message) {
        Message messageRecord = parseMessage(message);
        String keyword = messageRecord.keyword;
        switch (keyword) {
            case "bye": {
                return new ExitCommand();
            }
            case "list": {
                return new ListCommand();
            }
            case "mark": {
                String[] args = parseArgs(messageRecord.args, new String[]{});
                int index = parseInt(args[0]) - 1;
                return new MarkCommand(index);
            }
            case "unmark": {
                String[] args = parseArgs(messageRecord.args, new String[]{});
                int index = parseInt(args[0]) - 1;
                return new UnmarkCommand(index);
            }
            case "todo": {
                String[] args = parseArgs(messageRecord.args, new String[]{});
                return new CreateToDoCommand(args[0]);
            }
            case "deadline": {
                String[] args = parseArgs(messageRecord.args, new String[] { "/by" });
                return new CreateDeadlineCommand(args[0], parseDate(args[1]));
            }
            case "event": {
                String[] args = parseArgs(messageRecord.args, new String[] { "/from", "/to" });
                return new CreateEventCommand(args[0], parseDate(args[1]), parseDate(args[2]));
            }
            case "delete": {
                String[] args = parseArgs(messageRecord.args, new String[]{});
                int index = parseInt(args[0]);
                return new DeleteCommand(index);
            }
            case "find": {
                String[] args = parseArgs(messageRecord.args, new String[]{});
                return new FindCommand(args[0]);
            }
            default: {
                throw new KeywordNotRecognisedException(messageRecord.keyword);
            }
        }
    }

    private static Message parseMessage(String message) {
        String[] split = message.split(" ", 2);
        if (split.length <= 1) {
            return new Message(message, "");
        }
        return new Message(split[0], split[1]);
    }

    private static String[] parseArgs(String messageArgs, String[] argNames) {
        // The values ot the arguments. One additional space is used for the
        // text before all the arguments, i.e. <keyword> TEXT <arg1> ARG1 VAL...
        String[] values = new String[argNames.length + 1];
        int index = 0;

        StringJoiner sj = new StringJoiner(" ");
        String[] split = messageArgs.split(" ");
        for (String s : split) {
            // s is part of the previous argument
            if (!(index < argNames.length && s.equals(argNames[index]))) {
                sj.add(s);
                continue;
            }

            // s is the name of the next argument
            values[index] = sj.toString();
            sj = new StringJoiner(" ");
            index++;
        }
        values[index] = sj.toString();

        if (index != argNames.length) {
            throw new MissingArgumentException(argNames.length, index);
        }

        if (values[0].isEmpty() && values.length == 1) {
            throw new MissingArgumentException(1, 0);
        }

        return values;
    }

    private static int parseInt(String s) throws NumberFormatException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(String.format("%s is not an integer. Please enter an integer.", s));
        }
    }

    private static LocalDate parseDate(String s) {
        return LocalDate.parse(s);
    }
}
