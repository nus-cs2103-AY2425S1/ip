package Nah.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    private LocalDateTime start, end;

    public Events(String content, LocalDateTime start, LocalDateTime end) {
        super(content);
        this.start = start;
        this.end = end;
    }

    @Override
    public LocalDateTime endTime() {
        return this.end;
    }

    /**
     * Return a brief description of task
     * @return
     */
    @Override
    public String brief() {
        return "D | " + super.getStatus() + " | " + super.getTask() + " | "
                + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"))
                + " | " + this.end.format(DateTimeFormatter.ofPattern("MM d yyy, h:mm a"));

    }
    @Override
    public String toString() {
        return "[E] "
                + super.toString()
                + " (from: "
                + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"))
                + " to: "
                + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"))
                + ")";
    }
}
