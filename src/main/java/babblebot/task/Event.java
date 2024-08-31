package babblebot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate from;
    private LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.from = LocalDate.parse(from, formatter);
        this.to = LocalDate.parse(to, formatter);
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (getStatusIcon().equals("X")) {
            return "E" + " | " + "1" + " | " + this.description + " | " +
                    this.from.format(formatter) + " to " + this.to.format(formatter);
        } else {
            return "E" + " | " + "0" + " | " + this.description + " | " +
                    this.from.format(formatter) + " to " + this.to.format(formatter);
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" +  super.toString() + " (from: " + this.from.format(formatter) +
                " to: " + this.to.format(formatter) + ")";
    }
}
