package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private final LocalDateTime BY;

    public Deadline(String desc, LocalDateTime by) {
        super(desc);
        this.BY = by;
    }

    @Override
    public String getType() {
        return "[D]";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return super.toString() + " (by: " + this.BY.format(formatter) + ")";
    }

    public LocalDateTime getBy(){
        return this.BY;
    }
}
