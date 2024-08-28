import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN, FIND;

        public static Command fromString(String input) {
            String command = input.split(" ")[0].toLowerCase();
            switch (command) {
                case "bye":
                    return BYE;
                case "list":
                    return LIST;
                case "mark":
                    return MARK;
                case "unmark":
                    return UNMARK;
                case "delete":
                    return DELETE;
                case "todo":
                    return TODO;
                case "deadline":
                    return DEADLINE;
                case "event":
                    return EVENT;
                case "find":
                    return FIND;
                default:
                    return UNKNOWN;
            }
        }
    }
    public Command parseCommand(String userInput) {
        return Command.fromString(userInput);
    }

    public Task parseTask(String userInput) throws BobbyException {
        if (userInput.startsWith("todo ")) {
            String description = userInput.substring(5).trim();
            if (description.isEmpty()) {
                throw new EmptyTodoException();
            }
            return new Todo(description);
        } else if (userInput.startsWith("deadline ")) {
            String[] parts = userInput.substring(9).split(" /by ");
            if (parts.length < 2) {
                throw new EmptyDeadlineException();
            }
            String description = parts[0];
            String byString = parts[1];
            try {
                LocalDate by = LocalDate.parse(byString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return new Deadline(description, by);
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        } else if (userInput.startsWith("event ")) {
            String[] parts = userInput.substring(6).split(" /from | /to ");
            if (parts.length < 3) {
                throw new EmptyEventException();
            }
            String description = parts[0];
            String fromString = parts[1];
            String toString = parts[2];
            try {
                LocalDate from = LocalDate.parse(fromString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate to = LocalDate.parse(toString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return new Event(description, from, to);
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        } else {
            throw new InvalidInputException();
        }
    }
}
