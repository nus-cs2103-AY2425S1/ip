package bobby;

import bobby.exception.EmptyDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    public static Deadline createDeadline(String input) throws EmptyDescriptionException {
        String deadlineDescription = input.substring(8).trim();
        if (deadlineDescription.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }
        String deadlineName = deadlineDescription.split(" /by ")[0];
        String deadlineDay = deadlineDescription.split(" /by ")[1];
        return new Deadline(deadlineName, deadlineDay);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String taskInFile() {
        return String.format("D | %s | %s | /by %s", this.getStatusIcon(), this.getDescription(),
                this.deadline);
    }
}
