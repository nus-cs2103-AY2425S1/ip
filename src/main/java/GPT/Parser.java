package GPT;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parseCommand(String input) throws GPTException {
        if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("todo")) {
            return new TodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new DeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new EventCommand(input);
        } else {
            return new HelpCommand(); // Return a HelpCommand when an unrecognized input is entered
        }
    }

    public static LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("d/M/yyyy");
        DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            return LocalDateTime.parse(dateTimeStr, formatter1);
        } catch (DateTimeParseException e) {
            try {
                return LocalDateTime.parse(dateTimeStr, formatter2);
            } catch (DateTimeParseException ex) {
                try {
                    return LocalDateTime.parse(dateTimeStr, formatter3);
                } catch (DateTimeParseException exc) {
                    try {
                        LocalDate date = LocalDate.parse(dateTimeStr, formatter4);
                        return date.atStartOfDay();
                    } catch (DateTimeParseException exc2) {
                        try {
                            LocalDate date = LocalDate.parse(dateTimeStr, formatter5);
                            return date.atStartOfDay();
                        } catch (DateTimeParseException exc3) {
                            System.out.println("Invalid date format. Please use yyyy-MM-dd HHmm, d/M/yyyy HHmm, or yyyy-MM-dd format.");
                            return null;
                        }
                    }
                }
            }
        }
    }
}
