package duke;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        int day = this.by.getDayOfMonth();
        Month month = this.by.getMonth();
        int year = this.by.getYear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String time12HourFormat = this.by.format(formatter);
        String date = day + " " + month + " " + year + " " + time12HourFormat;
        return this.getTypeIcon() + super.toString() + " (by: " + date + ")";
    }

    public LocalDateTime getBy() {
        return this.by;
    }
}
