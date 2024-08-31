import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public Parser() {
    }

    public Command parse(String command) throws AsuraException {
        List<String> splitCommand = Arrays.asList(command.split(" "));
        String prefix = splitCommand.get(0);
        return switch (prefix) {
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand();
            case "unmark" -> new UnmarkCommand();
            case "todo" -> new TodoCommand();
            case "deadline" -> new DeadlineCommand();
            case "event" -> new EventCommand();
            case "delete" -> new DeleteCommand();
            default -> throw new AsuraException("Invalid input");
        };
    }
}
