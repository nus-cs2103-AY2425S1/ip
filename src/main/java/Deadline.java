import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    LocalDate deadline;
    Deadline(String taskString, String deadline) {
        super(taskString);
        this.deadline = LocalDate.parse(deadline);
    }

    LocalDate getDateTime() {
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