package simon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Parses user input into commands for the Simon application.
 * This class is responsible for interpreting different command formats.
 */
public class Parser {
    static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    private static final DateTimeFormatter SAVE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

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

    /**
     * Parses the given input string into a {@link Command} object.
     *
     * @param input The input string to parse.
     * @return The corresponding {@link Command} object.
     * @throws Exception If the input is invalid or if there is an error in parsing the command.
     */
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

    /**
     * Parses the arguments for the "mark" command.
     *
     * @param arguments The arguments of the "mark" command, which should be the index of the task.
     * @return A {@link MarkCommand} object.
     */
    private static Command parseMarkCommand(String arguments) {
        int index = Integer.parseInt(arguments.trim()) - 1;
        return new MarkCommand(true, index);
    }

    /**
     * Parses the arguments for the "unmark" command.
     *
     * @param arguments The arguments of the "unmark" command, which should be the index of the task.
     * @return A {@link MarkCommand} object.
     */
    private static Command parseUnmarkCommand(String arguments) {
        int index = Integer.parseInt(arguments.trim()) - 1;
        return new MarkCommand(false, index);
    }

    /**
     * Parses the arguments for the "deadline" command.
     *
     * @param arguments The arguments of the "deadline" command, which should include a description and a deadline date.
     * @return A {@link DeadlineCommand} object.
     * @throws IllegalArgumentException If the arguments are missing a description or date.
     */
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

    /**
     * Parses the arguments for the "event" command.
     *
     * @param arguments The arguments of the "event" command.
     * @return An {@link EventCommand} object.
     * @throws IllegalArgumentException If the arguments are missing a description, start time, or end time.
     */
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

    /**
     * Parses the arguments for the "todo" command.
     *
     * @param arguments The arguments of the "todo" command, which should be the description of the todo.
     * @return A {@link ToDoCommand} object.
     * @throws IllegalArgumentException If the description is empty.
     */
    private static Command parseTodoCommand(String arguments) {
        String info = arguments.trim();
        if (info.isEmpty()) {
            throw new IllegalArgumentException("The description of a todo cannot be empty.");
        }
        return new ToDoCommand(info);
    }

    /**
     * Parses the arguments for the "delete" command.
     *
     * @param arguments The arguments of the "delete" command, which should be the index of the task.
     * @return A {@link DeleteCommand} object.
     */
    private static Command parseDeleteCommand(String arguments) {
        int index = Integer.parseInt(arguments.trim()) - 1;
        return new DeleteCommand(index);
    }

    /**
     * Parses the arguments for the "find" command.
     *
     * @param arguments The arguments of the "find" command, which should be the title to search for.
     * @return A {@link FindCommand} object.
     */
    private static Command parseFindCommand(String arguments) {
        String title = arguments.trim();
        return new FindCommand(title);
    }
}
