package Azir;

/**
 * Event is a child of the Task class to indicate a task that has a start and end time.
 */
public class Event extends Task {
    private String startDay;
    private String endDay;

    /**
     * Constructs a new Event with task description, start day and end day.
     *
     * @param description Task Description.
     * @param startDay Task start day.
     * @param endDay Task end day.
     */
    public Event(String description, String startDay, String endDay) {
        super(description);
        this.startDay = startDay;
        this.endDay = endDay;
    }

    /**
     * Formats event task into a special string format.
     *
     * @return Special string format.
     */
    public String formatText() {
        return String.format("E | %s | %s | %s | %s", super.getDoneString(), super.getDescription(), this.startDay, this.endDay);
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), this.startDay, this.endDay);
    }
}
