package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import duke.commands.AddTaskCommand;
import duke.commands.Command;
import duke.commands.DeleteTaskCommand;
import duke.commands.FilterTaskCommand;
import duke.commands.ListTaskCommand;
import duke.commands.MarkTaskCommand;

public class Parser {
    private static final List<DateTimeFormatter> inputFormatters = new ArrayList<>();
    static {
        inputFormatters.add(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")); // e.g., "2/12/2019 1800"
        inputFormatters.add(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")); // e.g., "Dec 2 2019, 6:00 PM"
        inputFormatters.add(DateTimeFormatter.ofPattern("d/M/yyyy")); // e.g., "2/12/2019"
        inputFormatters.add(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")); // e.g., "Jul 2 2019, 5:00 pm");
    }

    public static Command parseUserInput(String userInput) {
        if (userInput.equals("list")) {
            return new ListTaskCommand();
        } else if (userInput.startsWith("unmark")) {
            return new MarkTaskCommand(false, Parser.parseTaskIndex(userInput));
        } else if (userInput.startsWith("mark")) {
            return new MarkTaskCommand(true, Parser.parseTaskIndex(userInput));
        } else if (userInput.startsWith("delete")) {
            return new DeleteTaskCommand(Parser.parseTaskIndex(userInput));
        } else if (userInput.startsWith("filter")) {
            String dateString = userInput.split(" ", 2)[1];
            LocalDateTime dateTime = Parser.parseDateTime(dateString);
            return new FilterTaskCommand(dateTime);
        } else { // we try to add a task (todos/ deadline/ event) else throw an exception
            return new AddTaskCommand(userInput);
        }
    }

    public static int parseTaskIndex(String userInput) {
        try {
            return Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid task number provided.");
            return -1;
        }
    }

    // Helper method to parse a date string with multiple formats
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        for (DateTimeFormatter formatter : Parser.inputFormatters) {
            try {
                if (Objects.equals(formatter, DateTimeFormatter.ofPattern("d/M/yyyy"))) {
                    LocalDate parsedDate = LocalDate.parse(dateTimeStr, formatter);
                    return parsedDate.atStartOfDay(); // Default to midnight
                } else {
                    return LocalDateTime.parse(dateTimeStr, formatter);
                }
            } catch (DateTimeParseException e) {
                // Try the next formatter
            }
        }
        return null; // If no format matched, return null
    }
}
