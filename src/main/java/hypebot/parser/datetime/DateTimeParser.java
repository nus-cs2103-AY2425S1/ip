package hypebot.parser.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import hypebot.exception.datetime.DueDateParseException;
import hypebot.exception.datetime.EventDateTimeParseException;
import hypebot.exception.illegal.DatePassedException;
import hypebot.exception.illegal.IllegalEventTimesException;
import hypebot.exception.missing.MissingDueDateException;
import hypebot.exception.missing.MissingEventTimeException;
import hypebot.task.Deadline;
import hypebot.task.Event;

/**
 * Represents a base {@code DateTimeParser} that serves as basis for all
 * parsers of {@link LocalDate} or {@link LocalDateTime} objects
 * entered by user or decoded from a save file.
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see FileDateTimeParser
 * @see UiDateTimeParser
 */
public abstract class DateTimeParser {
    protected final DateTimeFormatter formatterDueDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected final DateTimeFormatter formatterEventTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected LocalDate currentDate = LocalDate.now();
    protected LocalDateTime currentTime = LocalDateTime.now();

    /**
     * Takes in a {@link LocalDate} representing a due date of a {@link Deadline} object
     * and checks that {@link LocalDate} has not passed the {@code currentDate}.
     *
     * @param dueDate {@link LocalDate} representing a due date of a {@link Deadline}.
     * @throws DatePassedException If due date has passed {@code currentDate}.
     */
    protected abstract void checkDueDatePassedBy(LocalDate dueDate) throws DatePassedException;

    /**
     * Takes in two {@link LocalDateTime} objects representing a start time and end time
     * of an {@link Event} object and checks that the {@link LocalDateTime}s are in
     * chronological order.
     *
     * @param startTime {@link LocalDateTime} representing the start time of a {@link Event}.
     * @param endTime   {@link LocalDateTime} representing the end time of a {@link Event}.
     * @throws IllegalEventTimesException If event times are inordered.
     */
    protected abstract void checkEventTimesChronological(LocalDateTime startTime, LocalDateTime endTime)
            throws IllegalEventTimesException;

    /**
     * Takes in two {@link LocalDateTime} objects representing a start time and end time
     * of an {@link Event} object and checks that both the {@link LocalDateTime}s have not
     * passed the {@code currentTime}.
     *
     * @param startTime {@link LocalDateTime} representing the start time of a {@link Event}.
     * @param endTime   {@link LocalDateTime} representing the end time of a {@link Event}.
     * @throws DatePassedException If event times have passed {@code currentTime}.
     */
    protected abstract void checkEventPassedBy(LocalDateTime startTime, LocalDateTime endTime)
            throws DatePassedException;

    /**
     * Takes in a {@link String} and parses a {@link LocalDate} representing the due date
     * of a {@link Deadline} object.
     *
     *
     * @param text {@link String} text containing information about a due date.
     * @return {@link LocalDate} representing the due date of a {@link Deadline} object.
     * @throws MissingDueDateException If due date is missing in {@code text}.
     * @throws DueDateParseException   If due date in {@code text} is in a format different
     *                                 from {@code FORMATTER_DUE_DATE}.
     * @throws DatePassedException     If due date has passed {@code currentDate}.
     */
    public abstract LocalDate parseDueDate(String text)
            throws MissingDueDateException, DueDateParseException, DatePassedException;

    /**
     * Takes in a {@link String} and parses the {@link LocalDateTime}s representing
     * the start time and end time of an {@link Event} object.
     *
     * @param text {@link String} text containing information about event start and end times.
     * @return A {@link LocalDateTime} array containing {@link Event} start time and end time.
     * @throws MissingEventTimeException   If any event times are missing in {@code text}.
     * @throws EventDateTimeParseException If any event times in {@code text} are in a
     *                                     different format from {@code FORMATTER_EVENT_TIME}.
     * @throws DatePassedException         If both event times have passed {@code currentTime}.
     */
    public abstract LocalDateTime[] parseEventTimes(String text)
            throws MissingEventTimeException, EventDateTimeParseException, DatePassedException;
}
