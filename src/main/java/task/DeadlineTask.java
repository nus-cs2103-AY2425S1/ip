package task;

import parser.InvalidDateException;
import parser.Parser;

import java.time.LocalDate;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {
    private final LocalDate date;

    public DeadlineTask(String description, boolean isDone, String date) throws InvalidDateException {
        super(description, isDone);
        this.date = Parser.parseDate(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.dateToString(date) + ")";
    }

    @Override
    public String simpleFormat() {
        return "D | " + super.simpleFormat() + " | " + Parser.dateToStorageString(date);
    }
}
