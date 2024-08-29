package task;

import exceptions.AlreadyCompletedException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String title, String start, String end) {
        super(title);
        String[] startArgs = start.split("/at");
        String[] endArgs = end.split("/at");
        this.start = LocalDateTime.of(LocalDate.parse(startArgs[0].trim()), LocalTime.parse(startArgs[1].trim()));
        this.end = LocalDateTime.of(LocalDate.parse(endArgs[0].trim()), LocalTime.parse(endArgs[1].trim()));
    }

    public static Event of(String[] args) throws AlreadyCompletedException {
        Event event = new Event(args[2], args[3], args[4]);
        if (Boolean.parseBoolean(args[1])) {
            event.complete();
        }
        return event;
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String toData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'/at 'HH:mm");
        return super.toData() + "|" + start.format(formatter) + "|" + end.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' at 'HH:mm");
        return String.format("%s (FROM: %s | TO: %s)", super.toString(), start.format(formatter), end.format(formatter));
    }
}
