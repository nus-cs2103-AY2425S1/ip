import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Parser {

    public Parser() {
    }

    public static Command parse(String command) throws AsuraException {
        List<String> splitCommand = Arrays.asList(command.split(" "));
        String prefix = splitCommand.get(0);
        if (splitCommand.size() == 1 && !Objects.equals(prefix, "list")) {
            throw new AsuraException("Invalid command format");
        }
        int selection;
        String taskString = String.join(" ", splitCommand.subList(1, splitCommand.size()));
        List<String> descriptionArray;

        switch (prefix) {
            case "list":
                return new ListCommand();
            case "mark":
                selection = Integer.parseInt(splitCommand.get(1)) - 1;
                return new MarkCommand(selection);
            case "unmark":
                selection = Integer.parseInt(splitCommand.get(1)) - 1;
                return new UnmarkCommand(selection);
            case "todo":
                return new TodoCommand(taskString);
            case "deadline":
                int byIndex = splitCommand.indexOf("/by");
                descriptionArray = splitCommand.subList(1, byIndex);
                if (descriptionArray.isEmpty()) {
                    throw new AsuraException("The description todo cannot be empty.");
                }
                List<String> dateArray = splitCommand.subList(byIndex + 1, splitCommand.size());
                if (dateArray.isEmpty()) {
                    throw new AsuraException("The date cannot be empty.");
                }
                return new DeadlineCommand(descriptionArray, dateArray);
            case "event":
                int fromIndex = splitCommand.indexOf("/from");
                int toIndex = splitCommand.indexOf("/to");
                descriptionArray = splitCommand.subList(1, fromIndex);
                if (descriptionArray.isEmpty()) {
                    throw new AsuraException("The description todo cannot be empty.");
                }
                List<String> fromArray = splitCommand.subList(fromIndex + 1, toIndex);
                if (fromArray.isEmpty()) {
                    throw new AsuraException("The from date cannot be empty.");
                }
                List<String> toArray = splitCommand.subList(toIndex + 1, splitCommand.size());
                if (toArray.isEmpty()) {
                    throw new AsuraException("The to date cannot be empty.");
                }
                return new EventCommand(descriptionArray, fromArray, toArray);
            case "delete":
                selection = Integer.parseInt(splitCommand.get(1)) - 1;
                return new DeleteCommand(selection);
            default:
                throw new AsuraException("Invalid input");
        }
    }
}
