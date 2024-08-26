package task;

import exception.EventStartEndDateEmptyException;
import exception.InvalidDeadlineFormatException;
import exception.TaskNameEmptyException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends ScheduledTask {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(boolean isDone, String taskName, String from, String to) throws TaskNameEmptyException, EventStartEndDateEmptyException, InvalidDeadlineFormatException {
        super(isDone, taskName);
        if (from.isBlank() || to.isBlank()) {
            throw new EventStartEndDateEmptyException();
        }
        this.from = super.parseInputDateTime(from);
        this.to = super.parseInputDateTime(to);
    }

    @Override
    public boolean isTaskWithinThisDate(LocalDate date) {
        LocalDate from = this.from.toLocalDate();
        LocalDate to = this.to.toLocalDate();
        return (from.isBefore(date) || from.isEqual(date)) && (to.isAfter(date) || to.isEqual(date));
    }

    @Override
    public String save() {
        return "E " + super.save() + " | " + super.formatSaveFileDateTime(this.from) + " | " + super.formatSaveFileDateTime(this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + super.formatOutputDateTime(this.from) + " to: " + super.formatOutputDateTime(this.to) + ")";
    }
}
