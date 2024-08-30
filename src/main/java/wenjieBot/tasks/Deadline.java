package wenjieBot.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;

    protected LocalDateTime dateTime ;



    public Deadline(String description, String by) {
        super(description);
        this.by = by.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.dateTime = LocalDateTime.parse(by.trim(), formatter);
    }

    @Override
    public String toPrettierString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "D" + super.toPrettierString() + "/by: " + dateTime.format(formatter);
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm");

        return "[D]" + super.toString() + "(by: " + dateTime.format(formatter) + ")";
    }
}
