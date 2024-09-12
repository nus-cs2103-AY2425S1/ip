package eevee;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a type of Task that has a start and end time.
 */
public class Event extends Task {
    private static final String DATE_FORMAT = "MMM d yyyy";
    protected String from;
    protected String to;

    /**
     * Constructs an Event task using the given description, from and to date.
     *
     * @param description The String description of the Event.
     * @param from The starting time of the Event, can be in the form of date of format yyyy-mm-dd.
     * @param to The ending time of the Event, can be in the form of date of format yyyy-mm-dd.
     */
    public Event(String description, String from, String to) {
        super(description.trim());
        this.from = formatDate(from.trim());
        this.to = formatDate(to.trim());
        validateDates();
    }

    /**
     * Formats a date given in the form of yyyy-mm-dd to mmm d yyyy.
     * Does not change date inputs in other formats.
     *
     * @param date The String input for the date.
     * @return The correctly formatted date.
     */
    private String formatDate(String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            return parsedDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (DateTimeException ignored) {
            return date;
        }
    }

    /**
     * Checks that the start date does not come after the end date.
     */
    private void validateDates() {
        try {
            LocalDate date = LocalDate.parse(this.from, DateTimeFormatter.ofPattern(DATE_FORMAT));
            LocalDate date2 = LocalDate.parse(this.to, DateTimeFormatter.ofPattern(DATE_FORMAT));

            if (date.isAfter(date2)) {
                throw new EeveeException("Start date occurs before end date! Please check your input.");
            }
        } catch(DateTimeException ignored) {

        } catch (EeveeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + from + " to " + to + ")";
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString() + "|" + from + "|" + to;
    }
}
