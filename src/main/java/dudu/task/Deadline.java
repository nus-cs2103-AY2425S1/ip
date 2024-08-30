package dudu.task;

import dudu.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public String formatString() {
        String by = this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return String.format("D | %s | %s", super.formatString(), by);
    }

    @Override
    public String toString() {
        String by = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[D] %s (by: %s)", super.toString(), by);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || !(o instanceof Deadline)) {
            return false;
        }
        Deadline other = (Deadline) o;
        return this.by.equals(other.by) && super.equals(other);
    }
}
