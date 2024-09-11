package count.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import count.exception.InvalidTimelineException;

/**
 * The Event class extends the Task class
 * changing the String representation to include the [E] symbol
 * includes a LocalDate to complete the task by
 * and a LocalDate which the task starts from
 */
public class Event extends Task {
    protected LocalDate startTime;
    protected LocalDate endTime;
    protected DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    protected DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("LLLL dd yyyy");

    /**
     * Constructor for Event
     * @param description String description of the task
     * @param endTime String description of endTime in the format of inputFormat
     * @param startTime String description of startTime in the format of inputFormat
     */
    public Event(String description, String endTime, String startTime) throws InvalidTimelineException {
        super(description);
        this.startTime = LocalDate.parse(startTime, inputFormat);
        this.endTime = LocalDate.parse(endTime, inputFormat);

        if (this.endTime.isBefore(this.startTime) || this.endTime.isEqual(this.startTime)) {
            throw new InvalidTimelineException("The end time cannot be before or the same as the start time!");
        }
    }

    /**
     * Constructor for Event
     * @param description String description of the task
     * @param endTime LocalDate description of endTime
     * @param startTime LocalDate description of startTime
     * @param completion Boolean whether the task is completed
     */
    public Event(String description, LocalDate endTime, LocalDate startTime, boolean completion)
            throws InvalidTimelineException {
        super(description, completion);
        this.startTime = startTime;
        this.endTime = endTime;

        if (this.endTime.isBefore(this.startTime) || this.endTime.isEqual(this.startTime)) {
            throw new InvalidTimelineException("The end time cannot be before or the same as the start time!");
        }
    }
    public LocalDate getEndTime() {
        return this.endTime;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime.format(outputFormat)
                + " to: " + this.endTime.format(outputFormat) + ")";
    }
}
