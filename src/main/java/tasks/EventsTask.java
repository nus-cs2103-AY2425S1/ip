package tasks;

import dateAndTime.ReginaDateAndTime;
import exception.ReginaException;

/**
 * Represents an event task with a description, start time, and end time.
 * This class extends the Task.Task class and provides specific functionality
 * for event tasks.
 */
public class EventsTask extends Task {
    protected ReginaDateAndTime startTime;
    protected ReginaDateAndTime endTime;

    /**
     * Constructs an tasks.EventsTask with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param startTime The starting time of the event.
     * @param endTime The ending time of the event.
     */
    public EventsTask(String description, String startTime, String endTime) throws ReginaException {
        super(description);
        this.startTime = new ReginaDateAndTime(startTime);
        this.endTime = new ReginaDateAndTime(endTime);
    }

    /**
     * Checks if the event is occurring at the specified date and time.
     *
     * This method determines if the provided date and time falls within the
     * event's start and end time. The event is considered to be occurring
     * if the specified date and time is at or after the start time and
     * at or before the end time.
     *
     * @param dateAndTime The date and time to check against the event's start and end times.
     * @return true if the event is occurring at the specified date and time,
     *         false otherwise.
     */
    @Override
    public boolean isOccurringOn(ReginaDateAndTime dateAndTime) {
        return (this.startTime.isBefore(dateAndTime) || this.startTime.isEqual(dateAndTime))
                && (this.endTime.isAfter(dateAndTime) || this.endTime.isEqual(dateAndTime));
    }


    /**
     * Returns a string representation of the event task in a format suitable for saving.
     * The format includes the task type, completion status, description, start time, and end time.
     *
     * @return A formatted string representing the event task for saving purposes.
     */
    @Override
    public String toSavedFormatting() {
        return String.format("E | %s | %s | %s | %s",
                this.isDone ? "X" : " ",
                this.description,
                this.startTime.toSavedFormatting(),
                this.endTime.toSavedFormatting());
    }

    /**
     * Returns a string representation of the event task, including its
     * status (done or not), description, start time, and end time.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                this.startTime,
                this.endTime);
    }
}
