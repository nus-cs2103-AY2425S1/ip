package Buu;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * The Parser class is responsible for interpreting user input and converting it
 * into executable commands or parsing date and time strings into LocalDateTime objects.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding Command object.
     * This method identifies the type of command based on the input and creates the appropriate Command subclass.
     *
     * @param input The user input string to be parsed.
     * @return The corresponding Command object based on the input.
     * @throws GPTException If the input cannot be parsed into a valid command.
     */
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
        } else if (input.startsWith("find")) {
            return new FindCommand(input);
        } else {
            return new HelpCommand(); // Return a HelpCommand when an unrecognized input is entered
        }
    }

    /**
     * Parses a date and time string into a LocalDateTime object using Streams.
     * This method attempts to parse the input string using several predefined date and time formats.
     * If the input matches one of the formats, a corresponding LocalDateTime object is returned.
     *
     * @param dateTimeStr The date and time string to be parsed.
     * @return A LocalDateTime object representing the parsed date and time, or null if the input is invalid.
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter[] dateTimeFormatters = new DateTimeFormatter[]{
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy-M-d HHmm")
        };

        DateTimeFormatter[] dateFormatters = new DateTimeFormatter[]{
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        };

        // Use Streams to try parsing with dateTimeFormatters first
        Optional<LocalDateTime> parsedDateTime = Stream.of(dateTimeFormatters)
                .map(formatter -> parseWithDateTimeFormatter(dateTimeStr, formatter))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();

        if (parsedDateTime.isPresent()) {
            return parsedDateTime.get();
        }

        // Use Streams to try parsing with dateFormatters if no LocalDateTime match
        Optional<LocalDate> parsedDate = Stream.of(dateFormatters)
                .map(formatter -> parseWithDateFormatter(dateTimeStr, formatter))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();

        return parsedDate.map(LocalDate::atStartOfDay).orElse(null);
    }

    /**
     * Attempts to parse a date-time string with the given formatter.
     * @param dateTimeStr The date-time string to parse.
     * @param formatter The date-time formatter to use.
     * @return An Optional of LocalDateTime if parsing is successful, or an empty Optional if an exception occurs.
     */
    private static Optional<LocalDateTime> parseWithDateTimeFormatter(String dateTimeStr, DateTimeFormatter formatter) {
        try {
            return Optional.of(LocalDateTime.parse(dateTimeStr, formatter));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    /**
     * Attempts to parse a date string with the given formatter.
     * @param dateStr The date string to parse.
     * @param formatter The date formatter to use.
     * @return An Optional of LocalDate if parsing is successful, or an empty Optional if an exception occurs.
     */
    private static Optional<LocalDate> parseWithDateFormatter(String dateStr, DateTimeFormatter formatter) {
        try {
            return Optional.of(LocalDate.parse(dateStr, formatter));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }
}
