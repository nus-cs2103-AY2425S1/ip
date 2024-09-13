package jay.task;

import java.time.LocalDate;

import jay.parser.InvalidDateException;
import jay.parser.Parser;

/**
 * Represents a deadline Jay.task.
 */
public class DeadlineTask extends Task {
    private final LocalDate date;

    /**
     * Constructs a DeadlineTask object.
     *
     * @param description The description of the Jay.task.
     * @param date        The date of the Jay.task.
     * @throws InvalidDateException If the date is invalid.
     */
    public DeadlineTask(String description, boolean isDone, Priority priority, String date) throws InvalidDateException {
        super(description, isDone, priority);
        this.date = Parser.parseDate(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.convertDateToString(date) + ")";
    }

    @Override
    public String getSimpleFormat() {
        return "D | " + super.getSimpleFormat() + " | "
                + Parser.convertDateToStorageString(date);
    }
}
