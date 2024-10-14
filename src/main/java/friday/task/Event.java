package friday.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import friday.util.FridayException;

/**
 * Represents an Event with a description, start time, and end time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event with the specified description, start time, and end time.
     *
     * @param description Description of the event.
     * @param from Start time of the event in "yyyy-MM-dd HHmm" format.
     * @param to End time of the event in "yyyy-MM-dd HHmm" format.
     */
    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    @Override
    public void update(String updateDetails) throws FridayException {
        String[] details = updateDetails.split(" /from | /to ");
        if (details.length > 0 && !details[0].trim().isEmpty()) {
            this.description = details[0]; // Update the description
        } else {
            throw new FridayException("Event description cannot be empty.");
        }
        if (details.length > 1) {
            try {
                this.from = LocalDateTime.parse(details[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException e) {
                throw new FridayException("Invalid 'from' date format. Use 'yyyy-MM-dd HHmm'.");
            }
        }
        if (details.length > 2) {
            try {
                this.to = LocalDateTime.parse(details[2].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException e) {
                throw new FridayException("Invalid 'to' date format. Use 'yyyy-MM-dd HHmm'.");
            }
        }
    }

    /**
     * Returns the event details in a format suitable for file storage.
     *
     * @return Formatted string for file storage.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E " + super.toFileFormat() + " | " + from.format(formatter) + " | " + to.format(formatter);
    }

    /**
     * Returns the string representation of the event.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}
