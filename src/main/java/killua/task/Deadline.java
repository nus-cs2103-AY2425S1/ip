package killua.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime dateTime;
    protected LocalDate date;


    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public String format() {
        if (dateTime != null) {
            return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        } else if (date != null) {
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return "";
    }

    public LocalDate getDate() {
        if (date != null) {
            return date;
        }
        return dateTime.toLocalDate();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toSave() {
        return "D" + super.toSave() + " | " + format();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + format() + ")";
    }
}
