package hinlok.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String name, LocalDate by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    public String getBy() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }


    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}
