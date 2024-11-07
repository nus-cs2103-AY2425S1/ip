package chatbuddy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chatbuddy.exception.ChatBuddyException;

/**
 * Represents a deadline task in the ChatBuddy task list.
 * A Deadline contains a description and a date by which it should be completed.
 */
public class Deadline extends Task {
    protected LocalDate by;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructs a Deadline with the specified description and due date.
     *
     * @param description The description of the deadline.
     * @param by The due date of the deadline.
     * @throws ChatBuddyException If the date format is invalid.
     */
    public Deadline(String description, String by) throws ChatBuddyException {
        super(description);
        try {
            this.by = LocalDate.parse(by, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ChatBuddyException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }


    /**
     * Updates the due date of the deadline.
     *
     * @param newDate The new due date in yyyy-MM-dd format.
     * @throws ChatBuddyException If the date is in an invalid format.
     */
    @Override
    public void updateDate(String newDate) throws ChatBuddyException {
        assert newDate != null && !newDate.trim().isEmpty() : "New date must not be null or empty";
        if (newDate.contains("/by") || newDate.contains("/from") || newDate.contains("/to")) {
            throw new ChatBuddyException("Invalid format. Please provide the deadline with the format: <'update'> <number of task> <'date'> <yyyy-MM-dd>");
        }

        try {
            this.by = LocalDate.parse(newDate, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ChatBuddyException("Invalid date format. Please use <yyyy-MM-dd>.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMATTER) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D" + " | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(INPUT_FORMATTER);
    }
}
