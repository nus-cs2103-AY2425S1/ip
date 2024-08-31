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
        if (splitCommand.size() == 1) {
            throw new AsuraException("Invalid command format");
        }

        switch (prefix) {
            case "list":
                return new ListCommand();
            case "mark":
                int selection = Integer.parseInt(splitCommand.get(1)) - 1;
                return new MarkCommand(selection);
            case "unmark":
                return new UnmarkCommand();
            case "todo":
                return new TodoCommand();
            case "deadline":
                return new DeadlineCommand();
            case "event":
                return new EventCommand();
            case "delete":
                return new DeleteCommand();
            default:
                throw new AsuraException("Invalid input");
        }
    }
}
