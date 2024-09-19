package echoa.task;

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

    private LocalDate getStartDate() {
        return this.startDateAndTime.toLocalDate();
    }

    private LocalTime getStartTime() {
        return this.startDateAndTime.toLocalTime();
    }

    private LocalDate getEndDate() {
        return this.endDateAndTime.toLocalDate();
    }

    private LocalTime getEndTime() {
        return this.endDateAndTime.toLocalTime();
    }

    private void editStartDateAndTime(LocalDate date, LocalTime time) {
        this.startDateAndTime = LocalDateTime.of(date, time);
    }

    private void editStartDate(LocalDate date) {
        this.startDateAndTime = LocalDateTime.of(date, this.getStartTime());
    }

    private void editStartTime(LocalTime time) {
        this.startDateAndTime = LocalDateTime.of(this.getStartDate(), time);
    }

    private void editEndDateAndTime(LocalDate date, LocalTime time) {
        this.endDateAndTime = LocalDateTime.of(date, time);
    }

    private void editEndDate(LocalDate date) {
        this.endDateAndTime = LocalDateTime.of(date, this.getEndTime());
    }

    private void editEndTime(LocalTime time) {
        this.endDateAndTime = LocalDateTime.of(this.getEndDate(), time);
    }

    /**
     * Updates task according to input details.
     *
     * @param details containing new description, new start date, new start time, new end date, new end time.
     */
    public void update(Object[] details) {
        assert details.length == 5;
        assert details[0] == null || details[0] instanceof String;
        assert details[1] == null || details[1] instanceof LocalDate;
        assert details[2] == null || details[2] instanceof LocalTime;
        assert details[3] == null || details[3] instanceof LocalDate;
        assert details[4] == null || details[4] instanceof LocalTime;

        String newDescription = (String) details[0];
        LocalDate newStartDate = (LocalDate) details[1];
        LocalTime newStartTime = (LocalTime) details[2];
        LocalDate newEndDate = (LocalDate) details[3];
        LocalTime newEndTime = (LocalTime) details[4];

        if (newDescription != null) {
            super.editDescription(newDescription);
        }

        if (newStartDate != null && newStartTime != null) {
            editStartDateAndTime(newStartDate, newStartTime);
        } else if (newStartDate != null) {
            editStartDate(newStartDate);
        } else if (newStartTime != null) {
            editStartTime(newStartTime);
        }

        if (newEndDate != null && newEndTime != null) {
            editEndDateAndTime(newStartDate, newStartTime);
        } else if (newEndDate != null)  {
            editEndDate(newEndDate);
        } else if (newEndTime != null) {
            editEndTime(newEndTime);
        }
    }

    /**
     * Returns a String (file text) representation in the Event task.
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
