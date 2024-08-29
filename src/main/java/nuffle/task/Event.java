package nuffle.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        // Constructor for Event class
        super(description);
        this.to = to;
        this.from = from;
    }
    @Override
    public String toString() {
        // Add a [D] at the front of task description (parent class)
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }

    public String saveFormat() {
        String temp;
        if (isDone) {
            temp = "1";
        } else {
            temp = "0";
        }
        return "E | " + temp + " | " + description + " | " + to.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd HHmm"))
                + " | " + from.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd HHmm"));
    }

}
