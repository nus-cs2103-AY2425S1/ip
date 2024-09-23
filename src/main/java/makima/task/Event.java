package makima.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Task with a specified start and end date
 */
public class Event extends Task {

    public static final int SAVE_PARAMETERS = 6;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    Event() {
        super();
    }

    /**
     * Instantiates a new event
     *
     * @param name
     * @param startTime
     * @param endTime
     */
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Instantiates a new event from a saved file
     *
     * @param name
     * @param startTime
     * @param endTime
     * @param isDone
     */
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime, boolean isDone,
                 PriorityLevel priorityLevel) {
        super(name, isDone, priorityLevel);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format("(from: %s, to: %s)", startTime, endTime);
    }

    @Override
    public String toFileString() {
        return String.format("E\n%s%s\n%s\n", super.toFileString(), startTime, endTime);
    }

    @Override
    boolean load(ArrayList<String> data) {
        try {
            startTime = LocalDateTime.parse(data.get(3));
            endTime = LocalDateTime.parse(data.get(4));
        } catch (DateTimeParseException e) {
            System.out.println("The file is corrupted! Delete it before restarting the program!");
            return false;
        }
        return super.load(data);
    }

    /**
     * Factory method to load Event from data. Returns null if data is corrupted.
     *
     * @param data List of strings representing the Event.
     * @return Event
     */
    public static Event loadFromData(ArrayList<String> data) {
        Event event = new Event();
        if (event.load(data)) {
            return event;
        }
        return null;
    }
}
