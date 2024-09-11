package Mentos.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description,String from, String to) {
        super(description);
        this.from =LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to=LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public void updateFrom(String from) {
        this.from =LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public void updateTo(String to) {
        this.to=LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString(){
        return String.format("[E] %s (from: %s to: %s)",super.toString(),this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")),this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")));
    }

}
