package delta.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import delta.exception.DeltaException;

/**
 * Subclass of Task class.
 * Includes the name of the task and a deadline for the task to be completed by.
 */
public class Deadline extends Task {
    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy ha");
    protected LocalDateTime by;

    /**
     * Creates a Deadline instance.
     * Only used by load method in Storage class to load up saved tasks.
     *
     * @param description Name of deadline task.
     * @param by Deadline for task.
     * @throws DeltaException If save file is corrupted and time/date retrieved has the wrong format.
     */
    public Deadline(String description, String by) throws DeltaException {
        super(description, TaskType.Deadline);
        try {
            this.by = LocalDateTime.parse(by, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DeltaException("""
                    OOPS!!! Save File has been corrupted!
                    \t Please delete the save file.
                    \t Location: ./data/DeltaList.txt""");
        }
    }

    /**
     * Overloaded constructor for Deadline instance.
     *
     * @param description Name of deadline task.
     * @param by Deadline for task stored as a LocalDateTime object.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.Deadline);
        this.by = by;
    }

    public String getBy() {
        return by.format(FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }

    /**
     * Formats Deadline for saving.
     *
     * @return String Formatted details of Deadline.
     */
    @Override
    public String saveDetails() {
        return "D | " + super.saveDetails() + " | " + getBy();
    }
}
