package slothingwaffler;

import java.time.LocalDate;

public class Deadline extends Task {

    private final LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by, Task.DTF3);
    }

    @Override
    public String toFileFormat() {
        return "D" + super.toFileFormat() + " | " + this.by.format(Task.DTF3);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(Task.DTF4) + ")";
    }

}
