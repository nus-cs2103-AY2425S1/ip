import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public static Task parseCommand(String command) {
        if (command.startsWith("todo")) {
            String description = command.substring(5).trim();
            if (description.isEmpty()) {
                System.out.println("________________________________________________");
                System.out.println("Sorry! The description cannot be empty.");
                System.out.println("________________________________________________");
            }
            else {
                return new ToDo(description);
            }
        }
        else if (command.startsWith("deadline")) {
            try {
                String[] parts = command.split(" /by ");
                String description = parts[0].substring(9).trim();
                LocalDateTime deadline = parseDateTime(parts[1].trim());
                if (description.isEmpty() || deadline == null) {
                    System.out.println("________________________________________________");
                    System.out.println("Sorry! The description or the deadline cannot be empty.");
                    System.out.println("________________________________________________");
                }
                else {
                    return new Deadline(description, deadline);
                }
            }
            catch (Exception e) {
                System.out.println("Error parsing deadline command.");
                return null;
            }
        }
        else if (command.startsWith("event")) {
            try {
                String[] parts = command.split(" /from ");
                String[] subParts = parts[1].split(" /to ");
                String description = parts[0].substring(6).trim();
                LocalDateTime from = parseDateTime(subParts[0].trim());
                LocalDateTime to = parseDateTime(subParts[1].trim());
                if (description.isEmpty() || from == null || to == null) {
                    System.out.println("________________________________________________");
                    System.out.println("Sorry! The description or the timing cannot be empty.");
                    System.out.println("________________________________________________");
                }
                else {
                    return new Event(description, from, to);
                }
            }
            catch (Exception e) {
                System.out.println("Error parsing event command.");
                return null;
            }
        }
        else {
            System.out.println("________________________________________________");
            System.out.println("Invalid command. Please use 'todo', 'deadline', 'event', 'delete'," +
                    " 'mark', 'unmark', 'list' or 'bye'. Thank you for understanding!");
            System.out.println("________________________________________________");
        }
        return null;
    }

    private static LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr, FORMATTER);
        }
        catch (DateTimeParseException e) {
            System.out.println("Error parsing date/time: " + dateTimeStr);
            return null;
        }
    }
}
