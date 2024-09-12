package echoa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Event is a class that encapsulates the characteristics of an Event Task.
 * It extends from the class Task,
 * and contains additional characteristics of
 * startTime and endTime
 */

public class Event extends Task {

    private LocalDateTime startDateAndTime;
    private LocalDateTime endDateAndTime;

    public Event(String description, LocalDateTime startDateAndTime, LocalDateTime endDateAndTime) {
        super(description);
        this.startDateAndTime = startDateAndTime;
        this.endDateAndTime = endDateAndTime;
    }

    public Event(String description, boolean isDone, LocalDateTime startDateAndTime, LocalDateTime endDateAndTime) {
        super(description, isDone);
        this.startDateAndTime = startDateAndTime;
        this.endDateAndTime = endDateAndTime;
    }

    public LocalDate getStartDate() {
        return this.startDateAndTime.toLocalDate();
    }

    public LocalTime getStartTime() {
        return this.startDateAndTime.toLocalTime();
    }

    public LocalDate getEndDate() {
        return this.endDateAndTime.toLocalDate();
    }

    public LocalTime getEndTime() {
        return this.endDateAndTime.toLocalTime();
    }

    /**
     * The method converts the task to its text representation in the file.
     *
     * @return String representation of text.
     */
    @Override
    public String toText() {
        String completed = isDone ? "1" : "0";
        return "E | " +
                completed + " | " +
                super.description + " | " +
                this.getStartDate().toString() + " " + this.getStartTime().toString() + " | " +
                this.getEndDate().toString() + " " + this.getEndTime().toString();
    }

    public String toString() {
        return "[E]" +
                super.toString() +
                " (from: " + Task.getReformattedDate(this.getStartDate()) + " " + this.getStartTime().toString() +
                " to: " + Task.getReformattedDate(this.getEndDate()) + " " + this.getEndTime().toString() + ")";
    }
}
