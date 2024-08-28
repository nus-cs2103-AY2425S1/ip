package slaveFiles;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate deadline;
    public Deadline (String task, LocalDate deadline) {
        super(task);
        this.deadline = deadline;
    }

    protected Deadline(boolean completed, String task, LocalDate deadline) {
        super(completed, task);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
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
