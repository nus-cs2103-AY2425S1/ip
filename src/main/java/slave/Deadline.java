package slave;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String task, LocalDate deadline) {
        super(task);
        this.deadline = deadline;
    }

    protected Deadline(boolean isCompleted, String task, LocalDate deadline) {
        super(isCompleted, task);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    public LocalDate getRawDeadline() {
        return this.deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadline() + ")";
    }

    @Override
    public String save() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
