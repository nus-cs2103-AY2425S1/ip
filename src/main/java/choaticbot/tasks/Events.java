package choaticbot.tasks;

/**
 * The {@code Events} class represents a task that occurs during a specific time frame, with a start and end time.
 * It extends the {@link Task} class and includes the start and end times as additional fields.
 */
public class Events extends Task {

    /**
     * The start time of the event.
     */
    private String start;

    /**
     * The end time of the event.
     */
    private String end;

    /**
     * Constructs an {@code Events} object with the specified task name, start time, and end time.
     *
     * @param name The name of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Events(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the type of the task, which is "E" for events.
     *
     * @return The string "E" representing the type of the task.
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Returns additional information about the event, specifically the start and end times.
     * The start and end times are separated by a vertical bar {@code |}.
     *
     * @return A string representing the start and end times of the event.
     */
    @Override
    public String getAdditionalInfo() {
        return start + "|" + end;
    }

    /**
     * Converts the task to a string that can be written to a file.
     * This string includes the type, name, completion status, and additional event details (start and end times).
     *
     * @return A string formatted for file storage.
     */
    @Override
    public String toFileString() {
        return getType() + "|" + getName() + "|" + isComplete() + "|" + getAdditionalInfo();
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time of the event.
     */
    public String getFrom() {
        return start;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time of the event.
     */
    public String getTo() {
        return end;
    }

    /**
     * Returns the string representation of the event, including its type, name, start time, and end time.
     *
     * @return A string representing the event in the format {@code [E] task_name (start_time - end_time)}.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + "(" + start + end + ")";
    }
}
