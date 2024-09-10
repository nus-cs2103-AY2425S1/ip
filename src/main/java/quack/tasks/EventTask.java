package quack.tasks;

import java.time.LocalDateTime;

/**
 * This class defines and provides functionality for an event task.
 * <p>
 * An event task has a start date and an end date.
 */
public class EventTask extends Task {

    /** The start date for the event */
    private LocalDateTime startDate;
    /** The end date for the event */
    private LocalDateTime endDate;

    /**
     * Creates an event task object based on its description, start date and end date.
     * @param description The description of the event.
     * @param startDate The starting period for the event.
     * @param endDate The ending period for the event.
     */
    public EventTask(String description, LocalDateTime startDate, LocalDateTime endDate) {

        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Retrieves start date of the event task.
     * @return A LocalDateTime object which indicates the start date of the task.
     */
    protected LocalDateTime getStartDate() {

        return this.startDate;
    }

    /**
     * Retrieves end date of the event task.
     * @return A LocalDateTime object which indicates the end date of the task.
     */
    protected LocalDateTime getEndDate() {

        return this.endDate;
    }

    @Override
    public String toCsvFormat() {

        return "EVENT," + super.toCsvFormat() + "," + this.startDate.format(Task.DATE_FORMAT)
            + "," + this.endDate.format(Task.DATE_FORMAT) + "," + this.isChecked + "," + this.getTag();
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + " (From: " + this.startDate.format(Task.DATE_FORMAT)
            + " To: " + this.endDate.format(Task.DATE_FORMAT) + ")";
    }
}

