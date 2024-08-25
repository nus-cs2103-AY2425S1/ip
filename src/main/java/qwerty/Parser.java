package qwerty;

import java.util.HashMap;
import java.util.Scanner;
import qwerty.command.ByeCommand;
import qwerty.command.Command;
import qwerty.command.DeadlineCommand;
import qwerty.command.DeleteCommand;
import qwerty.command.EmptyCommand;
import qwerty.command.EventCommand;
import qwerty.command.ListCommand;
import qwerty.command.MarkCommand;
import qwerty.command.TodoCommand;
import qwerty.command.UnknownCommand;
import qwerty.command.UnmarkCommand;

public class Parser {
    /**
     * Parses additional arguments after the main command and argument.
     * (Everything after the first forward slash).
     * Returns a map where the keys are the parameter names and the values are
     * the arguments.
     *
     * @param additionalArgs String containing arguments input from the user.
     * @return A hashmap containing parameters and their arguments
     */
    private static HashMap<String, String> parseArgs(String additionalArgs) {
        HashMap<String, String> map = new HashMap<>();

        //extract first argument separately because it has no identifier
        String[] argSplit = additionalArgs.split(" /", 2);
        map.put("main", argSplit[0].isEmpty() ? null : argSplit[0]);

        if (argSplit.length < 2) {
            return map; // no additional parameters to parse
        }

        Scanner scanner = new Scanner(argSplit[1]).useDelimiter(" /");

        // extract additional parameters and their arguments
        while (scanner.hasNext()) {
            String[] params = scanner.next().split(" ", 2);

            // only create entry for a parameter if its arguments are given
            if (params.length > 1) {
                map.put(params[0], params[1]);
            }
        }
        return map;
    }

    public static Command parse(String rawInput) throws QwertyException {

        // separate command keyword from argument string
        String[] firstSplit = rawInput.split(" ", 2);
        String commandWord = firstSplit[0];
        String argumentString = firstSplit.length > 1 ? firstSplit[1] : ""; // empty string for no args
        HashMap<String, String> args = Parser.parseArgs(argumentString);

        return switch (commandWord) {
            case "" -> new EmptyCommand(args);
            case "bye" -> new ByeCommand(args);
            case "list" -> new ListCommand(args);
            case "mark" -> new MarkCommand(args);
            case "unmark" -> new UnmarkCommand(args);
            case "todo" -> new TodoCommand(args);
            case "deadline" -> new DeadlineCommand(args);
            case "event" -> new EventCommand(args);
            case "delete" -> new DeleteCommand(args);
            default -> new UnknownCommand(args);
        };
    }
}
