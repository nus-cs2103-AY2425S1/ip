package yapmeister.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline Task
 * @author BlazeChron
 */
public class Deadline extends Task{
    private final String deadline;
    private final LocalDate deadlineDate;

    /**
     * Creates a Deadline Task
     * @param taskName Name of the task.
     * @param deadline Time of deadline.
     */
    public Deadline(String taskName, String deadline) {
        super(taskName);
        String dl;
        LocalDate dld = null;
        try {
            dld = LocalDate.parse(deadline);
            dl = dld.format(DateTimeFormatter.ISO_WEEK_DATE);
        } catch (DateTimeParseException e) {
            dl = deadline;
        }
        this.deadlineDate = dld;
        this.deadline = dl;
    }

    public String getDeadline() {
        return this.deadline;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String exportString() {
        String completedString;
        if (this.getIsCompleted()) {
            completedString = "1";
        } else {
            completedString = "0";
        }
        return String.format("D|%s|%s|%s", completedString, this.getTaskName(), this.deadline);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                                super.toString(),
                                this.deadline);
    }
}
