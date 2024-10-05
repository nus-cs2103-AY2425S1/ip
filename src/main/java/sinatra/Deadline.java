package sinatra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task which extends the Task class.
 */
public class Deadline extends Task {

    private String dateTimeString;
    private LocalDateTime dateTime;

    /**
     * Constructs a new Deadline object with the specified content, status, and dateTimeString.
     *
     * @param content        the content of the Deadline task
     * @param status         the status of the Deadline task
     * @param dateTimeString the deadline time of the task in the format d/M/yyyy HHmm
     */
    public Deadline(String content, Boolean status, String dateTimeString) {
        super(content, status);
        this.dateTimeString = dateTimeString;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            this.dateTime = LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date-time format. Please use d/M/yyyy HHmm.");
        }
    }

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
     * Returns the data string for storage.
     *
     * @return the data string for storage
     */
    public String getDataForStorage() {
        return "Sinatra.Deadline:" + super.getContent() + "," + super.isMarkedString() + "," + dateTimeString;
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
