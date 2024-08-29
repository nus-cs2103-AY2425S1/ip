import java.time.LocalDate;

public class Parser {

    public Command parse(String input) throws GavinException {
        if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            return new MarkCommand(index);
        } else if (input.startsWith("unmark")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            return new UnmarkCommand(index);
        } else if (input.startsWith("todo")) {
            String description = input.substring(5).trim();
            return new AddToDoCommand(description);
        } else if (input.startsWith("deadline")) {
            String[] parts = input.substring(9).split("/by");
            String description = parts[0].trim();
            LocalDate by = LocalDate.parse(parts[1].trim());
            return new AddDeadlineCommand(description, by);
        } else if (input.startsWith("event")) {
            String[] parts = input.substring(6).split("/from");
            String description = parts[0].trim();
            String[] timeParts = parts[1].split("/to");
            String from = timeParts[0].trim();
            String to = timeParts[1].trim();
            return new AddEventCommand(description, from, to);
        } else if (input.startsWith("delete")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            return new DeleteCommand(index);
        } else {
            throw new GavinException("Invalid input! Please start with 'todo', 'deadline', or 'event'.");
        }
    }
}
