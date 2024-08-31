package cloudy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task in the Cloudy program.
 * An Event is a task that has a start date and end date.
 */
public class Event extends Task {
    LocalDate startTime;
    LocalDate endTime;

    /**
     * Initializes an event task with the specified description, start time and end time.
     * @param description The description of the event task.
     * @param startTime The start time of the event task.
     * @param endTime The end time of the event task.
     * @param isMarked The completion status of the event task.
     */
    public Event(String description, LocalDate startTime, LocalDate endTime, boolean isMarked) {
        super(description, isMarked);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String printTaskOnList() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedStartTime = startTime.format(formatter);
        String formattedEndTime = endTime.format(formatter);

        if (isMarked) {
            return "[E][X] " + this.description + " (from: " + formattedStartTime + " to: " + formattedEndTime + ")";
        } else {
            return "[E][ ] " + this.description + " (from: " + formattedStartTime + " to: " + formattedEndTime + ")";
        }
    }

    @Override
    public String toFileFormat() {
        return "E | " + (this.isMarked ? "1" : "0") + " | " + this.description + " | " + this.startTime + " | " + this.endTime;
    }

}
