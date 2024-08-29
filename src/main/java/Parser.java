import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static String[] parseCommand(String input) {
        return input.split(" ", 2);
    }

    public static Task parseTask(String input) throws JardException {
        String[] inputParts = input.split(" ", 2);
        String command = inputParts[0];

        if (command.equals("todo")) {
            if (inputParts.length > 1) {
                return new Todo(inputParts[1]);
            } else {
                throw new Jard.JardException("The description of a todo cannot be empty.");
            }
        } else if (command.equals("deadline")) {
            if (inputParts.length > 1) {
                String[] details = inputParts[1].split("/by", 2);
                if (details.length < 2) {
                    throw new Jard.JardException("Invalid format! Deadline should be /by.");
                }
                return new Deadline(details[0].trim(), details[1].trim());
            } else {
                throw new Jard.JardException("The description of a deadline cannot be empty.");
            }
        } else if (command.equals("event")) {
            if (inputParts.length > 1) {
                String[] details = inputParts[1].split("/from|/to");
                if (details.length != 3) {
                    throw new Jard.JardException("Invalid format! Events should be /from and /to.");
                }
                return new Event(details[0].trim(), details[1].trim(), details[2].trim());
            } else {
                throw new Jard.JardException("The description of an event cannot be empty.");
            }
        } else {
            throw new Jard.JardException("Invalid command!");
        }
    }

    public static int parseTaskIndex(String input, int taskListSize) {
        try {
            int taskIndex = Integer.parseInt(input) - 1;
            if (taskIndex < 0 || taskIndex >= taskListSize) {
                throw new Jard.JardException("Task number does not exist!");
            }
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new Jard.JardException("That's not a number!");
        }
    }

    public static LocalDateTime parseDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
