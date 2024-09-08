package qwerty.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * This class encapsulates a Deadline type task.
 * A Deadline contains a time by which it is due.
 */
public class Deadline extends DeadlineTask {

    /**
     * Creates a new Deadline instance.
     *
     * @param description String description of the deadline.
     * @param by Due date of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadlineAsString() + ")";
    }

    @Override
    public List<String> getAllDetails() {
        return Arrays.asList(
                "D",
                getStatusIcon(),
                getDescription(),
                getDeadline().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
        );
    }
}
