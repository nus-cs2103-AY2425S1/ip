import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate end;

    public Deadline(boolean status, String description, LocalDate end) {
        super(status, description);
        this.end = end;
    }

    @Override
    public String toFileString() {
        return "D | " + this.getStatus() + " | " + this.getDescription() + " | " + this.end;
    }

    @Override
    public String toString() {
        return "[D][" + (this.getStatus() ? "X" : " ") + "] " +
                this.getDescription() + " (by: " + this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

}
