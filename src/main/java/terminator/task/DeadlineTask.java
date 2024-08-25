package terminator.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        String byDate = deadline.format(formatter);
        return super.toString() + " by: " + byDate;
    }

    @Override
    public String getDataRepresentation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd+HH:mm");
        return super.getDataRepresentation()
                + this.deadline.format(formatter) + " "
                + this.description;
    }
}
