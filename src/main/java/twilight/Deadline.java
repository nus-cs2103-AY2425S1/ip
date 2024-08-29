package twilight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected LocalDate deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    public Deadline(boolean status, String description, String deadline) {
        super(description, status);
        this.deadline = LocalDate.parse(deadline);
    }

    public String toString() {
        return "[D]" + super.toString() + " by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String toStorage() {
        return "D," + super.toStorage() + "," + deadline;
    }
}
