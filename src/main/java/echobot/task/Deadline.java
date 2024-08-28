package echobot.task;

import echobot.exception.DeadlineEmptyException;
import echobot.exception.InvalidDeadlineFormatException;
import echobot.exception.TaskNameEmptyException;

import java.time.LocalDate;
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
    public boolean isTaskWithinThisDate(LocalDate date) {
        return this.deadline.toLocalDate().isAfter(date) || this.deadline.toLocalDate().isEqual(date);
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
