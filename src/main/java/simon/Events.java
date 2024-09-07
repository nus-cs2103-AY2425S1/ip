package simon;
/**
 * Represents an event task in the Simon application.
 * Inherits from the Task class and adds properties for the event's start and end times.
 */
public class Events extends Task {

    private final String from;
    private final String to;
    /**
     * Constructs an Events task with the specified name,
     * task number, start time, and end time.
     *
     * @param name the name of the event task
     * @param number the number of the task
     * @param from the start time of the event as a string
     * @param to the end time of the event as a string
     */
    public Events(String name, int number, String from, String to) {
        super(name, number);
        this.from = from;
        this.to = to;
    }
    /**
     * Returns a string representation of the Events task for display purposes.
     *
     * @return a representation of the Events task in the format "[E][task details] (from: start time to: end time)"
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: " + this.from + " to: " + this.to + ")";
    }
    /**
     * Returns a string representation of the Events task in the format used for saving to storage.
     *
     * @return a string representation of the Events task in the format "E | completed | name | from | to"
     */
    @Override
    public String toSaveFormat() {
        return "E | "
                + (super.getCompleted() ? 1 : 0) + " | " + super.getName1()
                + " | " + from + " | " + to;
    }
}
