package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    public DeadlineTask(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = parseDeadline(deadline);
    }

    private LocalDateTime parseDeadline(String deadline) throws DateTimeParseException {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(deadline, inputFormatter);
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String getDescription() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        return this.getTaskType() + " | " +
                super.getDescription().replace("\n", "") + "| " +
                String.format("%s", this.deadline.format(outputFormatter));
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        return String.format("%s | %s | %s | %s",
                this.getTaskType(), getStatusIcon(), super.description, this.deadline.format(outputFormatter));
    }

}
