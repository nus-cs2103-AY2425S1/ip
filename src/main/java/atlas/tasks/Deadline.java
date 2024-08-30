package atlas.tasks;

import java.time.LocalDateTime;

import atlas.utils.DateTime;

/**
 * Represents a deadline class containing the methods to create a deadline and represent it as strings.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Creates a deadline object.
     *
     * @param name The name or description of the deadline.
     * @param deadline The date and time to finish this deadline by.
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * @return String The format in which this deadline is stored in a file.
     */
    @Override
    public String toFileString() {
        return String.format("D %s | %s", super.toFileString(),
                this.deadline.format(DateTime.DATE_TIME_FILE_OUTPUT_FORMATTER));
    }

    /**
     * @return String The format in which this deadline is shown in the ui.
     */
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(),
                this.deadline.format(DateTime.DATE_TIME_PRINT_OUTPUT_FORMATTER));
    }
}
