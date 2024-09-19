package pebble;

import java.time.DateTimeException;
import java.util.Arrays;

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

        // Friendlier syntax support
        command = convertToProperSyntax(command);

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
     * Converts string command to proper command syntax.
     * Accommodates alternative command strings
     * @param command Command inputted by user
     * @return Command String accepted by program
     */
    private static String convertToProperSyntax(String command) {
        command = command.toLowerCase();
        if (Arrays.asList("goodbye", "bb", "sayonara").contains(command)) {
            command = "bye";
        } else if (Arrays.asList("ls", "tasks").contains(command)) {
            command = "list";
        } else if (Arrays.asList("m", "done").contains(command)) {
            command = "mark";
        } else if (Arrays.asList("um", "undo").contains(command)) {
            command = "unmark";
        } else if (Arrays.asList("t", "td").contains(command)) {
            command = "todo";
        } else if (Arrays.asList("d", "dl").contains(command)) {
            command = "deadline";
        } else if (Arrays.asList("e", "ev").contains(command)) {
            command = "event";
        } else if (Arrays.asList("dl", "rm", "remove").contains(command)) {
            command = "delete";
        } else if (Arrays.asList("search", "f").contains(command)) {
            command = "find";
        } else {
            command = "unknown";
        }
        return command;
    }


    /**
     * Parses saved tasks in pebble.txt and returns them as instances of Task object.
     *
     * @param line Line read from txt file.
     * @return Instance of task corresponding to the saved line.
     */
    public static Task parseTaskFromString(String line) {
        if (line.startsWith("[T]")) {
            return createToDoTask(line);
        } else if (line.startsWith("[D]")) {
            return createDeadlineTask(line);
        } else if (line.startsWith("[E]")) {
            return createEventTask(line);
        }
        return new InvalidTask();
    }

    private static ToDo createToDoTask(String line) {
        String description = extractDescription(line);
        ToDo todo = new ToDo(description);
        if (line.contains("[X]")) {
            todo.markAsDone();
        }
        return todo;
    }

    private static Deadline createDeadlineTask(String line) {
        String description = extractDescriptionForDeadline(line);
        String by = extractDeadlineDate(line);

        Deadline deadline;
        try {
            deadline = new Deadline(description, by);  // Use the stored date directly
        } catch (DateTimeException e) {
            deadline = new Deadline(description, by);
        }
        if (line.contains("[X]")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    private static Event createEventTask(String line) {
        String description = extractDescriptionForEvent(line);
        String from = extractEventStart(line);
        String to = extractEventEnd(line);

        Event event;
        try {
            event = new Event(description, from, to);  // Use the stored dates directly
        } catch (DateTimeException e) {
            event = new Event(description, from, to);
        }
        if (line.contains("[X]")) {
            event.markAsDone();
        }
        return event;
    }

    private static String extractDescription(String line) {
        return line.substring(line.indexOf("] ") + 2); // Generic description for ToDo
    }

    private static String extractDescriptionForDeadline(String line) {
        return line.substring(line.indexOf("] ") + 2, line.lastIndexOf(" (by: "));  // Skip adding 'by'
    }

    private static String extractDescriptionForEvent(String line) {
        return line.substring(line.indexOf("] ") + 2, line.lastIndexOf(" (from: "));  // Skip adding 'from' and 'to'
    }

    private static String extractDeadlineDate(String line) {
        return line.substring(line.lastIndexOf(" (by: ") + 6, line.length() - 1);
    }

    private static String extractEventStart(String line) {
        return line.substring(line.lastIndexOf(" (from: ") + 8, line.lastIndexOf(" to: "));
    }

    private static String extractEventEnd(String line) {
        return line.substring(line.lastIndexOf(" to: ") + 5, line.length() - 1);
    }
}
