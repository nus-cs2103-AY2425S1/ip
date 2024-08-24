package Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private final LocalDateTime by;

    public Deadline(String desc, LocalDateTime by) {
        super(desc);
        this.by = by;
    }

    @Override
    String getType() {
        return "[D]";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return super.toString() + " (by: " + by.format(formatter) + ")";
    }

    public LocalDateTime getBy(){
        return this.by;
    }
}
