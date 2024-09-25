package Mentos.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task in the format "yyyy-MM-dd HHmm".
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Updates the deadline of the task.
     *
     * @param by The new deadline for the task in the format "yyyy-MM-dd HHmm".
     */
    public void updateBy(String by) {
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns the string representation of the Deadline task,
     * including its status, description, and deadline.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString(){
        return String.format("[D] %s (by: %s)",super.toString(),this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")));
    }
}
