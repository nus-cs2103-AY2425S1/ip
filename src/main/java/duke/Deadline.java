package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;
    public Deadline(String name, LocalDate deadline) {
        super(name);
        setDeadline(deadline);
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    protected String getWriteFormat() {
        return "D , " + (isDone ? "1" : "0") + " , " + name + " , " + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
