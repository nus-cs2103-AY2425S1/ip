package task;

import exception.DeadlineEmptyException;
import exception.InvalidDeadlineFormatException;
import exception.TaskNameEmptyException;

import java.time.LocalDateTime;

public class Deadline extends ScheduledTask {
    private final LocalDateTime deadline;

    public Deadline(boolean isDone, String taskName, String deadline) throws TaskNameEmptyException, DeadlineEmptyException, InvalidDeadlineFormatException {
        super(isDone, taskName);
        if (deadline.isBlank()) {
            throw new DeadlineEmptyException();
        }
        this.deadline = super.parseInputDateTime(deadline);
    }

    @Override
    public String save() {
        return "D " + super.save() + " | " + super.formatSaveFileDateTime(this.deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.formatOutputDateTime(this.deadline) + ")";
    }
}
