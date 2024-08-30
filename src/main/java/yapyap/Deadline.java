package yapyap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task with a description and a due date, which can be specified
 * in either date-time format ("d/M/yyyy HHmm") or date format ("yyyy-MM-dd").
 * The task can be marked as done or not done. The class also contains a boolean field to
 * distinguish which of the 2 valid formats is the due date stored in.
 */
public class Deadline extends Task {

    private LocalDateTime byDateTime;
    private LocalDate byDate;
    private boolean isDateTimeFormat;

    /**
     * Creates a Deadline with the specified description and due date.
     * The due date can be in the format "d/M/yyyy HHmm" or "yyyy-MM-dd".
     *
     * @param description Description of the deadline.
     * @param by Due date of the deadline in either "d/M/yyyy HHmm" or "yyyy-MM-dd" format.
     * @throws YapperBotException If the date format is invalid.
     */
    public Deadline (String description, String by) throws YapperBotException {
        super(description);
        this.byDate = null;
        this.byDateTime = null;
        this.isDateTimeFormat = false;

        try {
            this.byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            this.isDateTimeFormat = true;
        } catch (DateTimeParseException e1) {
            try {
                this.byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                this.isDateTimeFormat = false;
            } catch (DateTimeParseException e2) {
                throw new YapperBotException("Invalid date format! Please use either 'd/M/yyyy HHmm' "
                        + "or 'yyyy-MM-dd'.");
            }
        }
    }

    /**
     * Creates a Deadline with the specified description, due date, and completion status.
     * The due date can be in the format "d/M/yyyy HHmm" or "yyyy-MM-dd".
     *
     * @param description Description of the deadline.
     * @param by Due date of the deadline in either "d/M/yyyy HHmm" or "yyyy-MM-dd" format.
     * @param isDone Completion status of the deadline.
     * @throws YapperBotException If the date format is invalid.
     */
    public Deadline (String description, String by, boolean isDone) throws YapperBotException {
        super(description);
        this.byDate = null;
        this.byDateTime = null;
        this.isDateTimeFormat = false;
        this.isDone = isDone;

        try {
            this.byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            this.isDateTimeFormat = true;
        } catch (DateTimeParseException e1) {
            try {
                this.byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                this.isDateTimeFormat = false;
            } catch (DateTimeParseException e2) {
                throw new YapperBotException("Invalid date format! Please use either 'd/M/yyyy HHmm' "
                        + "or 'yyyy-MM-dd'.");
            }
        }
    }

    /**
     * Returns a string representation of the deadline, including its status, description,
     * and due date formatted appropriately based on the input format.
     *
     * @return Formatted string of the deadline task.
     */
    @Override
    public String toString() {
        if (isDateTimeFormat) {
            return "[D]" + super.toString() + " (by: "
                    + this.byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + this.byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
    }

    /**
     * Converts the deadline to a format suitable for saving to a file.
     * The format includes the task type, status, description, and due date formatted based on the input format.
     *
     * @return Formatted string representation of the deadline for saving.
     */
    @Override
    public String toSaveFormat() {
        if (this.isDateTimeFormat) {
            return "D | " + (this.isDone ? 1 : 0) + " | " + this.description
                    + " | " + this.byDateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } else {
            return "D | " + (this.isDone ? 1 : 0) + " | " + this.description
                    + " | " + this.byDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }
}
