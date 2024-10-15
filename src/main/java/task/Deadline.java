package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task that has a specific deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime BY;

    /**
     * Constructs a new Deadline task with a description and a due date.
     *
     * @param desc The description of the task.
     * @param by   The due date and time for the task, as a LocalDateTime object.
     */
    public Deadline(String desc, LocalDateTime by) {
        super(desc);
        this.BY = by;
    }

    /**
     * Returns the type of the task as a string. For a deadline, this is always "[D]".
     *
     * @return A string representing the type of task, which is "[D]" for deadlines.
     */
    @Override
    public String getType() {
        return "[D]";
    }

    /**
     * Returns a string representation of the Deadline task,
     * including the description and formatted deadline.
     * The deadline is displayed in the format "MMM dd yyyy, h:mm a".
     *
     * @return A string representing the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return super.toString() + " (by: " + this.BY.format(formatter) + ")";
    }

    /**
     * Returns the due date and time of the task.
     *
     * @return A LocalDateTime object representing the deadline.
     */
    public LocalDateTime getBy(){
        return this.BY;
    }
}
