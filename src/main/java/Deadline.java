import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * This class encapsulates a Deadline type task.
 * A Deadline needs to be done before a specific time.
 */
public class Deadline extends Task {
    /** The deadline of the task */
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline as a formatted string.
     *
     * @return Formatted string representing the deadline.
     */
    public String getDeadline() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadline() + ")";
    }

    @Override
    public List<String> getAllDetails() {
        return Arrays.asList(
                "D",
                getStatusIcon(),
                getDescription(),
                by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
        );
    }
}
