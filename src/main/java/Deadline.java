import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate doneBy;
    public Deadline(String description, LocalDate doneBy) {
        super(description);
        this.doneBy = doneBy;
    }
    public String printDate() {
        return doneBy.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), printDate());
    }
    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, doneBy);
    }
}
