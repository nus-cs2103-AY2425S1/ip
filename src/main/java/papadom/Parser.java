package papadom;

import papadom.Exceptions.IncorrectDeadlineDateFormatException;
import papadom.Exceptions.NoDateException;
import papadom.Exceptions.NoTaskException;
import papadom.tasks.Deadline;
import papadom.tasks.Event;
import papadom.tasks.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public Deadline deadlineTaskCreator(String details) throws NoTaskException, NoDateException, IncorrectDeadlineDateFormatException {
        String[] parts = details.split(" /by ");
        if (parts[0] == "") {
            throw new NoTaskException();
        } else if (parts.length == 1) {
            throw new NoDateException();
        }
        try {
            // Determine if the input includes a time
            if (parts[1].contains(" ")) {
                // If it includes a time, parse it as LocalDateTime
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm");
                LocalDateTime dateTime = LocalDateTime.parse(parts[1], dateTimeFormatter);
                return new Deadline(parts[0], dateTime);
            } else {
                // If it doesn't include a time, parse it as LocalDate
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(parts[1], dateFormatter);
                return new Deadline(parts[0], date);
            }
        } catch (DateTimeParseException e) {
            throw new IncorrectDeadlineDateFormatException(); // Throw custom exception if parsing fails
        }
    }
    public Event eventTaskCreator(String details) throws NoTaskException, NoDateException {
        String[] parts = details.split(" /from | /to ");
        if (parts[0] == "") {
            throw new NoTaskException();
        } else if (parts.length <= 2) {
            throw new NoDateException();
        }
        return new Event(parts[0], parts[1], parts[2]);
    }

    public static Task parseTask(String line) {
        // This is a placeholder logic. You will need to implement task parsing based on how tasks are saved.
        // For example, parsing the format `[D][ ] task description (by: Dec 1 2019)`
        return null;
    }

    public static LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            // handle the exception or return a default
            return null;
        }
    }
}
