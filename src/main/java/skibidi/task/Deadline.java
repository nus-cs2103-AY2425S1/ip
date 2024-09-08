package skibidi.task;

import java.time.LocalDate;

/** Task subclass with end date. */
public class Deadline extends AbstractTask {
    private final LocalDate by;

    /** Construct Deadline instance using command inputs. */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /** Constructor for Deadline instance using deserialized inputs. */
    public Deadline(String marker, String description, LocalDate by) {
        super(marker, description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String serialize() {
        return String.join("|", new String[]{"D", getStatusIcon(), description, by.toString()});
    }
}
