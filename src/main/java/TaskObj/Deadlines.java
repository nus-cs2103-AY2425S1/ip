package TaskObj;
import java.time.LocalDate;

public class Deadlines extends Task {
    private final LocalDate deadline;
    public Deadlines(String desc, LocalDate deadline) {
        super(desc);
        this.deadline = deadline;
    }

    public Deadlines(String desc, LocalDate deadline, Boolean isCompleted) {
        super(desc, isCompleted);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String toTextString() {
        return "D" + super.toTextString() + " | " + deadline;
    }
}
