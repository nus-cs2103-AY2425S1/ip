package simon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
/**
 * Parses user input into commands for the Simon application.
 * This class is responsible for interpreting different command formats and creating corresponding Command objects.
 */
public class Parser {
    private static final DateTimeFormatter SAVE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    private static final Map<String, Function<String, Command>> commandMap = new HashMap<>();

    static {
        commandMap.put("list", input -> new ListCommand());
        commandMap.put("mark", Parser::parseMarkCommand);
        commandMap.put("unmark", Parser::parseUnmarkCommand);
        commandMap.put("deadline", Parser::parseDeadlineCommand);
        commandMap.put("event", Parser::parseEventCommand);
        commandMap.put("todo", Parser::parseTodoCommand);
        commandMap.put("delete", Parser::parseDeleteCommand);
        commandMap.put("find", Parser::parseFindCommand);
    }

    public static Command parse(String input) throws Exception {
        assert input != null : "Input is null";
        if (input.isEmpty()) {
            return null;
        }

        String[] parts = input.split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        Function<String, Command> commandFunction = commandMap.get(commandWord);
        if (commandFunction != null) {
            return commandFunction.apply(arguments);
        } else {
            throw new IllegalArgumentException("Illegal command.");
        }
    }

    private static Command parseMarkCommand(String arguments) {
        int index = Integer.parseInt(arguments.trim()) - 1;
        return new MarkCommand(true, index);
    }

    private static Command parseUnmarkCommand(String arguments) {
        int index = Integer.parseInt(arguments.trim()) - 1;
        return new MarkCommand(false, index);
    }

    private static Command parseDeadlineCommand(String arguments) {
        String[] info = arguments.split("/by");
        if (info.length < 2 || info[0].trim().isEmpty() || info[1].trim().isEmpty()) {
            throw new IllegalArgumentException("The deadline command is missing description or date.");
        }
        String name = info[0].trim();
        String deadlineString = info[1].trim();
        LocalDateTime deadline = LocalDateTime.parse(deadlineString, INPUT_FORMATTER);
        return new DeadlineCommand(name, deadline);
    }

    private static Command parseEventCommand(String arguments) {
        String[] info = arguments.split("/from");
        if (info.length < 2 || info[0].trim().isEmpty()) {
            throw new IllegalArgumentException("The event command is missing description or date/time.");
        }
        String[] deadlines = info[1].split("/to");
        if (deadlines.length < 2 || deadlines[0].trim().isEmpty() || deadlines[1].trim().isEmpty()) {
            throw new IllegalArgumentException("The event command is missing the start or end time.");
        }
        String name = info[0].trim();
        String from = deadlines[0].trim();
        String to = deadlines[1].trim();
        return new EventCommand(name, from, to);
    }

    private static Command parseTodoCommand(String arguments) {
        String info = arguments.trim();
        if (info.isEmpty()) {
            throw new IllegalArgumentException("The description of a todo cannot be empty.");
        }
        return new ToDoCommand(info);
    }

    private static Command parseDeleteCommand(String arguments) {
        int index = Integer.parseInt(arguments.trim()) - 1;
        return new DeleteCommand(index);
    }

    private static Command parseFindCommand(String arguments) {
        String title = arguments.trim();
        return new FindCommand(title);
    }
}

