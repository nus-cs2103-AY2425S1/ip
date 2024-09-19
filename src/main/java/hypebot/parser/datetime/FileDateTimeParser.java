package hypebot.parser.datetime;

import static hypebot.common.Messages.MESSAGE_DELETING_PAST_DEADLINE;
import static hypebot.common.Messages.MESSAGE_DELETING_PAST_EVENT;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import hypebot.exception.datetime.DueDateParseException;
import hypebot.exception.datetime.EventDateTimeParseException;
import hypebot.exception.illegal.DatePassedException;
import hypebot.exception.illegal.IllegalEventTimesException;
import hypebot.main.HypeBot;
import hypebot.storage.StorageManager;
import hypebot.task.Deadline;
import hypebot.task.Event;

/**
 * Represents a {@code FileDateTimeParser} that parses {@link LocalDate} or
 * {@link LocalDateTime} objects encoded in a save {@link File} accessed by
 * a {@link HypeBot}'s {@link StorageManager}.
 * <p>A child of {@link DateTimeParser}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 */
public class FileDateTimeParser extends DateTimeParser {
    /**
     * Creates a new {@code FileDateTimeParser}.
     */
    public FileDateTimeParser() {
        super();
    }

    /**
     * Takes in a fully parsed {@link LocalDate} due date from task information
     * saved on a {@link File} and checks if due date has passed.
     *
     * @param dueDate {@link LocalDate} form of ue date.
     * @throws DatePassedException If due date has passed current date.
     */
    @Override
    protected void checkDueDatePassedBy(LocalDate dueDate) throws DatePassedException {
        if (dueDate.isBefore(currentDate)) {
            throw new DatePassedException(MESSAGE_DELETING_PAST_DEADLINE);
        }
    }

    /**
     * Takes in a {@link String} representing a due date for a {@link Deadline}
     * saved on a {@link File}, and returns the due date in {@link LocalDate} form.
     *
     * @param dueDateString {@link String} form of due date.
     * @return {@link LocalDate} form of due date.
     * @throws DueDateParseException If due date is encoded in an incorrect format.
     * @throws DatePassedException   If due date has passed current date.
     */
    @Override
    public LocalDate parseDueDate(String dueDateString)
            throws DueDateParseException, DatePassedException {
        try {
            LocalDate dueDate = LocalDate.parse(dueDateString, formatterDueDate);
            checkDueDatePassedBy(dueDate);
            return dueDate;
        } catch (DateTimeParseException e) {
            throw new DueDateParseException(e.getParsedString(), e.getErrorIndex());
        }
    }

    /**
     * Takes in two {@link LocalDateTime} objects representing a start time and end time
     * of an {@link Event} saved on a {@link File}, and checks that the {@link LocalDateTime}s
     * are in chronological order.
     *
     * @param startTime {@link LocalDateTime} representing the start time.
     * @param endTime   {@link LocalDateTime} representing the end time.
     * @throws IllegalEventTimesException If event times are inordered.
     */
    @Override
    protected void checkEventTimesChronological(LocalDateTime startTime, LocalDateTime endTime)
            throws IllegalEventTimesException {
        if (startTime.isAfter(endTime)) {
            throw new IllegalEventTimesException();
        }
    }

    /**
     * Takes in fully parsed {@link LocalDateTime} start time and end time of an {@link Event}
     * saved on a {@link File} and checks if event has concluded.
     *
     * @param startTime {@link LocalDateTime} representing the start time.
     * @param endTime   {@link LocalDateTime} representing the end time.
     * @throws DatePassedException If event has already concluded.
     */
    @Override
    protected void checkEventPassedBy(LocalDateTime startTime, LocalDateTime endTime)
            throws DatePassedException {
        if (startTime.isBefore(currentTime) && endTime.isBefore(currentTime)) {
            throw new DatePassedException(MESSAGE_DELETING_PAST_EVENT);
        }
    }

    /**
     * Takes in {@link String} representing a start time and end time for a {@link Event}
     * saved on a {@link File}, and returns them in a {@link LocalDateTime} array.
     *
     * @param eventTimesString {@link String} form of start and end time.
     * @return {@link LocalDateTime} array of start time and end time.
     * @throws EventDateTimeParseException If any event times are encoded in an incorrect format.
     * @throws DatePassedException         If {@link Event} has already concluded.
     */
    @Override
    public LocalDateTime[] parseEventTimes(String eventTimesString)
            throws EventDateTimeParseException, DatePassedException {
        String startTimeString = eventTimesString.split("/")[0];
        String endTimeString = eventTimesString.split("/")[1];
        try {
            LocalDateTime startTime = LocalDateTime.parse(startTimeString, formatterEventTime);
            LocalDateTime endTime = LocalDateTime.parse(endTimeString, formatterEventTime);
            checkEventTimesChronological(startTime, endTime);
            checkEventPassedBy(startTime, endTime);
            return new LocalDateTime[] {startTime, endTime};
        } catch (DateTimeParseException e) {
            throw new EventDateTimeParseException(e.getParsedString(), e.getErrorIndex());
        }
    }
}
