package hana;

import hana.task.ToDo;
import hana.task.Deadline;
import hana.task.Event;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String fullCommand) throws HanaException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];
        int taskNumber;
        switch (commandWord) {
        case "list":
            return new ListCommand();
        case "mark":
            Pattern markPattern = Pattern.compile("^mark (\\d+)$");
            Matcher markMatcher = markPattern.matcher(fullCommand);
            if (!markMatcher.matches()) {
                throw new HanaException("Invalid mark syntax. Write only the task index after the word 'mark'.");
            }
            taskNumber = Integer.parseInt(markMatcher.group(1)) - 1;
            return new MarkCommand(taskNumber);
        case "unmark":
            Pattern unmarkPattern = Pattern.compile("^unmark (\\d+)$");
            Matcher unmarkMatcher = unmarkPattern.matcher(fullCommand);
            if (!unmarkMatcher.matches()) {
                throw new HanaException("Invalid unmark syntax. Write only the task index after the word 'unmark'.");
            }
            taskNumber = Integer.parseInt(unmarkMatcher.group(1)) - 1;
            return new UnmarkCommand(taskNumber);
        case "todo":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new HanaException("OOPS!!! The description of a todo cannot be empty.");
            }
            return new AddCommand(new ToDo(parts[1]));
        case "deadline":
            String[] deadlineParts = parts[1].split(" /by ");
            if (fullCommand.trim().equals("deadline") || fullCommand.startsWith("deadline /by")) {
                throw new HanaException("OOPS!!! The description of a deadline cannot be empty.");
            } else if (!fullCommand.contains(" /by ")) {
                throw new HanaException("OOPS!!! Please add deadline date/time by adding /by");
            }
            try {
                LocalDate d1 = LocalDate.parse(deadlineParts[1]);
                deadlineParts[1] = d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } catch (DateTimeParseException e) {
                throw new HanaException("OOPS!!! The date format is incorrect. Please use the format YYYY-MM-DD.");
            }
            return new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1]));
        case "event":
            String[] eventParts = parts[1].split(" /from | /to ");
            if (fullCommand.trim().equals("event")  || fullCommand.startsWith("event /from")) {
                throw new HanaException("OOPS!!! The description of an event cannot be empty.");
            } else if (!fullCommand.contains(" /from ") || !fullCommand.contains(" /to ")) {
                throw new HanaException("OOPS!!! Please add event time by adding /from and /to");
            } else if (fullCommand.indexOf(" /to ") < fullCommand.indexOf(" /from ")) {
                throw new HanaException("OOPS!!! Please add /from before /to");
            }
            try {
                LocalDate d1 = LocalDate.parse(eventParts[1]);
                LocalDate d2 = LocalDate.parse(eventParts[2]);
                if (d1.isAfter(d2)) {
                    throw new HanaException("OOPS!!! from date cannot be after to date.");
                }
                eventParts[1] = d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                eventParts[2] = d2.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } catch (DateTimeParseException e) {
                throw new HanaException("OOPS!!! The date format is incorrect. \nPlease use the format YYYY-MM-DD.");
            }
            return new AddCommand(new Event(eventParts[0], eventParts[1], eventParts[2]));
        case "delete":
            Pattern deletePattern = Pattern.compile("^delete (\\d+)$");
            Matcher deleteMatcher = deletePattern.matcher(fullCommand);
            if (!deleteMatcher.matches()) {
                throw new HanaException("Invalid delete syntax. Write only the task index after the word 'delete'.");
            }
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            return new DeleteCommand(taskIndex);
        case "bye":
            return new ExitCommand();
        default:
            throw new HanaException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

