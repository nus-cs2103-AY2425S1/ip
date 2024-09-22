package spike.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of the deadline task.
     * @param by          Deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return Deadline of the task.
     */
    public LocalDateTime getDate() {
        return LocalDateTime.from(this.by);
    }

    /**
     * Converts a LocalDateTime object to a string.
     * The format of the string is "d MMM yyyy HH:mm".
     *
     * @param dateTime LocalDateTime object to be converted.
     * @return String representation of the LocalDateTime object.
     */
    public String dateToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"));
    }

    /**
     * Updates the task based on the update type and updated part.
     *
     * @param updateType  The type of update to be made.
     * @param updatedPart The updated part of the task.
     * @return Task with the updated part.
     */
    public Task updateTask(String updateType, String updatedPart) throws IllegalArgumentException {
        switch (updateType) {
        case "description":
            return new Deadline(updatedPart, this.by);
        case "date time":
            return new Deadline(this.getDescription(), LocalDateTime.parse(updatedPart));
        default:
            throw new IllegalArgumentException("Please enter a valid option");
        }
    }

    /**
     * Returns the task type.
     *
     * @return Task type.
     */
    @Override
    public String getTaskType() {
        return "deadline";
    }

    /**
     * Converts the Deadline object to a string to be written to a file.
     * The format of the string is "D | 0 | description | deadline".
     *
     * @return String representation of the Deadline object to be written to a file.
     */
    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + this.by.toString();
    }

    /**
     * Converts the Deadline object to a string.
     * The format of the string is "[D][status] description (by: deadline)".
     *
     * @return String representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateToString(this.by) + ")";
    }
}
