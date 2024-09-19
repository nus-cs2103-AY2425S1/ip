package tars.parsers;

import tars.exceptions.TarsException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class CommandValidator {

    private static final Map<CommandType, Map<Predicate<String[]>, String>> VALIDATION_RULES;

    static {
        VALIDATION_RULES = new HashMap<>();
        VALIDATION_RULES.put(CommandType.BY, Map.of(
                command -> command.length == 1 && command[0].equals("by"),
                "Finish the command by adding a deadline date",
                command -> (command.length == 1 || command.length == 2) && !command[0].equals("by"),
                "Add the /by command",
                command -> command.length == 2 && command[1].isEmpty(),
                "Finish the command by adding a deadline date"
        ));
        VALIDATION_RULES.put(CommandType.FROM, Map.of(
                command -> command.length == 1 && command[0].equals("from"),
                "Add an event start date",
                command -> command.length == 1 && !command[0].equals("from"),
                "Add the /from command",
                command -> command.length == 2 && !command[0].equals("from"),
                "Add the /from command",
                command -> command.length == 2 && command[1].isEmpty(),
                "Add an event start date"
        ));
        VALIDATION_RULES.put(CommandType.TO, Map.of(
                command -> command.length == 1 && command[0].equals("to"),
                "Add an event end date",
                command -> command.length == 1 && !command[0].equals("to"),
                "Add the /to command",
                command -> command.length == 2 && !command[0].equals("to"),
                "Add the /to command",
                command -> command.length == 2 && command[1].isEmpty(),
                "Add an event end date"
        ));
    }

    public static void validate(String[] command, CommandType type) {
        Map<Predicate<String[]>, String> rules = VALIDATION_RULES.get(type);
        rules.forEach((condition, errorMessage) -> {
            if (condition.test(command)) {
                throw new TarsException(errorMessage);
            }
        });
    }

    public enum CommandType {
        BY,
        FROM,
        TO
    }
}
