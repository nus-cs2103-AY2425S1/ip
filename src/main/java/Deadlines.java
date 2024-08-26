
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    protected LocalDate deadline;

    public Deadlines(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", super.getStatusIcons(), super.description,
         this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", this.done ? 1 : 0, this.description, this.deadline);
    }
}
