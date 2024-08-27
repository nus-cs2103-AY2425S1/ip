package Nah.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    private LocalDateTime time;

    public Deadlines(String content, LocalDateTime by) {
        super(content);
        this.time = by;
    }

    @Override
    public LocalDateTime endTime() {
        return this.time;
    }

    /**
     * Return a brief description of task
     * @return
     */
    @Override
    public String brief() {
        return "D | " + super.getStatus() + " | " + super.getTask() + " | "
               + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
    }
    @Override
    public String toString() {
        return "[D] "
                + super.toString()
                + " (by: "
                + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"))
                + ")";
    }
}
