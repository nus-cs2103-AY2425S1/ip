package diomon.task;

import diomon.parser.Parser;

import java.time.LocalDate;

/**
 * The {@code Deadline} class represents a task with a specific deadline.
 * It extends the {@link Task} class, adding the ability to track a due date.
 * Each Deadline task has a description and a due date.
 */
public class Deadline extends Task{
    public static final String TYPEICON = "D";
    private LocalDate deadline;

    /**
     * Constructs a new {@code Deadline} task with the given description and deadline.
     *
     * @param description The description of the task.
     * @param deadline The due date of the task.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructs a new {@code Deadline} task with the given completion status,
     * description, and deadline.
     *
     * @param complete {@code true} if the task is completed, {@code false} otherwise.
     * @param description The description of the task.
     * @param deadline The due date of the task.
     */
    public Deadline(boolean complete, String description, LocalDate deadline) {
        super(complete, description);
        this.deadline = deadline;
    }

    /**
     * Returns the icon representing the task type ("D" for Deadline).
     *
     * @return A string representing the task type icon.
     */
    @Override
    public String getTypeIcon() {
        return TYPEICON;
    }

    /**
     * Returns a string representation of the {@code Deadline} task, including
     * the description and formatted deadline information.
     *
     * @return A string representing the {@code Deadline} task.
     */
    @Override
    public String toString() {
        return super.description + String.format(" (by: %s (%s))", deadline.format(Parser.DATEFORMATTER), deadline.getDayOfWeek());
    }

    /**
     * Returns a string representation of the {@code Deadline} task formatted for
     * storage, which includes the type, status, description, and formatted deadline.
     *
     * @return A string formatted for storage.
     */
    @Override
    public String toStorageString(){
        return String.format("%s|%s|%s|%s", getTypeIcon(), getStatusIcon(),description, deadline.format(Parser.DATEFORMATTER));
    }

    /**
     * Checks whether this {@code Deadline} task is equal to another object.
     * Two {@code Deadline} tasks are considered equal if they have the same deadline,
     * completion status, and description.
     *
     * @param other The object to compare to.
     * @return {@code true} if the tasks are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Deadline temp) {
            return temp.deadline.equals(deadline) && temp.completed == completed && temp.description.equals(description);
        }
        return false;
    }
}
