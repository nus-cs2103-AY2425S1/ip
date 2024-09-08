package opus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline. 
 */
public class Deadline extends Task {
    protected LocalDate byDateTime;
    protected String byString;

    /**
     * Constructs a Deadline task with the specified description and deadline date.
     * If the date cannot be parsed, it is stored as a string.
     *
     * @param description The description of the task.
     * @param by The deadline in the format [yyyy-MM-dd].
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.byDateTime = LocalDate.parse(by, inputFormat);
        } catch (DateTimeParseException e) {
            this.byString = by;
        }
    }

    /**
     * Returns the formatted deadline. If the deadline was successfully parsed,
     * it is returned in [MMM dd yyyy] format. Otherwise, the original
     * string is returned.
     *
     * @return The formatted deadline or the original string if parsing failed.
     */
    private String getBy() {
        if (byDateTime != null) {
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return byDateTime.format(outputFormat);
        } else {
            return byString;
        }
    }

    /**
     * Returns the string representation of the deadline, including its description
     * and whether it is done.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + description + " (by: " + getBy() + ")";
    }

    /**
     * Returns the save format for the deadline task to be written to storage.
     *
     * @return The formatted string for saving the deadline task.
     */
    @Override
    public String toSaveFormat() {
        return "D|" + (isDone ? "1" : "0") + "|" + description + "|" + getBy();
    }

    /**
     * Creates a Deadline object from a formatted string read from storage.
     *
     * @param fullLine The line read from the storage file.
     * @return A new Deadline object based on the parsed string.
     */
    public static Task fromFileFormat(String fullLine) {
        String[] parts = fullLine.split("\\|");
        Deadline deadline = new Deadline(parts[2], parts[3]);
        if (parts[1].equals("1")) {
            deadline.markAsDone();
        }
        return deadline;
    }
}
