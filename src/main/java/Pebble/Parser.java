package pebble;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;

/**
 *  Parser class converts String inputs into usable commands and arguments
 */
public class Parser {

    /**
     * Parse strings into commands.
     *
     * @param input String inputs.
     * @return Command output.
     */
    public static Command parseCommand(String input) {
        String[] words = input.split(" ", 2);
        String command = words[0];
        String arguments = words.length > 1 ? words[1] : "";

        switch (command) {
        case "bye":
            return new Command(CommandType.BYE, arguments);
        case "list":
            return new Command(CommandType.LIST, arguments);
        case "mark":
            return new Command(CommandType.MARK, arguments);
        case "unmark":
            return new Command(CommandType.UNMARK, arguments);
        case "todo":
            return new Command(CommandType.TODO, arguments);
        case "deadline":
            return new Command(CommandType.DEADLINE, arguments);
        case "event":
            return new Command(CommandType.EVENT, arguments);
        case "delete":
            return new Command(CommandType.DELETE, arguments);
        case "find":
            return new Command(CommandType.FIND, arguments);
        default:
            return new Command(CommandType.UNKNOWN, arguments);
        }
    }

    /**
     * Parses saved tasks in pebble.txt and returns them as instances of Task object.
     *
     * @param line Line read from txt file.
     * @return Instance of task corresponding to the saved line.
     */
    public static Task parseTaskFromString(String line) {
        if (line.startsWith("[T]")) {
            String description = line.substring(line.indexOf("] ") + 2);
            ToDo todo = new ToDo(description);
            if (line.contains("[X]")) {
                todo.markAsDone();
            }
            return todo;
        } else if (line.startsWith("[D]")) {
            String description = line.substring(line.indexOf("] ") + 2, line.lastIndexOf(" (by: "));
            String by = line.substring(line.lastIndexOf(" (by: ") + 6, line.length() - 1);
            Deadline deadline;
            try {
                // Try to parse the date
                deadline = new Deadline(description,
                        LocalDate.parse(by, DateTimeFormatter.ofPattern("MMM dd yyyy")).toString());
            } catch (DateTimeException e) {
                // If parsing fails, use the original string
                deadline = new Deadline(description, by);
            }
            if (line.contains("[X]")) {
                deadline.markAsDone();
            }
            return deadline;
        } else if (line.startsWith("[E]")) {
            String description = line.substring(line.indexOf("] ") + 2, line.lastIndexOf(" (from: "));
            String from = line.substring(line.lastIndexOf(" (from: ") + 8, line.lastIndexOf(" to: "));
            String to = line.substring(line.lastIndexOf(" to: ") + 5, line.length() - 1);
            Event event;
            try {
                // Try to parse the dates
                event = new Event(description,
                        LocalDate.parse(from, DateTimeFormatter.ofPattern("MMM dd yyyy")).toString(),
                        LocalDate.parse(to, DateTimeFormatter.ofPattern("MMM dd yyyy")).toString());
            } catch (DateTimeException e) {
                // If parsing fails, use the original strings
                event = new Event(description, from, to);
            }
            if (line.contains("[X]")) {
                event.markAsDone();
            }
            return event;
        }
        return new InvalidTask();
    }
}
