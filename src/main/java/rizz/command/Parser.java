package rizz.command;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * The Parser class is responsible for interpreting user input and converting it into specific commands.
 * It parses the input string and returns the corresponding Command object.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     * If the input matches a known command, it creates a new Command object; otherwise, it returns null.
     *
     * Supported commands:
     * - "bye": Exit the application.
     * - "list": List all tasks.
     * - "tod0 <details>": Add a new ToD0 task.
     * - "event <details> /from <start time> /to <end time>": Add a new Event task.
     * - "deadline <details> /by <due date>": Add a new Deadline task.
     * - "mark <index>": Mark a task as done.
     * - "unmark <index>": Unmark a task.
     * - "delete <index>": Delete a task.
     * - "find <details>": Find a task.
     *
     * @param input The user input string to be parsed.
     * @return The Command object representing the parsed command, or null if the input is invalid.
     */
    public static Command parseCommand(String input) {
        String[] splitInput = input.split(" ", 2);
        String commandString= splitInput[0].toUpperCase(); // convert to enum later
        String details = splitInput.length > 1 ? splitInput[1] : null;

        CommandType commandType;

        try {
            // Convert the input string to a CommandType enum value
            commandType = CommandType.valueOf(commandString);
        } catch (IllegalArgumentException e) {
            // If the command doesn't match any enum value, return null
            return null;
        }

        switch (commandType) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case TODO:
            return new AddToDoCommand(details);
        case EVENT:
            String[] eventParts = details.split("/from|/to");
            return new AddEventCommand(eventParts[0].trim(), LocalDateTime.parse(eventParts[1].trim()),
                    LocalTime.parse(eventParts[2].trim()));
        case DEADLINE:
            String[] deadlineParts = details.split("/by");
            LocalDateTime by = LocalDateTime.parse(deadlineParts[1].trim());
            return new AddDeadlineCommand(deadlineParts[0].trim(), by);
        case MARK:
            return new MarkCommand(Integer.parseInt(details));
        case UNMARK:
            return new UnmarkCommand(Integer.parseInt(details));
        case DELETE:
            return new DeleteCommand(Integer.parseInt(details));
        case FIND:
            return new FindCommand(details);
        default:
            return null;
        }
    }
    
}
