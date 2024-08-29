package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate deadline;
    public Deadline(String taskName) {
        super(taskName.split("/by")[0]);
        this.deadline = LocalDate.parse(taskName.split("/by")[1].trim());
    }

    public Deadline(String taskName, boolean isCompleted) {
        super(taskName.split("/by")[0], isCompleted);
        this.deadline = LocalDate.parse(taskName.split("/by")[1].trim());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String writeToFile() {
        return "D " + (super.isCompleted() ? "0" : "1") + " " + this.getTaskName() + "/by " + this.deadline;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

}
