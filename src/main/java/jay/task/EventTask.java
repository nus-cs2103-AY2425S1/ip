package jay.task;

import java.time.LocalDate;
import java.time.LocalTime;

import jay.parser.InvalidDateException;
import jay.parser.InvalidTimeException;
import jay.parser.Parser;


/**
 * Represents an event Jay.task.
 */
public class EventTask extends Task {
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * Constructs an EventTask object.
     *
     * @param description The description of the Jay.task.
     * @param date        The date of the Jay.task.
     * @param startTime   The start time of the Jay.task.
     * @param endTime     The end time of the Jay.task.
     * @throws InvalidDateException If the date is invalid.
     * @throws InvalidTimeException If the time is invalid.
     */
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
    public String getSimpleFormat() {
        return "E | " + super.getSimpleFormat() + " | " + Parser.convertDateToStorageString(this.date) + " | "
                + Parser.convertTimeToStorageString(this.startTime) + " | "
                + Parser.convertTimeToStorageString(this.endTime);
    }
}
