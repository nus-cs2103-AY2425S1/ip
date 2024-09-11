package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A deadline object is for tasks that have to be completed by a certain date.
 *
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
    * Initialises the Deadline object as the constructor.
     *
     * @param name The description of the Deadline task.
     * @param deadline The date the task should be completed by, using the {@link LocalDate} format.
    *
     */
    public Deadline(String name, LocalDate deadline) {
        super(name);
        setDeadline(deadline);
    }

    /**
     * Set the deadline of the Deadline object to a new value, by inputting the new deadline using the
     * {@link LocalDate} format.
     *
     * @param deadline The date the task should be completed by, using the {@link LocalDate} format.
     *
     */
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    protected String getWriteFormat() {
        return "D , " + (isDone ? "1" : "0") + " , " + name + " , " + deadline;
    }

    @Override
    public void snooze(LocalDate newDate) {
        deadline = newDate;
    }

    /**
     * Overrides the existing toString() method in the Task class to fit the required display requirement for
     * Deadline objects.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
