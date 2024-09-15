package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a class for deadline-type tasks.
 */
public class Deadline extends Task {

    private LocalDate endTime;

    /**
     * Creates a deadline task.
     *
     * @param name Name for deadline task.
     * @param endTime Deadline date.
     */
    public Deadline(String name, LocalDate endTime) {
        super(name);
        this.endTime = endTime;
    }

    /**
     * Creates a deadline task, marking it as done or undone.
     *
     * @param name Name for deadline task.
     * @param endTime Deadline date.
     * @param done True if the task is done.
     */
    public Deadline(String name, LocalDate endTime, boolean done) {
        super(name, done);
        this.endTime = endTime;
    }

    /**
     * Returns string format of the Task.
     *
     * @return String format of the Task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + endTime.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy")) + ")";
    }

    /**
     * Returns data format of the Task.
     *
     * @return Data format of the Task.
     */
    @Override
    public String toData() {
        return "D" + super.toData() + "%" + this.endTime;
    }
}
