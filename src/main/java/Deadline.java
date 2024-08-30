import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;
    public Deadline(String task, LocalDate by, boolean isCompleted) {
        super(task, isCompleted);
        this.by = by;
    }
    public String toFileString() {
        return "D|" + super.toFileString() + "|" + by;
    }
    public String toString() {

        return "[D]" + super.toString() + "(" + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
