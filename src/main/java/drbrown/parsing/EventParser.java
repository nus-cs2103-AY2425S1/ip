package drbrown.parsing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import drbrown.command.AddCommand;
import drbrown.command.Command;
import drbrown.task.Event;
import drbrown.task.Task;
import drbrown.utils.DrBrownException;
import drbrown.utils.Ui;

/**
 * A parser that handles the "event" command input.
 * Responsible for parsing the input to create an {@link Event} task
 * with a description, start date, and end date.
 */
public class EventParser extends Parsing {

    /**
     * The formatter for parsing date and time in the specific format "MMM dd yyyy HH:mm".
     */
    static final DateTimeFormatter FILE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /** The user input string to be parsed. */
    private String userInput;

    /**
     * Constructs an {@code EventParser} with the given user input and input split array.
     *
     * @param userInput The full user input string.
     * @param inputSplit The user input split into parts for parsing.
     */
    public EventParser(String userInput, String[] inputSplit) {
        super(inputSplit);
        this.userInput = userInput;
    }

    /**
     * Parses the input to create an {@link AddCommand} that adds an {@link Event} task.
     *
     * @return An instance of {@link AddCommand} containing the {@link Event} task.
     * @throws DrBrownException If the input is invalid, such as missing description, dates,
     *                          incorrect order of dates, or if the date format is incorrect.
     */
    public Command parse() throws DrBrownException {
        assert this.getInputSplit() != null : "Input string array should not be null";
        try {
            if (this.getInputSplit().length == 1) {
                throw new DrBrownException(Ui.getEventExceptionNoDescription());
            }

            boolean containsValidString = this.userInput.contains("/from") || this.userInput.contains("/to")
                    || this.userInput.contains("/priority");
            boolean correctOrderString = this.userInput.indexOf("/from") > this.userInput.indexOf("/to");

            if (!containsValidString || correctOrderString) {
                throw new DrBrownException(Ui.getEventExceptionNoDate());
            }

            String[] eventSplit = this.getInputSplit()[1].split("/from | /to | /priority");

            Task event = new Event(false, eventSplit[0].trim(),
                    LocalDateTime.parse(eventSplit[1].trim(), FILE_DATE_TIME_FORMATTER),
                    LocalDateTime.parse(eventSplit[2].trim(), FILE_DATE_TIME_FORMATTER),
                    Task.Priority.valueOf(eventSplit[3].trim()));

            return new AddCommand(event);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException | IllegalArgumentException e) {
            throw new DrBrownException(Ui.getEventExceptionOthers());
        }
    }
}
