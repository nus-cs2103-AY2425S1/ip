package tudee.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate deadline;
    public Deadline(String taskString, String deadline) {
        super(taskString);
        this.deadline = LocalDate.parse(deadline);
    }

    public LocalDate getDateTime() {
        return  this.deadline;
    }

    @Override
    public String toFileString() {
        return "D | " + (done ? 1 : 0) +" | " + taskString + " | " + deadline;
    }

    @Override
    public String toString() {
        String convertedDeadline = deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + convertedDeadline + ")";
    }
}