package pebble;

import java.time.DateTimeException;
import java.util.Arrays;

/**
 *  Parser class converts String inputs into usable commands and arguments
 */
public class Parser {
    private static final int DESCRIPTION_OFFSET = 2;
    private static final String DEADLINE_BY = " (by: ";
    private static final String EVENT_FROM = " (from: ";
    private static final String EVENT_TO = " to: ";
    private static final int DEADLINE_BY_OFFSET = DEADLINE_BY.length();
    private static final int EVENT_FROM_OFFSET = EVENT_FROM.length();
    private static final int EVENT_TO_OFFSET = EVENT_TO.length();

    /**
     * Parses given string input to get corresponding command.
     *
     * @param input String inputs from the user.
     * @return Command output that is understood by program
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
     * Converts users' string command to proper command syntax.
     * Accommodates friendlier and alternative command strings
     *
     * @param userCommand Command inputted by user that is not yet formatted
     * @return Formatted command that is recognised by program
     */
    private static String convertToProperSyntax(String userCommand) {
        userCommand = userCommand.toLowerCase();
        String command;

        // Below are supported syntax
        if (Arrays.asList("bye", "goodbye", "bb", "sayonara").contains(userCommand)) {
            command = "bye";
        } else if (Arrays.asList("list", "ls", "tasks").contains(userCommand)) {
            command = "list";
        } else if (Arrays.asList("mark", "m", "tag").contains(userCommand)) {
            command = "mark";
        } else if (Arrays.asList("unmark", "um", "untag").contains(userCommand)) {
            command = "unmark";
        } else if (Arrays.asList("todo", "t", "td").contains(userCommand)) {
            command = "todo";
        } else if (Arrays.asList("deadline", "d", "dl").contains(userCommand)) {
            command = "deadline";
        } else if (Arrays.asList("event", "e", "ev").contains(userCommand)) {
            command = "event";
        } else if (Arrays.asList("delete", "rm", "remove").contains(userCommand)) {
            command = "delete";
        } else if (Arrays.asList("find", "search", "f").contains(userCommand)) {
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

    /**
     * Converts stored string into ToDo task
     * @param line String stored in the local task list text file
     * @return Converted ToDo task
     */
    private static ToDo createToDoTask(String line) {
        String description = extractDescriptionForToDo(line);
        ToDo todo = new ToDo(description);
        if (line.contains("[X]")) {
            todo.markAsDone();
        }
        return todo;
    }

    /**
     * Converts stored string into Deadline task
     * @param line String stored in the local task list text file
     * @return Converted Deadline task
     */
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

    /**
     * Converts stored string into Event task
     * @param line String stored in the local task list text file
     * @return Converted Event task
     */
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

    /**
     * Extract description of a ToDo given a ToDo formatted string
     * @param line A properly formatted string
     * @return Description of ToDo
     */
    private static String extractDescriptionForToDo(String line) {
        return line.substring(line.indexOf("] ") + DESCRIPTION_OFFSET);
    }

    /**
     * Extract description of a Deadline given a Deadline formatted string
     * @param line A properly formatted string
     * @return Description of Deadline
     */
    private static String extractDescriptionForDeadline(String line) {
        return line.substring(line.indexOf("] ") + DESCRIPTION_OFFSET, line.lastIndexOf(DEADLINE_BY));
    }

    /**
     * Extract description of an Event given an Event formatted string
     * @param line A properly formatted string
     * @return Description of Event
     */
    private static String extractDescriptionForEvent(String line) {
        return line.substring(line.indexOf("] ") + DESCRIPTION_OFFSET, line.lastIndexOf(EVENT_FROM));
    }

    /**
     * Extract date of a Deadline given a Deadline formatted string
     * @param line A properly formatted string
     * @return Date in string form
     */
    private static String extractDeadlineDate(String line) {
        return line.substring(line.lastIndexOf(DEADLINE_BY) + DEADLINE_BY_OFFSET, line.length() - 1);
    }

    /**
     * Extract start date of an Event given an Event formatted string
     * @param line A properly formatted string
     * @return Date in string form
     */
    private static String extractEventStart(String line) {
        return line.substring(line.lastIndexOf(EVENT_FROM) + EVENT_FROM_OFFSET, line.lastIndexOf(EVENT_TO));
    }

    /**
     * Extract end date of an Event given an Event formatted string
     * @param line A properly formatted string
     * @return Date in string form
     */
    private static String extractEventEnd(String line) {
        return line.substring(line.lastIndexOf(EVENT_TO) + EVENT_TO_OFFSET, line.length() - 1);
    }
}
