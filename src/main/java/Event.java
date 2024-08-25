import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Represents an Event task which is a specific type of Task.
 * An Event task has a start time and an end time.
 */
public class Event extends Task {
    /**
     * The start time of the event.
     */
    protected String from;

    /**
     * The end time of the event.
     */
    protected String to;

    /**
     * Constructs a new Event task with the specified description, start time, and end time.
     *
     * @param description The description of the Event task.
     * @param from The start time of the Event task.
     * @param to The end time of the Event task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task, including its type indicator,
     * start time, and end time.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }

    /**
     * Parses a StringTokenizer to create a new Event task.
     *
     * @param tokenizedInput The StringTokenizer containing the description, start time, and end time of the Event task.
     * @return A new Event task with the parsed description, start time, and end time.
     * @throws NoSuchElementException If the input does not contain the expected tokens.
     */
    public static Event parseEvent(StringTokenizer tokenizedInput) throws NoSuchElementException {
        StringBuilder description = new StringBuilder();
        String token = tokenizedInput.nextToken();
        while (!token.equals("/from")) {
            description.append(token).append(" ");
            token = tokenizedInput.nextToken();
        }
        StringBuilder from = new StringBuilder();
        token = tokenizedInput.nextToken();
        while (!token.equals("/to")) {
            from.append(token).append(" ");
            token = tokenizedInput.nextToken();
        }
        StringBuilder to = new StringBuilder();
        token = tokenizedInput.nextToken();
        to.append(token).append(" ");
        while (tokenizedInput.hasMoreTokens()) {
            token = tokenizedInput.nextToken();
            to.append(token).append(" ");
        }
        return new Event(description.toString().trim(), from.toString().trim(), to.toString().trim());
    }
}