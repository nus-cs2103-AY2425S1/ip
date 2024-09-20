package fridayproject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * Represents an event task.
 * An event task has a description, a start time and an end time.
 * Example: [E][ ] project meeting (at: 2021-09-30 14:00-16:00)
 */
public class Event extends Tasks {
    private String start;
    private String end;

    /*
     * Constructor for an event task.
     * @param description The description of the event task.
     * @param start The start time of the event task.
     * @param end The end time of the event task.
     */
    public Event(String description, String start, String end) {
        super(description);

        // Assertions to ensure that the description, start and end times are not null or empty
        assert description != null && !description.trim().isEmpty() : "Description should not be null";
        assert start != null && !start.trim().isEmpty() : "Start time should not be null";
        assert end != null && !end.trim().isEmpty() : "End time should not be null";
        
        this.start = start;
        this.end = end;
    }

    /*
     * Returns the start time of the event task.
     * @return The start time of the event task.
     */
    public LocalDate getStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(this.start, formatter);
    }

    /*
     * Returns the end time of the event task.
     * @return The end time of the event task.
     */
    public LocalDate getEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(this.end, formatter);
    }

    /*
     * Returns the string representation of the task.
     */
    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    /*
     * Returns the string representation of the task.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + start + " to: " + end + ")";
    }

    /*
     * Returns the string representation of the task to be saved in the file.
     */
    @Override 
    public String toFileString() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description 
            +  " | " + this.start + " | " + this.end;
    }
    
}
