package FRIDAY;

/**
 * Represents a task with a start and end time, description and a completion status.
 * <p>
 * This class extends the Task class and adds functionality to manage start and end times
 * it allows for the storage and display of sait start and end times
 * </p>
 */
public class Event extends Task {
    private String start, end;

    /**
     * Constructor for task of type Event
     * @param description task description
     * @param start event start time
     * @param end event end time
     * @param type reflects completion status of task
     */
    public Event(String description, String start, String end, int type) {
        super(description, type);
        this.start = start;
        this.end = end;
    }

    /**
     * outputs all the information pertaining the task in a specified format for storage purposes
     * @return a string containing all important details of the task
     */
    @Override
    public String storageDisplay() {
        return "E" + super.storageDisplay() + " | from " + start + " to " + end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +  "(" + start + " to " + end + ")";
    }
}
