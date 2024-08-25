import java.io.Serializable;

public class Event extends Task implements Serializable {
    private String startTime;
    private String endTime;

    /**
     * Creates an event task object.
     * An event will start at a specific date/time
     * and ends at a specific date/time.
     *
     * @param task task information of the event.
     * @param startTime start time of the event.
     * @param endTime end time of the event.
     */
    public Event(String task, String startTime, String endTime) {
        this.task = task;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the information of the task.
     *
     * @return information the task in "[E][-] Task (from: 'start time' to: 'end time')" format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
