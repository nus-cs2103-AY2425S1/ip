/**
 * The EventTask class represents a task that occurs during a specific time frame.
 * It extends the Task class and includes a start time and an end time.
 */
public class EventTask extends Task {
    private String startTime;
    private String endTime;

    /**
     * Constructs an EventTask object with the specified name, start time, and end time.
     * @param name the name or description of the event task
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     */
    public EventTask(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the event task, including the task name,
     * start time, and end time, formatted as "(from: startTime to: endTime)".
     * @return a formatted string representation of the event task
     */
    @Override
    public String toString() {
        int from = startTime.indexOf(" ");
        String start = String.format("from: " + startTime.substring(from + 1));
        int to = endTime.indexOf(" ");
        String end = String.format("to: " + endTime.substring(to + 1));
        return String.format("[E]" + super.toString() + " (" + start + " " + end + ")");
    }
}
