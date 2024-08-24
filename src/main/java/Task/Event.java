package Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String desc, LocalDateTime from, LocalDateTime to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    String getType() {
        return "[E]";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    public LocalDateTime getFrom(){
        return this.from;
    }

    public LocalDateTime getTo(){
        return this.to;
    }
}
