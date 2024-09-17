package slothingwaffler;

import java.time.LocalDate;

public class Deadline extends Task implements Comparable<Deadline> {

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

    @Override
    public boolean hasDeadline() {
        return true;
    }

    @Override
    public int compareTo(Deadline deadline) {
        return this.by.compareTo(deadline.by);
    }

}
