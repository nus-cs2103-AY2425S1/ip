package noisy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    private LocalDate deadline;

    public Deadline(String description, boolean isDone, LocalDate deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String formatTask() {
        String status = isDone? "1" : "0";
        return "D | " + this.description + " | " + status + " | " + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}