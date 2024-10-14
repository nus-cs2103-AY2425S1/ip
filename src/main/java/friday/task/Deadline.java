package friday.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import friday.util.FridayException;

/**
 * Represents a Deadline with a description and deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a Deadline with the specified description and deadline.
     *
     * @param description Description of the deadline.
     * @param by Deadline in "yyyy-MM-dd HHmm" format.
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.deadline = LocalDateTime.parse(by, formatter);
    }

    @Override
    public void update(String updateDetails) throws FridayException {
        String[] details = updateDetails.split(" /by ");
        if (details.length > 0 && !details[0].trim().isEmpty()) {
            this.description = details[0];
        } else {
            throw new FridayException("Deadline description cannot be empty.");
        }
        if (details.length > 1) {
            try {
                this.deadline = LocalDateTime.parse(details[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException e) {
                throw new FridayException("Invalid date format for deadline. Use 'yyyy-MM-dd HHmm'.");
            }
        }
    }

    /**
     * Returns the deadline details in a format suitable for file storage.
     *
     * @return Formatted String for file storage.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D " + super.toFileFormat() + " | " + deadline.format(formatter);
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + deadline.format(formatter) + ")";
    }
}
