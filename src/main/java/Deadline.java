import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() { //prototype in case of future modification
        return "[D]" + super.toString() + " by: " +
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
