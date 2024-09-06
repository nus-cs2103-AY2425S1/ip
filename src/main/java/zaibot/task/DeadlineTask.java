package zaibot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a task with the "Deadline" category.
 * It has a deadline DateTime attribute to signify the /by.
 */
public class DeadlineTask extends Task {

    private final LocalDateTime deadline;

    /**
     * Creates a DeadlineTask from the name, and a given deadline
     *
     * @param name     A string representing the name of the event
     * @param deadline A LocalDateTime object representing the datetime of the event
     */
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

    @Override
    public boolean equals(Object other) {
        if (other instanceof DeadlineTask otherEvent) {
            boolean isSameAsSuper = super.equals(other);
            return (isSameAsSuper
                    && deadline.equals(otherEvent.deadline));
        } else {
            return false;
        }
    }
}
