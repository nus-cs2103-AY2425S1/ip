package clover.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected LocalDate by;
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String saveFormat() {
        return "D | " + super.saveFormat() + " | " + by.toString();
    }
    @Override
    public String toString() {
        DateTimeFormatter MMMddyyyy = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(MMMddyyyy) + ")";
    }
}
