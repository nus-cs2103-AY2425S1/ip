package zaibot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a task with the "Deadline" category.
 * It has a deadline DateTime attribute to signify the /by.
 */
public class DeadlineTask extends Task {

    private LocalDateTime deadline;

    public DeadlineTask(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toSaveString() {
        return String.format("D | %s | %s",
                super.toSaveString(), this.deadline);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = getFormatter();
        return String.format("[D]%s (by: %s)", super.toString(), deadline.format(formatter));
    }
}
