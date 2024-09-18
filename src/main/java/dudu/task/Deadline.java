package dudu.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task
 */
public class Deadline extends Task {
    private static final String displayDateFormat = "MMM d yyyy";
    private static final String storageDateFormat = "yyyy-MM-dd";

    private LocalDate byDate;

    /**
     * Constructs a deadline task with the specified description and due date.
     * By default, the task is uncompleted.
     *
     * @param description The description of the task.
     * @param byDate The date by which the task should be completed.
     */
    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
    }

    /**
     * Formats the task into a string for storage, including the task type ("D" for deadline),
     * its completion status, description, and due date.
     *
     * @return The formatted string representation of the deadline task for storage.
     */
    public String formatString() {
        String by = this.byDate.format(DateTimeFormatter.ofPattern(storageDateFormat));
        return String.format("D | %s | %s", super.formatString(), by);
    }

    /**
     * Returns a string representation of the task, including its status
     * (marked or unmarked), its description and due date.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String by = this.byDate.format(DateTimeFormatter.ofPattern(displayDateFormat));
        return String.format("[D] %s (by: %s)", super.toString(), by);
    }

    /**
     * Compares this Task to another object for equality. Two tasks are considered
     * equal if they have the same description, completion status and due date.
     *
     * @param object The object to compare this Task with.
     * @return true if the other object is a Task with the same description, status and due date, false otherwise.
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
