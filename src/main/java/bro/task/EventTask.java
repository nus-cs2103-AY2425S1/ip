package bro.task;

/**
 * Represents an Event task tracked by Bro. An <code>EventTask</code> object
 * is a type of task that has a specific start and end time associated with it.
 */
public class EventTask extends Task {
    private final String startTime;
    private final String endTime;

    /**
     * Constructs a new EventTask with the specified content, start time, and end time.
     * The task is initially marked as not completed.
     *
     * @param content   The content of the Event task.
     * @param startTime The start time of the Event task.
     * @param endTime   The end time of the Event task.
     */
    public EventTask(String content, String startTime, String endTime) {
        super(content);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructs a new EventTask with the specified content, start time, end time,
     * and completion status.
     *
     * @param content     The content of the Event task.
     * @param startTime   The start time of the Event task.
     * @param endTime     The end time of the Event task.
     * @param isCompleted The initial completion status of the task.
     */
    public EventTask(String content, String startTime, String endTime, boolean isCompleted) {
        super(content, isCompleted);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the string representation of the Event task, indicating it is an Event task
     * along with its content, completion status, start time, and end time.
     *
     * @return A string representation of the Event task, prefixed with "[E]"
     *         and showing its start and end times.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
