package cstwooneohthree.glados.tasks;

import java.time.LocalDate;

import cstwooneohthree.glados.enums.TaskType;

/**
 * Event class that handles from and start dates.
 *
 * @author jayjay19630
 */
public class Event extends Task {

    /* Local dates for start and end dates for Event */
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructs new Event object by calling parent and setting dates.
     *
     * @param description Description of Event.
     * @param startDate Local date of Event start date.
     * @param endDate Local date of Event end date.
     */
    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * {@inheritDoc}
     * Returns Event task type icon.
     *
     * @return [E] icon.
     */
    @Override
    public String getTaskTypeIcon() {
        return "[E]";
    }

    /**
     * {@inheritDoc}
     * Returns Event task type.
     *
     * @return Event enum.
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    /**
     * {@inheritDoc}
     * Returns Event description.
     *
     * @return Description string.
     */
    @Override
    public String getDescription() {
        return this.description + " (from: " + this.startDate.toString() + " to: " + this.endDate.toString() + ")";
    }


    /**
     * {@inheritDoc}
     * Returns Event string for saving into data folder.
     *
     * @return Event save format string.
     */
    @Override
    public String getSaveFormat() {
        return this.description + " | " + this.startDate.toString() + " | " + this.endDate.toString();
    }
}
