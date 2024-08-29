import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles parsing of user commands.
 */
public class Parser {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses the input command and its parameters.
     *
     * @param input The full user input.
     * @return A Command object containing the command type and its parameters.
     * @throws InvalidCommandException If the command is not valid.
     */
    public static Command parseCommand(String input) throws InvalidCommandException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];

        switch (command) {
        case "bye":
            return new Command(command, null);
        case "list":
            return new Command(command, null);
        case "todo":
            return parseTodoCommand(parts);
        case "deadline":
            return parseDeadlineCommand(parts);
        case "event":
            return parseEventCommand(parts);
        case "mark":
        case "unmark":
        case "delete":
            return parseIndexCommand(parts, command);
        default:
            throw new InvalidCommandException("Unknown command: " + command + ". Please enter a valid command (e.g., list, todo, deadline, event, mark, unmark, delete, bye).");
        }
    }

    private static Command parseTodoCommand(String[] parts) throws InvalidCommandException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new InvalidCommandException("The description of a todo cannot be empty. Please specify a task after 'todo'.");
        }
        return new Command("todo", parts[1].trim());
    }

    private static Command parseDeadlineCommand(String[] parts) throws InvalidCommandException {
        if (parts.length < 2 || !parts[1].contains(" /by ")) {
            throw new InvalidCommandException("The description of a deadline task must include a '/by' followed by the due date/time. Example: 'deadline return book /by 2019-10-10 1800'.");
        }
        String[] deadlineDetails = parts[1].split(" /by ");
        LocalDateTime deadlineDate = parseDate(deadlineDetails[1]);
        return new Command("deadline", new DeadlineDetail(deadlineDetails[0], deadlineDate));
    }

    private static Command parseEventCommand(String[] parts) throws InvalidCommandException {
        if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
            throw new InvalidCommandException("The description of an event must include '/from' and '/to' followed by the start and end times. Example: 'event project meeting /from 2019-10-10 1800 /to 2019-10-10 2000'.");
        }
        String[] eventDetails = parts[1].split(" /from ");
        String[] times = eventDetails[1].split(" /to ");
        LocalDateTime fromDate = parseDate(times[0]);
        LocalDateTime toDate = parseDate(times[1]);
        return new Command("event", new EventDetail(eventDetails[0], fromDate, toDate));
    }

    private static Command parseIndexCommand(String[] parts, String command) throws InvalidCommandException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new InvalidCommandException("Please specify the task number to " + command + ".");
        }
        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            return new Command(command, taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task number for '" + command + "'. Please enter a valid number after '" + command + "'.");
        }
    }

    private static LocalDateTime parseDate(String dateTimeString) throws InvalidCommandException {
        try {
            return LocalDateTime.parse(dateTimeString, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Invalid date/time format. Please use 'yyyy-MM-dd HHmm'.");
        }
    }

    // Command and Detail classes for encapsulating command data
    public static class Command {
        private String type;
        private Object details;

        public Command(String type, Object details) {
            this.type = type;
            this.details = details;
        }

        public String getType() {
            return type;
        }

        public Object getDetails() {
            return details;
        }
    }

    public static class DeadlineDetail {
        private String description;
        private LocalDateTime deadline;

        public DeadlineDetail(String description, LocalDateTime deadline) {
            this.description = description;
            this.deadline = deadline;
        }

        public String getDescription() {
            return description;
        }

        public LocalDateTime getDeadline() {
            return deadline;
        }
    }

    public static class EventDetail {
        private String description;
        private LocalDateTime from;
        private LocalDateTime to;

        public EventDetail(String description, LocalDateTime from, LocalDateTime to) {
            this.description = description;
            this.from = from;
            this.to = to;
        }

        public String getDescription() {
            return description;
        }

        public LocalDateTime getFrom() {
            return from;
        }

        public LocalDateTime getTo() {
            return to;
        }
    }
}
