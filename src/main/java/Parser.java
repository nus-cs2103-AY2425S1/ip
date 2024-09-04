import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static Command parse(String fullCommand) throws StreamsException {
        String[] parts = fullCommand.split(" ", 2);
        String commandType = parts[0].toLowerCase();
        String rest = parts.length > 1 ? parts[1].trim() : "";

        switch (commandType) {
            case "list":
                return new ListCommand();
            case "done":
            case "undone":
                return new MarkCommand(rest, commandType.equals("done"));
            case "todo":
                return new AddCommand(new ToDoTask(rest));
            case "deadline":
                return parseDeadline(rest);
            case "event":
                return parseEvent(rest);
            case "delete":
                return new DeleteCommand(rest);
            case "list-date":
                return new ListDateCommand(rest);
            case "list-week":
                return new ListWeekCommand();
            case "sort-deadline":
                return new SortDeadlineCommand();
            case "bye":
                return new ExitCommand();
            default:
                throw new StreamsException("incorrect command: " + commandType);
        }
    }

    private static Command parseDeadline(String rest) throws StreamsException {
        String[] parts = rest.split(" /by ");
        if (parts.length != 2) {
            throw new StreamsException("the format for deadlines is 'deadline [description] /by yyyy-MM-dd HH:mm'");
        }
        String description = parts[0].trim();
        String byString = parts[1].trim();
        try {
            LocalDateTime by = LocalDateTime.parse(byString, INPUT_FORMATTER);
            return new AddCommand(new DeadlineTask(description, by));
        } catch (DateTimeParseException e) {
            throw new StreamsException("invalid date format. Please use yyyy-MM-dd HH:mm");
        }
    }

    private static Command parseEvent(String rest) throws StreamsException {
        String[] parts = rest.split(" /from ");
        if (parts.length != 2) {
            throw new StreamsException("the format for events is 'event [description] /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm'");
        }
        String description = parts[0].trim();
        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length != 2) {
            throw new StreamsException("the format for events is 'event [description] /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm'");
        }
        String fromString = timeParts[0].trim();
        String toString = timeParts[1].trim();
        try {
            LocalDateTime from = LocalDateTime.parse(fromString, INPUT_FORMATTER);
            LocalDateTime to = LocalDateTime.parse(toString, INPUT_FORMATTER);
            return new AddCommand(new EventTask(description, from, to));
        } catch (DateTimeParseException e) {
            throw new StreamsException("invalid date format. Please use yyyy-MM-dd HH:mm");
        }
    }
}

