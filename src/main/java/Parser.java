import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parse(String inputLine) throws GarfieldException {
        inputLine = inputLine.strip();

        if (inputLine.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        }

        if (inputLine.equalsIgnoreCase("list")) {
            return new ListCommand();
        }

        if (inputLine.toLowerCase().startsWith("delete")) {
            try {
                return new DeleteCommand(getIntegerArg(inputLine));
            } catch (Exception e) {
                // todo: return some thing
            }
        }

        if (inputLine.toLowerCase().startsWith("mark")) {
            try {
                return new MarkCommand(getIntegerArg(inputLine));
            } catch (Exception e) {
                // todo: return some thing
            }
        }

        if (inputLine.toLowerCase().startsWith("unmark")) {
            try {
                return new UnmarkCommand(getIntegerArg(inputLine));
            } catch (Exception e) {
                // todo: return some thing
            }
        }

        if (inputLine.toLowerCase().startsWith("todo")) {
            try {
                Todo newTodo = parseTodo(inputLine);
                return new AddCommand(newTodo);
            } catch (Exception e) {
                // todo:
            }
        }

        if (inputLine.toLowerCase().startsWith("deadline")) {
            try {
                Deadline newDeadline = parseDeadline(inputLine);
                return new AddCommand(newDeadline);
            } catch (Exception e) {
                // todo:
            }
        }

        if (inputLine.toLowerCase().startsWith("event")) {
            try {
                Event newEvent = parseEvent(inputLine);
                return new AddCommand(newEvent);
            } catch (Exception e) {
                // todo:
            }
        }

        throw new GarfieldException(inputLine + "? I’m not sure what that means. Can you give me a bit more to work with?");
    }

    private static int getIntegerArg(String fullInput) {
        String[] output = fullInput.trim().split("\\s+");
        if (output.length != 2) {
            // todo: throw some error about invalid
        }
        return Integer.parseInt(output[1]);
    }

    private static Todo parseTodo(String fullInput) {
        if (fullInput.length() <= 5) {
            // todo: Looks like you forgot to add a description. Don’t leave me hanging—give it some detail!");
        }
        String todoDescription = fullInput.substring(5);

        if (todoDescription.isBlank()) {
            // todo: Looks like you forgot to add a description. Don’t leave me hanging—give it some detail!");
        }

        return new Todo(todoDescription);
    }

    private static Deadline parseDeadline(String fullInput) throws GarfieldException{
        if (fullInput.length() <= 9) {
            // todo: Looks like you forgot to add a description. Don’t leave me hanging—give it some detail!");
        }
        String deadlineInput = fullInput.substring(9);

        if (deadlineInput.isBlank() || !deadlineInput.contains("/by")) {
            // todo: Looks like you forgot to add a description. Don’t leave me hanging—give it some detail!");
        }

        String[] deadlineArgs = fullInput.split("/by");
        if (deadlineArgs.length != 2) {
            // todo: missing args
        }
        String deadlineDescription = deadlineArgs[0].strip();

        try {
            LocalDateTime deadlineBy = parseDateTime(deadlineArgs[1].strip());
            return new Deadline(deadlineDescription, deadlineBy);
        } catch (DateTimeParseException e) {
            throw new GarfieldException("Datetime wrong format");
        }
    }

    private static Event parseEvent(String fullInput) throws GarfieldException {
        if (fullInput.length() <= 6) {
            // todo: missing description
        }
        String eventInput = fullInput.substring(6);

        if (eventInput.isBlank() || !eventInput.contains("/from") || !eventInput.contains("/to")) {
            // todo: missing args
        }

        String[] eventInputTwo = eventInput.split("/from");

        if (!eventInputTwo[1].contains("/to")) {
            // todo: missing args
        }

        String[] eventArgs = eventInputTwo[1].split("/to");
        String eventDescription = eventInputTwo[0].strip();

        try {
            LocalDateTime eventFrom = parseDateTime(eventArgs[0].strip());
            LocalDateTime eventTo = parseDateTime(eventArgs[1].strip());
            return new Event(eventDescription, eventFrom, eventTo);
        } catch (DateTimeParseException e) {
            throw new GarfieldException("datetime wrong format");
        }
    }

    private static LocalDateTime parseDateTime(String dateInput) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateInput, formatter);
    }
}
