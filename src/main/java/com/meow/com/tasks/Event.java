package com.meow.com.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.meow.Meowception;
public class Event extends Task {
    protected String start;
    protected String end;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HHmm MMM dd yyyy");

    public Event(String taskName, String from, String to) throws Meowception {
        super(taskName);
        this.start = from;
        this.end = to;
        try {
            startDateTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            endDateTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (java.time.format.DateTimeParseException e) {
            throw new Meowception("300");
        }
    }
    @Override
    public String toString() {
        
        String startFormated = startDateTime.format(FORMATTER);
        String endFormated = endDateTime.format(FORMATTER);
        return "[E]" + super.toString() + " (at: " + startFormated + " to: "+ endFormated + ")";
    }

    @Override
    public String getExtra() {
        DateTimeFormatter formatted = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String startFormated = startDateTime.format(formatted);
        String endFormated = endDateTime.format(formatted);
        return "/from " + startFormated + " /to " + endFormated;
    }

    @Override
    public String getType() {
        return "event";
    }

}
