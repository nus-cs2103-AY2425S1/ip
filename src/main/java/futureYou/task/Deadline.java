package futureYou.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    // yyyy-MM-dd HH:mm

    public Deadline(String name, LocalDateTime date) {
        super(name);
        this.deadline = date;
    }

    public Deadline(String name, Boolean taskStatus, LocalDateTime date) {
        super(name, taskStatus);
        this.deadline = date;
    }

    public String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy - HH:mm"));
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + "|" + this.getDeadline();
    }

    @Override
    public String print() {
        return super.print() + " (by: " + this.getDeadline() + ")";
    }
}