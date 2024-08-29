package task;

import parser.InvalidDateException;
import parser.InvalidTimeException;
import parser.Parser;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents an event task.
 */
public class EventTask extends Task {
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public EventTask(String description, boolean isDone, String date, String startTime, String endTime)
            throws InvalidDateException, InvalidTimeException {
        super(description, isDone);
        this.date = Parser.parseDate(date);
        this.startTime = Parser.parseTime(startTime);
        this.endTime = Parser.parseTime(endTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.dateToString(this.date) + " "
                + Parser.timeToString(this.startTime) + " to: " + Parser.timeToString(this.endTime) + ")";
    }

    @Override
    public String simpleFormat() {
        return "E | " + super.simpleFormat() + " | " + Parser.dateToStorageString(this.date) + " | "
                + Parser.timeToStorageString(this.startTime) + " | " + Parser.timeToStorageString(this.endTime);
    }
}
