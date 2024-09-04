package mylo.task;

import mylo.utils.exceptions.IllegalValueException;
import mylo.utils.formatters.Formatter;
import mylo.utils.helpers.HelperFunctions;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String title, String deadline) throws IllegalValueException {
        this(title, deadline, false);
    }

    public Deadline(String title, String deadline, boolean isDone) throws IllegalValueException {
        super(title, isDone);
        this.deadline = HelperFunctions.stringToDateTime(deadline);
    }

    public boolean dueOnDate(LocalDateTime dateTime) {
        return deadline.toLocalDate().equals(dateTime.toLocalDate());
    }

    private String getDeadlineString() {
        return  " (by: " + Formatter.dateTimeDisplay(deadline) + ")";
    }

    @Override
    public String storageFormat() {
        return String.format("DEADLINE | %s | %s | %s", super.getStatus(), super.getTitle(), Formatter.dateTimeStorage(deadline));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + getDeadlineString();
    }
}
