package tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate deadline;
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu");


    public Deadline(String task, LocalDate deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("%s | Deadline by %s", super.toString(), outputFormatter.format(deadline));
    }

    public LocalDate getDeadline() {
        return deadline;
    }
}
