import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final String due;
    private LocalDate dueDate;

    public Deadline(String name, String due) {
        super(name);
        this.due = due;
        try {
            dueDate = LocalDate.parse(due);
        } catch (IllegalArgumentException e) {
            dueDate = null;
        }
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + (dueDate == null ? this.due :this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                + ")";
    }

    @Override
    public String savedString() {
        return "D | " + super.savedString() + " | " + this.due;
    }
}
