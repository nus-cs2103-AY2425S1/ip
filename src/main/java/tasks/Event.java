package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exceptions.GrokInvalidUserInputException;
import exceptions.InvalidIsoFormatException;

/**
 * An Event stores two fields, one 'from' field and one 'to' field, in ISO date format.
 */
public class Event extends Task {

    private LocalDate start;
    private LocalDate end;

    /**
     * Initializes an event with the following inputs, assuming an event that is not yet done.:
     * @param description title, or name, of event
     * @param start starting date of event which MUST be in ISO yyyy-mm-dd format
     * @param end ending date of event which MUST be in ISO yyyy-mm-dd format
     * @throws GrokInvalidUserInputException if user input is invalid - empty description, or non-ISO dates
     */
    public Event(String description, String start, String end) throws GrokInvalidUserInputException {
        super(description);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            throw new InvalidIsoFormatException();
        }
    }

    /**
     * Initializes an event with the following inputs:
     * @param description title, or name, of event
     * @param start starting date of event which MUST be in ISO yyyy-mm-dd format
     * @param end ending date of event which MUST be in ISO yyyy-mm-dd format
     * @param isDone whether the event has been completed or not.
     * @throws GrokInvalidUserInputException if user input is invalid - empty description, or non-ISO dates
     */
    public Event(String description, String start, String end, Boolean isDone) throws GrokInvalidUserInputException {
        super(description, isDone);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            throw new InvalidIsoFormatException();
        }
    }

    @Override
    public String serialize() {
        return String.join(" | ", "E", super.serialize(), start.toString(), end.toString());
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
