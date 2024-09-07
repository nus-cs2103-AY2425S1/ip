package simon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Parses user input into commands for the Simon application.
 * This class is responsible for interpreting different command formats and creating corresponding Command objects.
 */
public class Parser {
    private static final DateTimeFormatter SAVE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Parses a string input into a Command object.
     * The input string is analyzed to determine the type of command and its parameters.
     *
     * @param input the string input representing a user command
     * @return a Command object corresponding to the parsed input
     * @throws Exception if the input string is invalid or cannot be parsed into a command
     */
    public static Command parse(String input) throws Exception {
        assert input != null: "Input is null";
        if (input.isEmpty()) {
            return null;
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            return new MarkCommand(true, index);
        } else if (input.startsWith("unmark")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            return new MarkCommand(false, index);
        } else if (input.startsWith("deadline")) {
            String[] info = input.substring(9).split("/by");
            if (info.length < 2 || info[0].trim().isEmpty() || info[1].trim().isEmpty()) {
                throw new IllegalArgumentException("The deadline command is missing description or date.");
            }
            String name = info[0].trim();
            String deadlineString = info[1].trim();
            LocalDateTime deadline = LocalDateTime.parse(deadlineString, INPUT_FORMATTER);
            return new DeadlineCommand(name, deadline);
        } else if (input.startsWith("event")) {
            String[] info = input.substring(5).split("/from");
            if (info.length < 2 || info[0].trim().isEmpty()) {
                throw new IllegalArgumentException("The event command is missing description or date/time.");
            }
            String[] deadlines = info[1].split("/to");
            if (deadlines.length < 2 || deadlines[0].trim().isEmpty() || deadlines[1].trim().isEmpty()) {
                throw new IllegalArgumentException("The event command is missing the start or end time.");
            }
            String from = deadlines[0].trim();
            String to = deadlines[1].trim();
            String name = info[0].trim();
            return new EventCommand(name, from, to);
        } else if (input.startsWith("todo")) {
            String info = input.substring(4).trim();
            if (info.isEmpty()) {
                throw new IllegalArgumentException("The description of a todo cannot be empty.");
            }
            return new ToDoCommand(info);
        } else if (input.startsWith("delete")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            return new DeleteCommand(index);
        } else if (input.startsWith("find")) {
            String[] parts = input.split(" ");
            String title = parts[1];
            return new FindCommand(title);
        } else {
            throw new IllegalArgumentException("Illegal command.");
        }
    }
}
