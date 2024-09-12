package echoa;

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


    public LocalDate getDate() {
        return this.dateAndTime.toLocalDate();
    }

    public LocalTime getTime() {
        return this.dateAndTime.toLocalTime();
    }

    /**
     * The method converts the task to its text representation in the file.
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
