import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate due;
    public Deadline(String task, LocalDate due) {
        super(task);
        this.due = due;
    }
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)",
                super.toString(),
                this.due.format(DateTimeFormatter.ofPattern("MMM dd yy")));
    }

    public String toStorageString() {
        return String.format("D | %s | %s", this.taskString, this.due);
    }
}
