package sinatra;

/**
 * Represents an Event task which extends the Task class.
 */
public class Event extends Task {

    private String from;
    private String to;

    /**
     * Creates a new Event object from a data string.
     *
     * @param data the data string containing the content, status, from, and to separated by commas
     * @return a new Event object
     */
    public static Event newObjectFromData(String data) {
        String[] parts = data.split(",");
        return new Event(parts[0], Boolean.parseBoolean(parts[1]), parts[2], parts[3]);
    }

    /**
     * Constructs a new Event object with the specified content, status, from, and to.
     *
     * @param content the content of the Event task
     * @param status the status of the Event task
     * @param from the start time of the Event
     * @param to the end time of the Event
     */
    public Event(String content, Boolean status, String from, String to) {
        super(content, status);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the data string for storage.
     *
     * @return the data string for storage
     */
    public String getDataForStorage() {
        return "Sinatra.Event:" + super.getContent() + "," + super.getStatusString() + "," + from + "," + to;
    }

    /**
     * Returns the string representation of the Event object.
     *
     * @return the string representation of the Event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}