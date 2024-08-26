package optimus;

// Let ChatGPT check and suggest comments and JavaDocs according to CS2103T style guide
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task with a start date (from) and an end date (to).
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructor to create an Event task with a description and start/end dates.
     * Parses the dates from strings into LocalDate objects.
     *
     * @param description The description of the event.
     * @param from The start date of the event (in yyyy-mm-dd format).
     * @param to The end date of the event (in yyyy-mm-dd format).
     */
    public Event(String description, String from, String to) throws OptimusException {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e){
            throw new OptimusException("Invalid date format for Event. Please use yyyy-mm-dd.");
        }
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return A string in the format: [E][ ] description (from: MMM d yyyy to: MMM d yyyy)
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Formats the Event task data for saving to a file.
     *
     * @return A string in the format: E | 0/1 | description | yyyy-mm-dd | yyyy-mm-dd
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}
