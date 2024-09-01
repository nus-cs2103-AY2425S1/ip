package carly.tasks;

import java.text.MessageFormat;

/**
 * Represents a task of type Event.
 * An Event task includes a description, start time, and end time.
 */
public class Event extends Task {
    /** The task type identifier for Event tasks. */
    private static final String TASK_TYPE = "E";

    /** The end time of the event. */
    private final String startTime;

    /** The end time of the event. */
    private final String endTime;

    /** Constructs a new Event task with the specified description, start time, and end time. */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.isDone = false;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime(){
        return this.startTime;
    }

    public String getEndTime(){
        return this.endTime;
    }

    /**
     * Returns a string representation of the Event task, including
     * its type identifier, status, start time, and end time.
     *
     * @return A string formatted as "[E][status] description (from: start to: end)".
     * @inheritDoc
     */
    @Override
    public String toString() {
        return MessageFormat.format("[{0}]{1} (from: {2} to: {3})",
                TASK_TYPE,
                super.toString(),
                this.getStartTime(),
                this.getEndTime());
    }
}
