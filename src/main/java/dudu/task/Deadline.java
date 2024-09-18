package dudu.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private static final String displayDateFormat = "MMM d yyyy";
    private static final String storageDateFormat = "yyyy-MM-dd";

    private LocalDate byDate;

    /**
     * Constructs a Deadline task.
     *
     * @param description Task description.
     * @param byDate Date to do task by.
     */
    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
    }

    /**
     * Returns task in storage format.
     */
    @Override
    public String toStorageString() {
        String by = this.byDate.format(DateTimeFormatter.ofPattern(storageDateFormat));
        return String.format("D | %s | %s", super.toStorageString(), by);
    }

    /**
     * Returns task in display format.
     */
    @Override
    public String toString() {
        String by = this.byDate.format(DateTimeFormatter.ofPattern(displayDateFormat));
        return String.format("[D] %s (by: %s)", super.toString(), by);
    }

    /**
     * Compares this object with another object.
     * Compares for same descriptions and date to do task by.
     *
     * @param object Object to compare with.
     * @return True if object has the same fields else false.
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Deadline)) {
            return false;
        }
        Deadline otherDeadlineTask = (Deadline) object;
        boolean hasSameByDate = this.byDate.equals(otherDeadlineTask.byDate);
        boolean hasSameTaskDetails = super.equals(otherDeadlineTask);
        return hasSameByDate && hasSameTaskDetails;
    }
}
