package echo.task;

/**
 * The Event class represents a task with a start and end time.
 */
public class Event extends Task {
    private String start;
    private String end;
    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description the description of the event task
     * @param start the start date/time of the event
     * @param end the end date/time of the event
     */
    public Event(String description, String start, String end) {
        super(description, TaskType.EVENT);
        assert !start.isEmpty(): "Start should not be null";
        assert !end.isEmpty(): "End should not be null";

        this.start = start;
        this.end = end;
    }
    /**
     * Returns a formatted string representing the event task,
     * including its description, start time, and end time.
     *
     * @return a string representing the event task
     */
    @Override
    public String getTaskString() {
        return super.getTaskString() +
                String.format(
                        " (from: %s to: %s)",
                        this.start,
                        this.end
                );
    }
    /**
     * Returns a string representing the event task's save format,
     * intended for saving to a file.
     *
     * @return a string representing the event task's save format
     */
    @Override
    public String getData() {
        return super.getData() + " | " + this.start + "->" + this.end;
    }
}
