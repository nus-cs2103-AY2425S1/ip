package skibidi.task;

import java.time.LocalDate;

public class Deadline extends AbstractTask {
    private final LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String marker, String description, LocalDate by) {
        super(marker, description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String serialize() {
        return String.join("|", new String[]{"D", getStatusIcon(), description, by.toString()});
    }
}
