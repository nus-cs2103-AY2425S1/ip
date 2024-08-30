package gale;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
public class Parser {
    private static ArrayList<DateTimeFormatter> formatters = new ArrayList<>(
            List.of(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                    DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"))
    );

    public static ArrayList<DateTimeFormatter> getFormatters() {
        return formatters;
    }
    public static LocalDateTime parseDateTime(String by) throws DateTimeParseException {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(by, formatter);
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new DateTimeParseException("Unable to parse date & time", by, 0);
    }

    public static Event parseEvent(String input) throws GaleException {
        if (input.length() <= 6) {
            throw new GaleException("Your event is lost in the wind! Please use 'event [description] /from [start] /to [end]'.");
        }
        String[] strA = input.substring(6).split("/from|/to");
        if (strA.length != 3 || strA[0].trim().isEmpty() || strA[1].trim().isEmpty()
                || strA[2].trim().isEmpty()) {
            throw new GaleException("Your event is lost in the wind! Please use 'event [description] /from [start] /to [end]'.");
        }
        String description = strA[0].trim();
        String from = strA[1].trim();
        String to = strA[2].trim();
        try {
            return new Event(description, from, to);
        } catch (DateTimeParseException e) {
            throw new GaleException("Oops! The wind blew away your date. Please use 'yyyy-MM-dd HH:mm' or 'd/M/yyyy HH:mm'.");
        }
    }

    public static Task parseTask(String input) throws GaleException {
        if (input.startsWith("todo")) {
            return parseToDo(input);
        } else if (input.startsWith("deadline")) {
            return parseDeadline(input);
        } else if (input.startsWith("event")) {
            return parseEvent(input);
        } else {
            throw new GaleException("Whoosh! The wind blew away your command. Please use 'todo', 'deadline' or 'event'.");
        }
    }
    public static ToDo parseToDo(String input) throws GaleException {
        if (input.length() <= 5) {
            throw new GaleException("Oops! The wind blew away your to-do description. Please use: 'todo [description]'.");
        }
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new GaleException("Oops! The wind blew away your to-do description. Please furnish it again.");
        }
        return new ToDo(description);
    }

    public static Deadline parseDeadline(String input) throws GaleException {
        if (input.length() <= 9) {
            throw new GaleException("Your deadline got tossed by the wind! Please use 'deadline [description] /by [date]'.");
        }
        String[] strA  = input.substring(9).split("/by");
        if (strA.length != 2 || strA[0].trim().isEmpty() || strA[1].trim().isEmpty()) {
            throw new GaleException("Your deadline got tossed by the wind! Please use 'deadline [description] /by [date]'.");
        }
        String description = strA[0].trim();
        String by = strA[1].trim();
        try {
            return new Deadline(description, by);
        } catch (DateTimeParseException e) {
            throw new GaleException("Oops! The wind blew away your date. Please use 'yyyy-MM-dd HH:mm' or 'd/M/yyyy HH:mm'.");
        }
    }
}
