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
        int selection = Integer.parseInt(splitCommand.get(1)) - 1;
        String taskString = String.join(" ", splitCommand.subList(1, splitCommand.size()));

        switch (prefix) {
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(selection);
            case "unmark":
                return new UnmarkCommand(selection);
            case "todo":
                return new TodoCommand(taskString);
            case "deadline":
                int byIndex = splitCommand.indexOf("/by");
                List<String> descriptionArray = splitCommand.subList(1, byIndex);
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
                try {
                    List<String> descriptionArray = splitCommand.subList(1, fromIndex);
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
                    Event newEvent = new Event(String.join(" ", descriptionArray), String.join(" ", fromArray), String.join(" ", toArray));
                    tasks.add(newEvent);
                    saveTasks(tasks, savePath);
                    output.append("Got it. I've added this task:\n").append(newEvent.toString()).append("\n").append("Now you have ").append(tasks.size()).append(" tasks in your list.\n");
                    System.out.println(formatResponse(output.toString()));
                }
                catch (Exception e) {
                    throw new AsuraException(e.getMessage());
                }
            case "delete":
                return new DeleteCommand();
            default:
                throw new AsuraException("Invalid input");
        }
    }
}
