package Jay.task;

import Jay.parser.InvalidDateException;
import Jay.parser.InvalidTimeException;
import Jay.parser.Parser;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents an event Jay.task.
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
        return "[E]" + super.toString() + " (from: " + Parser.convertDateToString(this.date) + " "
                + Parser.convertTimeToString(this.startTime) + " to: " + Parser.convertTimeToString(this.endTime) + ")";
    }

    @Override
    public String simpleFormat() {
        return "E | " + super.simpleFormat() + " | " + Parser.convertDateToStorageString(this.date) + " | "
                + Parser.convertTimeToStorageString(this.startTime) + " | "
                + Parser.convertTimeToStorageString(this.endTime);
    }
}
