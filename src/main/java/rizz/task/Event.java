package rizz.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;


public class Event extends Task {
    private final LocalDateTime from;
    private final LocalTime to;

    DateTimeFormatter writeDateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    DateTimeFormatter readDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    DateTimeFormatter writeTimeFormatter = DateTimeFormatter.ofPattern("HHmm");
    DateTimeFormatter readTimeFormatter = DateTimeFormatter.ofPattern("HHmm");


    public Event(String text, LocalDateTime from, LocalTime to, boolean isDone) {
        super(text,isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String export() {
        return "E | " + (isDone ? "1" : "0") + " | " + text + " | " + from.format(readDateTimeFormatter) + " " +
                to.format(readTimeFormatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + from.format(writeDateTimeFormatter) + "-" +
                to.format(writeTimeFormatter) + ")";
    }
}