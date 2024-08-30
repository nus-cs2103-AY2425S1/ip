package garfield.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    private DateTimeFormatter saveFormatter;
    private DateTimeFormatter uiFormatter;

    public Deadline(String taskDescription, LocalDateTime deadline) {
        super(taskDescription);
        this.deadline = deadline;
        this.saveFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.uiFormatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mma");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(uiFormatter) + ")";
    }

    @Override
    public String toSaveRepresentation() {
        return "D | " + super.toSaveRepresentation() + " | " + deadline.format(saveFormatter);
    }
}
