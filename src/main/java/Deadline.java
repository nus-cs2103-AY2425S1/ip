import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate deadline;
    
    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    public Deadline(String name, LocalDate deadline, boolean isDone) {
        super(name, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toFileString() {
        String done = this.isDone() ? "1" : "0";
        String stringDeadline = this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return "D," + done + "," + this.getName() + "," + stringDeadline;
    }

    @Override
    public String toString() {
        String stringDeadline = this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D] " + super.toString() + " (by: " + stringDeadline + ")";
    }

}
