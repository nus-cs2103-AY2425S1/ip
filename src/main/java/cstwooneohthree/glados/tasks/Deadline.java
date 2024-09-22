package cstwooneohthree.glados.tasks;

import java.time.LocalDate;

import cstwooneohthree.glados.enums.TaskType;

/**
 * Deadline class that handles deadline date.
 *
 * @author jayjay19630
 */
public class Deadline extends Task {

    /* Local date for Deadline */
    private LocalDate deadline;

    /**
     * Constructs new Deadline object by calling parent and setting Deadline date.
     *
     * @param description Description of Deadline.
     * @param deadline Local date of Deadline.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * {@inheritDoc}
     * Returns Deadline task type icon.
     *
     * @return [D] icon.
     */
    @Override
    public String getTaskTypeIcon() {
        return "[D]";
    }

    /**
     * {@inheritDoc}
     * Returns Deadline task type.
     *
     * @return Deadline enum.
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
    }

    /**
     * {@inheritDoc}
     * Returns Deadline description.
     *
     * @return Description string.
     */
    @Override
    public String getDescription() {
        return this.description + " (by: " + this.deadline.toString() + ")";
    }

    /**
     * {@inheritDoc}
     * Returns Deadline string for saving into data folder.
     *
     * @return Deadline save format string.
     */
    @Override
    public String getSaveFormat() {
        return this.description + " | " + this.deadline.toString();
    }
}
