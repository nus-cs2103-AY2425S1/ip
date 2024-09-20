package task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public LocalDateTime from;
    public LocalDateTime to;

    public Event(String des, String from, String to) {
        super(des);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeException e) {
            System.out.println("Hey man, you have to use the yyyy-mm-dd HHmm format instead");
            this.from = null;
            this.to = null;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter desiredFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        if (from == null || to == null) {
            return "[E]" + super.toString() + " (I did not get the date, use yyyy-mm-dd HHmm.)";
        }
        return "[E]" + super.toString() + " (from: " + (from == null ? "I did not get the date, use yyyy-mm-dd HHmm."
                : from.format(desiredFormat)) + " to: " + (to == null ? "I did not get the date, use yyyy-mm-dd HHmm."
                : to.format(desiredFormat)) + ")";
    }
}