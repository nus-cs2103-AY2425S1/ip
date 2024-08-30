package nixy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represents a task that has a start and end time.
 */
public class EventTask extends Task {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter SAVE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate startDate;
    private LocalDate endDate;


    /**
     * Creates an event task with the specified description, start time, end time and done status.
     * @param description The description of the task.
     * @param startDate The start time of the task.
     * @param endDate The end time of the task.
     * @param isDone The done status of the task.
     */
    public EventTask(String description, LocalDate startDate, LocalDate endDate, boolean isDone) {
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Creates an event task with the specified description, start time, end time.
     * @param description The description of the task.
     * @param startDate The start time of the task.
     * @param endDate The end time of the task.
     */
    public EventTask(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the start time of the task.
     * @return The start time of the task.
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * Returns the end time of the task.
     * @return The end time of the task.
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * Returns the type label of the task.
     *
     * @return The type label of the task.
     */
    private String getTypeLabel() {
        return "E";
    }

    /**
     * Returns the timing label of the task.
     *
     * @return The timing label of the task.
     */
    private String getTimingLabel() {
        return String.format("(from: %s to: %s)", this.startDate.format(DATE_TIME_FORMATTER),
            this.endDate.format(DATE_TIME_FORMATTER));
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s %s", this.getTypeLabel(), super.toString(), this.getTimingLabel());
    }

    /**
     * Returns the string representation of the task in data format (for saving to file)
     *
     * @return The data string.
     */
    @Override
    public String toDataString() {
        return String.format("%s|%s|%s|%s", this.getTypeLabel(), super.toDataString(),
            this.startDate.format(SAVE_DATE_TIME_FORMATTER), this.endDate.format(SAVE_DATE_TIME_FORMATTER));
    }
}
