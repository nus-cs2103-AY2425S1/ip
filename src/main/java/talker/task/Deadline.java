package talker.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import talker.TalkerException;

/**
 * Represents a Deadline task with a date/time to complete it by
 */
public class Deadline extends Task {

    // input formatter
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    // output formatter
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

    // deadline of Deadline task
    private LocalDateTime by;
    /**
     * Constructor for Deadline task
     *
     * @param description description of task
     * @param by deadline for task
     */
    public Deadline(String description, String by) throws TalkerException {
        super(description, TaskType.DEADLINE);
        try {
            this.by = LocalDateTime.parse(by, INPUT_FORMAT);
        } catch (DateTimeException e) {
            throw new TalkerException("Invalid date-time format. Use dd-MM-yyyy HH:mm (01-01-2024 00:00)");
        }
    }

    /**
     * Constructor for Deadline task with status
     *
     * @param description description of task
     * @param by deadline for task
     * @param isComplete status of task
     */
    public Deadline(String description, String by, boolean isComplete) throws TalkerException {
        super(description, TaskType.DEADLINE, isComplete);
        try {
            this.by = LocalDateTime.parse(by, INPUT_FORMAT);
        } catch (DateTimeException e) {
            throw new TalkerException("Invalid date-time format. Use dd-MM-yyyy HH:mm (01-01-2024 00:00)");
        }
    }

    /**
     * Returns deadline of task
     *
     * @return LocalDateTime object with the deadline of task
     */
    public LocalDateTime getDeadline() {
        return by;
    }

    /**
     * Returns string representation of Deadline for file writing
     *
     * @return String formatted by Task including deadline
     */
    @Override
    public String getSaveFormat() throws TalkerException {
        return super.getSaveFormat() + " | " + by.format(INPUT_FORMAT);
    }

    /**
     * Returns String representation of Deadline
     * @return "[D]" with string representation of Task and included deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}
