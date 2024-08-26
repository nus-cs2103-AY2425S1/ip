package optimus;

// Let ChatGPT check and suggest comments and JavaDocs according to CS2103T style guide
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline that includes a description and a due date.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the task.
     * @param by The due date of the task in the format yyyy-mm-dd.
     */
    public Deadline(String description, String by) throws OptimusException {
        super(description);
        try {
            this.by = LocalDate.parse(by); // Create date from String
        } catch (DateTimeParseException e) {
            throw new OptimusException("Invalid date format for deadline. Please use yyyy-mm-dd.");
        }
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return A string in the format: [D][ ] description (by: MMM d yyyy)
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Formats the Deadline task data for saving to a file.
     *
     * @return A string in the format: D | 0/1 | description | yyyy-mm-dd
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
