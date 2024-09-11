package Mentos.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {

    protected LocalDateTime by;
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public void updateBy(String by) {
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString(){
        return String.format("[D] %s (by: %s)",super.toString(),this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")));
    }
}
