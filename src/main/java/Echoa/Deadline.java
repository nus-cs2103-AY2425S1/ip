package Echoa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Deadline is a class that encapsulates the characteristics of a Deadline Task.
 * It extends from the class Task,
 * and contains an additional characteristic of
 * date.
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

    public LocalDateTime getDateAndTime() {
        return this.dateAndTime;
    }

    public LocalDate getDate() {
        return this.getDateAndTime().toLocalDate();
    }

    public LocalTime getTime() {
        return this.getDateAndTime().toLocalTime();
    }

    /**
     * The method reformat the given date into MONTH-DD-YYYY.
     *
     * @param date LocalDate to be reformatted.
     * @return String representation of reformatted date.
     */
    public static String getReformattedDate(LocalDate date) {
        return date.getMonth() + " " +
               date.getDayOfMonth() + " " +
               date.getYear();
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
                " (by: " + getReformattedDate(this.getDate()) + " " +
                this.getTime().toString() + ")";
    }
}
