package rizz.source;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import rizz.commands.*;

public class Parser {

    public static Command parseCommand(String input) {
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0];
        String details = splitInput.length > 1 ? splitInput[1] : null;

        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "todo":
                return new AddToDoCommand(details);
            case "event":
                String[] eventParts = details.split("/from|/to");
                return new AddEventCommand(eventParts[0].trim(), LocalDateTime.parse(eventParts[1].trim()), LocalTime.parse(eventParts[2].trim()));
            case "deadline":
                String[] deadlineParts = details.split("/by");
                LocalDateTime by = LocalDateTime.parse(deadlineParts[1].trim());
                return new AddDeadlineCommand(deadlineParts[0].trim(), by);
            case "mark":
                return new MarkCommand(Integer.parseInt(details));
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(details));
            case "delete":
                return new DeleteCommand(Integer.parseInt(details));
            default:
            return null;
        }
    }
    
}
