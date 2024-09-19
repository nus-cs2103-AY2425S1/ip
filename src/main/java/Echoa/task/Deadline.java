package echoa.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Deadline is a class that encapsulates the characteristics of a Deadline Task.
 * It extends from the class Task,
 * and contains an additional characteristic of
 * dateAndTime.
 */
public class Deadline extends Task {
    private LocalDateTime dateAndTime;

    public Deadline(String description, LocalDateTime dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }

    public Deadline(String description, boolean isDone, LocalDateTime dateAndTime) {
        super(description, isDone);
        this.dateAndTime = dateAndTime;
    }

    private LocalDate getDate() {
        return this.dateAndTime.toLocalDate();
    }

    private LocalTime getTime() {
        return this.dateAndTime.toLocalTime();
    }

    private void editDateAndTime(LocalDate date, LocalTime time) {
        this.dateAndTime = LocalDateTime.of(date, time);
    }

    private void editDate(LocalDate date) {
        this.dateAndTime = LocalDateTime.of(date, this.getTime());
    }

    private void editTime(LocalTime time) {
        this.dateAndTime = LocalDateTime.of(this.getDate(), time);
    }

    /**
     * Updates task according to input details.
     *
     * @param details containing new description, new date and new time.
     */
    public void update(Object[] details) {
        assert details.length == 3;
        assert details[0] == null || details[0] instanceof String;
        assert details[1] == null || details[1] instanceof LocalDate;
        assert details[2] == null || details[2] instanceof LocalTime;

        String newDescription = (String) details[0];
        LocalDate newDate = (LocalDate) details[1];
        LocalTime newTime = (LocalTime) details[2];

        if (newDescription != null) {
            super.editDescription(newDescription);
        }

        if (newDate != null && newTime != null) {
            editDateAndTime(newDate, newTime);
        } else if (newDate != null) {
            editDate(newDate);
        } else if (newTime != null) {
            editTime(newTime);
        }
    }

    /**
     * Returns a String (file text) representation of the Deadline task.
     *
     * @return String representation of text.
     */
    @Override
    public String toText() {
        String completed = isDone ? "1" : "0";
        return "D | " +
                completed + " | " +
                super.description + " | " +
                getDate().toString() + " " + getTime().toString();
    }

    @Override
    public String toString() {
        return "[D]" +
                super.toString() +
                " (by: " + Task.getReformattedDate(this.getDate()) + " " +
                this.getTime().toString() + ")";
    }
}
