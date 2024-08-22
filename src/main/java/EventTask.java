/**
 * Represents a task that has a start and end time.
 */
public class EventTask extends Task {
    private String startTime;
    private String endTime;

    /**
     * Creates an event task with the specified description, start time and end time.
     *
     * @param description The description of the task.
     * @param startTime The start time of the task.
     * @param endTime The end time of the task.
     */
    public EventTask(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
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
        return String.format("(from: %s to: %s)", this.startTime, this.endTime);
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
}
