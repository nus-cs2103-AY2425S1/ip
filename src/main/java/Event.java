
/**
 * Represents an event that starts at a specific date/time and ends at a specific date/time.
 * An Event object corresponds to an event in the Chobo chatbot.
 */
public class Event extends Task{
    private String from;
    private String to;
    /**
     * Creates a new Event task.
     *
     * @param name The name of the task.
     * @param done The status of the task (true if done, false otherwise).
     * @param from The start time/date of the event.
     * @param to   The end time/date of the event.
     */
    public Event (String name, boolean done, String from, String to) {
        super(name, done);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task, including its type,
     * status, name, start time, and end time.
     *
     * @return A string representing the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + "to: " + to + ")";
    }
}
