package Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private final LocalDateTime FROM;
    private final LocalDateTime TO;

    public Event(String desc, LocalDateTime from, LocalDateTime to) {
        super(desc);
        this.FROM = from;
        this.TO = to;
    }

    @Override
    public String getType() {
        return "[E]";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return super.toString() + " (from: " + FROM.format(formatter) + " to: " + TO.format(formatter) + ")";
    }

    public LocalDateTime getFrom(){
        return this.FROM;
    }

    public LocalDateTime getTo(){
        return this.TO;
    }
}
