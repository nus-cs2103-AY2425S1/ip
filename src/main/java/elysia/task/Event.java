package elysia.task;

/**
 * Represents an Event task with a description, starting time
 * and end time.
 */
public class Event extends Task {
    private String startTime;
    private String endTime;

    /**
     * Constructs an event task.
     *
     * @param description The description of the task.
     * @param startTime   The start time of the event.
     * @param endTime     the end time of the event.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }

    @Override
    public String saveToTxt() {
        int i = this.isDone ? 1 : 0;
        return "E | " + i + " | " + this.description + " | "
                + this.startTime + " | " + this.endTime;
    }
}
