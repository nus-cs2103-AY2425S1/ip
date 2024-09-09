package chatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the concept of an Event task that the user adds into his todolist
 * The Event class extends the abstract class of Task
 */
public class Event extends Task {
    /** Date time that the event starts */
    private LocalDateTime from;
    /** Date time that the event ends */
    private LocalDateTime to;

    /**
     * Overloaded constructor for the Event class, taking in 4 arguments
     *
     * @param name Name of Event
     * @param from Date and time that the event starts
     * @param to Date and time that the event ends
     * @param isDone Boolean value representing whether the task has been marked as completed
     */
    public Event(String name, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(name, isDone);

        assert from != null : "from should not be null";
        assert to != null : "to should not be null";

        this.from = from;
        this.to = to;
    }

    /**
     * Overloaded constructor for the Event class, taking in 3 arguments
     *
     * @param name Name of Event
     * @param from Date and time that the event starts
     * @param to Date and time that the event ends
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        this(name, from, to, false);
    }

    /**
     * Overridden toString method, displaying the type of the task along with other details
     *
     * @return String representation of the Event task
     */
    @Override
    public String toString() {
        String str = String.format("[E]%s (from: %s to: %s)", super.toString(),
                Task.formatDate(this.from), Task.formatDate(this.to));
        return str;
    }

    /**
     * Encodes the Event task into a string, to be written to a text file
     *
     * @return String encoding of the Event task
     */
    @Override
    public String encode() {
        return "E|" + super.encode() + "|" + this.from + "|" + this.to;
    }
}
