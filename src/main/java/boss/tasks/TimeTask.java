package boss.tasks;

import boss.tasks.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Represents an event associated with times!
 */
public class TimeTask extends Task {
    protected LocalDate date;
    protected LocalDateTime time;

    /**
     * Creates a TimeTask object
     * @param description description of task
     * @param isDone status of task
     */
    public TimeTask(String description, boolean isDone) {
        super(description, isDone);
    }


    public boolean checkDateDifference() {
        if (this.date == null) {
            return false;
        }
        long daysBetween = DAYS.between(this.date, LocalDate.now());
        if (daysBetween <= 7) {
            return true;
        }
        return false;
    }

    public boolean checkTimeDifference() {
        if (this.time == null) {
            return false;
        }
        long hoursBetween = this.time.until(LocalDateTime.now(), ChronoUnit.HOURS);
        if (hoursBetween <= 168) {
            return true;
        }
        return false;

    }


    /**
     * Checks if the task has a date
     * @param s string that potentially contains a date
     * @return boolean value
     */
    public boolean hasDate(String s) {
        if (s.matches("\\d{4}-\\d{2}-\\d{2}") || s.matches("\\d{4}-\\d{2}-\\d{2} ")) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the task has a Date + Time
     * @param s string that potentially contains a date
     * @return boolean value
     */

    public boolean hasDateTime(String s) {
        if (s.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}") || s.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2} ")) {
            return true;
        }
        return false;
    }

    /**
     * Formats the string and returns a LocalDateTime object.
     * It can be assumed that the string's format is valid.
     * @param s string containing datetime string
     * @return LocalDateTime object
     */
    public LocalDateTime formatDateTime(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse(s, formatter);
        return date;
    }

    /**
     * Formats the string and returns a LocalTime object.
     * It can be assumed that the string's format is valid.
     * @param s string containing date string
     * @return LocalDate object
     */

    public LocalDate formatDate(String s) {
        s = s.trim();
        LocalDate date = LocalDate.parse(s);
        return date;
    }


}
