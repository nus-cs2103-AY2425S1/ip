package chatbuddy.task;

import chatbuddy.exception.ChatBuddyException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task in the ChatBuddy task list.
 * An Event contains a description, a start date, and an end date.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructs an Event with the specified description, start date, and end date.
     *
     * @param description The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     * @throws ChatBuddyException If the date format is invalid or the start date is after the end date.
     */
    public Event(String description, String from, String to) throws ChatBuddyException {
        super(description);

        if (from == null || to == null) {
            throw new ChatBuddyException("Start and end dates must not be null.");
        }

        try {
            this.from = LocalDate.parse(from, INPUT_FORMATTER);
            this.to = LocalDate.parse(to, INPUT_FORMATTER);

            if (this.from.isAfter(this.to)) {
                throw new ChatBuddyException("Start date must be before or equal to the end date.");
            }
        } catch (DateTimeParseException e) {
            throw new ChatBuddyException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    /**
     * Updates the event's start and end dates.
     *
     * @param newDate The new date range in yyyy-MM-dd /to yyyy-MM-dd format.
     * @throws ChatBuddyException If the date format is invalid or start date is after end date.
     */
    @Override
    public void updateDate(String newDate) throws ChatBuddyException {
        assert newDate != null && !newDate.trim().isEmpty() : "New date must not be null or empty";
        try {
            String[] dateRange = newDate.split(" /to ");
            if (dateRange.length != 2 || !newDate.contains("/from")) {
                throw new ChatBuddyException("Invalid date format. Please provide both start and end dates with the format: <'update'> <number of task> <'date'> <yyyy-MM-dd> <'/to'> <yyyy-MM-dd>");
            }
            this.from = LocalDate.parse(dateRange[0], INPUT_FORMATTER);
            this.to = LocalDate.parse(dateRange[1], INPUT_FORMATTER);
            if (this.from.isAfter(this.to)) {
                throw new ChatBuddyException("Start date must be before or equal to end date.");
            }
        } catch (DateTimeParseException e) {
            throw new ChatBuddyException("Invalid date format. Please use <yyyy-MM-dd>.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMATTER) + " to: " + to.format(OUTPUT_FORMATTER) + ")";
    }

    @Override
    public String toFileFormat() {
        return "E" + " | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(INPUT_FORMATTER) + " | " + to.format(INPUT_FORMATTER);
    }
}
