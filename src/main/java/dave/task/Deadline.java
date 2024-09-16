package dave.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import dave.exceptions.InvalidDescriptionException;

/**
 * Represents a deadline task. A deadline task contains a description and a due date/time.
 * It inherits from the Task class.
 */
public class Deadline extends Task {

    /** The due date and time of the task */
    private final LocalDateTime dueDateTime;

    /**
     * Constructs a Deadline task with the specified description and due date/time.
     *
     * @param description The description of the deadline task, which includes the due date and time.
     * @throws InvalidDescriptionException If the format of the description is invalid.
     */
    public Deadline(String description) throws InvalidDescriptionException {
        super(description.split("/by ")[0].trim());
        String[] arguments = description.split("/by ");

        if (arguments.length < 2 || arguments[1].trim().isEmpty()) {
            throw new InvalidDescriptionException("Huh! Please provide a deadline task in the format: "
                    + "<task> /by yyyy-MM-dd HHmm");
        }

        // Try parsing the dueDateTime directly in yyyy-MM-dd HHmm format
        this.dueDateTime = parseDateTime(arguments[1].trim());
    }

    /**
     * Parses the provided date and time string into a LocalDateTime.
     *
     * @param dateTime The date and time string in yyyy-MM-dd HHmm format.
     * @return The parsed LocalDateTime.
     * @throws InvalidDescriptionException If the date and time format is incorrect.
     */
    private LocalDateTime parseDateTime(String dateTime) throws InvalidDescriptionException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDescriptionException("Invalid date/time format. "
                    + "Please use yyyy-MM-dd HHmm (e.g., 2024-09-14 1800)");
        }
    }

    /**
     * Returns the due date and time of the task.
     *
     * @return The due date and time as a {@code LocalDateTime}.
     */
    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    /**
     * Converts the deadline into a string format that is suitable for saving to a file.
     *
     * @return The formatted string representing the deadline task.
     */
    @Override
    public String write() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String formattedDateTime = dueDateTime != null ? dueDateTime.format(formatter) : "unknown date";
        return String.format("D | %d | %s | %s\n", this.getIsDone() ? 1 : 0,
                this.getDescription(), formattedDateTime);
    }

    /**
     * Returns the string representation of the deadline task for display purposes.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedDateTime = dueDateTime != null ? dueDateTime.format(formatter) : "unknown date";
        return "[D]" + super.toString() + " (by: " + formattedDateTime + ")";
    }
}
