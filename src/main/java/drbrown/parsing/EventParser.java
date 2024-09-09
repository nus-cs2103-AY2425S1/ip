package drbrown.parsing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import drbrown.command.AddCommand;
import drbrown.command.Command;
import drbrown.task.Event;
import drbrown.task.Task;
import drbrown.utils.DrBrownException;

/**
 * A parser that handles the "event" command input.
 * Responsible for parsing the input to create an {@link Event} task
 * with a description, start date, and end date.
 */
public class EventParser {

    /** The formatter for parsing date and time in the specific format "MMM dd yyyy HH:mm". */
    static final DateTimeFormatter FILE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Parses the input to create an {@link AddCommand} that adds an {@link Event} task.
     *
     * @param userInput  The full user input as a string.
     * @param inputSplit An array of strings containing the user's input split by spaces.
     * @return An instance of {@link AddCommand} containing the {@link Event} task.
     * @throws DrBrownException If the input is invalid, such as missing description, dates,
     *                          incorrect order of dates, or if the date format is incorrect.
     */
    public static Command parse(String userInput, String[] inputSplit) throws DrBrownException {
        assert inputSplit != null : "Input string array should not be null";
        try {
            if (inputSplit.length == 1) {
                throw new DrBrownException("Great Scott! You can't add an event without a description "
                        + "and from and to date!\nUse the format: "
                        + "event {description} /from {date} /to {date} /priority {priority}");
            }

            boolean containsValidString = userInput.contains("/from") || userInput.contains("/to") || userInput.contains("/priority");
            boolean correctOrderString = userInput.indexOf("/from") > userInput.indexOf("/to");

            if (!containsValidString || correctOrderString) {
                throw new DrBrownException("Looks like your Uncle Joey didn't make parole again... "
                        + "and you missed the date! Let's fix that event!\nUse the format: "
                        + "event {description} /from {date} /to {date} /priority {priority}");
            }

            String[] eventSplit = inputSplit[1].split("/from | /to | /priority");

            Task event = new Event(false, eventSplit[0].trim(),
                    LocalDateTime.parse(eventSplit[1].trim(), FILE_DATE_TIME_FORMATTER),
                    LocalDateTime.parse(eventSplit[2].trim(), FILE_DATE_TIME_FORMATTER),
                    Task.Priority.valueOf(eventSplit[3].trim()));

            return new AddCommand(event);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException | IllegalArgumentException e) {
            throw new DrBrownException("Looks like your Uncle Joey didn't make parole again... "
                    + "and you missed the date! Let's fix that event!\nUse the format: "
                    + "event {description} /from {MMM dd yyyy HH:mm} /to {MMM dd yyyy HH:mm} /priority {priority}");
        }
    }

}
