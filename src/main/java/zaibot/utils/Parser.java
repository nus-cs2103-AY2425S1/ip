package zaibot.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import zaibot.command.Command;
import zaibot.command.ExitCommand;
import zaibot.command.TaskAdditionCommand;
import zaibot.command.TaskFindCommand;
import zaibot.command.TaskListingCommand;
import zaibot.command.TaskUpdateCommand;
import zaibot.exception.ZaibotException;

/**
 * The Parser class is responsible for parsing the commands
 * and creating the Command object responsible for
 * executing the logic as intended.
 */
public class Parser {
    /**
     * This takes a command split by spaces, and processes them into separate commands
     * The Hashmap returned contains key-value pairs of {parameter name: argument value}
     *
     * @param command A command
     * @return A zaibot.command.Command with the parsed arguments.
     */
    public static Command parse(String command) throws ZaibotException {
        HashMap<String, String> arguments = new HashMap<>();

        if (command.indexOf(' ') == -1) {
            switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new TaskListingCommand();
            default:
                throw new ZaibotException("Are you missing arguments? Invalid command.");
            }
        }

        String commandName = command.substring(0, command.indexOf(' '));
        assert !commandName.isEmpty();

        switch (commandName) {
        case "mark":
            // fallthrough
        case "unmark":
            // fallthrough
        case "delete":
            arguments.put("number", command.substring(command.indexOf(" ") + 1));
            return new TaskUpdateCommand(commandName, arguments);
        case "find":
            arguments.put("name", command.substring(command.indexOf(" ") + 1));
            return new TaskFindCommand(commandName, arguments);
        case "todo":
            // fallthrough
        case "deadline":
            // fallthrough
        case "event":
            arguments = getOptionSet(command);
            return new TaskAdditionCommand(commandName, arguments);
        default:
            throw new ZaibotException("Unknown command.");
        }
    }

    private static List<String> splitOptionString(String option) {

        if (option.indexOf(' ') == -1) {
            return Arrays.asList(option, "");
        }

        String optionName = option.substring(0, option.indexOf(' ')).trim();
        String optionValue = option.substring(option.indexOf(' ')).trim();
        System.out.println(Arrays.asList(optionName, optionValue));
        return Arrays.asList(optionName, optionValue);
    }

    private static HashMap<String, String> getOptionSet(String command) throws ZaibotException {
        HashMap<String, String> arguments = new HashMap<>();
        String optionString = command.substring(command.indexOf(' '));

        String[] options = optionString.split(" /");
        int length = options.length;

        options = Arrays.stream(options)
                .map(String::trim)
                .collect(Collectors.toCollection(ArrayList::new))
                .toArray(new String[length]);

        if (optionString.isEmpty() || options[0].isEmpty()) {
            throw new ZaibotException("Name cannot be empty.");
        }

        arguments.put("name", options[0]);

        // Filter out all options with empty values, and adds them to optionSet
        Arrays.stream(options)
                .skip(1)
                .map(Parser::splitOptionString)
                .filter(o -> !(o.get(1).isBlank()))
                .forEach(o -> arguments.put(o.get(0), o.get(1)));

        /* Checks if filtered option set is the same size as non-filtered */
        if (arguments.size() != length) {
            throw new ZaibotException("Options cannot be empty");
        }
        return arguments;
    }
}
