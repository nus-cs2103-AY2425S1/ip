package sinatra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task which extends the Task class.
 */
public class Deadline extends Task {

    private String dateTimeString;
    private LocalDateTime dateTime;

    /**
     * Creates a new Deadline object from a data string.
     *
     * @param data the data string containing the content, status, and dateTimeString separated by commas
     * @return a new Deadline object
     */
    public static Deadline newObjectFromData(String data) {
        String[] parts = data.split(",");
        return new Deadline(parts[0], Boolean.parseBoolean(parts[1]), parts[2]);
    }

    /**
     * Constructs a new Deadline object with the specified content, status, and dateTimeString.
     *
     * @param content the content of the Deadline task
     * @param status the status of the Deadline task
     * @param dateTimeString the deadline date and time as a string
     */
    public Deadline(String content, Boolean status, String dateTimeString) {
        super(content, status);
        this.dateTimeString = dateTimeString;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.dateTime = LocalDateTime.parse(dateTimeString, formatter);
    }

    /**
     * Returns the data string for storage.
     *
     * @return the data string for storage
     */
    public String getDataForStorage() {
        return "Sinatra.Deadline:" + super.getContent() + "," + super.getStatusString() + "," + dateTimeString;
    }

    /**
     * Returns the deadline date and time as a string.
     *
     * @return the deadline date and time as a string
     */
    public String getBy() {
        return dateTimeString;
    }

    /**
     * Returns the string representation of the Deadline object.
     *
     * @return the string representation of the Deadline object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTimeString + ")";
    }
}