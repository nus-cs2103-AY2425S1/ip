package socchat.task.deadline;

import java.time.LocalDateTime;

import Parser.Parser;
import socchat.task.Task;

/**
 * The Deadline class represents a task with a deadline.
 * It extends the Task class and includes a date and time by which the task must be completed.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task with the specified description and deadline.
     * The task is initially not done.
     *
     * @param description the description of the task
     * @param by          the deadline (due date) of the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    public Deadline(String description, LocalDateTime by, String tagName) {
        super(description, tagName);
        this.by = by;
    }

    /**
     * Constructs a new Deadline task with the specified description, deadline, and completion status.
     *
     * @param description the description of the task
     * @param by          the deadline of the task
     * @param isDone      the initial completion status of the task
     */
    public Deadline(String description, LocalDateTime by, Boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, Boolean isDone, String tagName) {
        super(description, isDone, tagName);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + Parser.dateToString(by) + ")"
                + "<tag: " + tagName + ">";
    }

    @Override
    public String toSave() {
        return "D" + " | " + super.getDoneStatus()
                + " | " + super.getDescription()
                + " | " + Parser.dateToString(by)
                + " | "
                +  " | " + super.getTagName();
    }
}
