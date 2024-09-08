package knight2103.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    protected final LocalDate deadline;

    /**
     * Constructs a task with deadline object which contains a description of the task.
     * The object by default has the completion status set as not done.
     * The object also contains information of the deadline for task completion.
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toStringInFile() {
        return "D " + super.toStringInFile() + String.format(" | %s", this.deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}