package Jay.task;

import Jay.parser.InvalidDateException;
import Jay.parser.Parser;

import java.time.LocalDate;

/**
 * Represents a deadline Jay.task.
 */
public class DeadlineTask extends Task {
    private final LocalDate date;

    public DeadlineTask(String description, boolean isDone, String date) throws InvalidDateException {
        super(description, isDone);
        this.date = Parser.parseDate(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.convertDateToString(date) + ")";
    }

    @Override
    public String simpleFormat() {
        return "D | " + super.simpleFormat() + " | "
                + Parser.convertDateToStorageString(date);
    }
}
