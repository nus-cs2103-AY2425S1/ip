package hypebot.parser.datetime;

import static hypebot.common.Messages.MESSAGE_DELETING_PAST_DEADLINE;
import static hypebot.common.Messages.MESSAGE_DELETING_PAST_EVENT;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import hypebot.exception.datetime.DueDateParseException;
import hypebot.exception.datetime.EventDateTimeParseException;
import hypebot.exception.illegal.DatePassedException;

public class FileDateTimeParser extends DateTimeParser {
    public FileDateTimeParser() {
        super();
    }

    /**
     * Takes in a fully parsed due date from tasks saved on a file
     * and checks if due date has passed.
     *
     * @param dueDate Due date of deadline outlined in file.
     * @throws DatePassedException If due date has passed current date.
     */
    @Override
    protected void checkDueDatePassedBy(LocalDate dueDate) throws DatePassedException {
        if (dueDate.isBefore(currentDate)) {
            throw new DatePassedException(MESSAGE_DELETING_PAST_DEADLINE);
        }
    }

    /**
     * Takes in a String representing a due date for a deadline saved on a file,
     * and returns the due date in LocalDate form.
     *
     * @param dueDateString String form of due date of deadline outlined in file.
     * @return LocalDate form of Due date of deadline outlined in file.
     * @throws DueDateParseException If due date is encoded in an incorrect format.
     * @throws DatePassedException If due date has passed current date.
     */
    @Override
    public LocalDate parseDueDate(String dueDateString)
            throws DueDateParseException, DatePassedException {
        try {
            LocalDate dueDate = LocalDate.parse(dueDateString, FORMATTER_DUE_DATE);
            checkDueDatePassedBy(dueDate);
            return dueDate;
        } catch (DateTimeParseException e) {
            throw new DueDateParseException(e.getParsedString(), e.getErrorIndex());
        }
    }

    @Override
    protected void checkEventTimesChronological(LocalDateTime startTime, LocalDateTime endTime) {

    }

    /**
     * Takes in fully parsed event start time and end time from tasks saved on a file
     * and checks if event has concluded.
     *
     * @param startTime Start time of event outlined in file.
     * @param endTime End time of event outlined in file.
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
     * Takes in Strings representing a start time and end time for a deadline saved on a file,
     * and returns them in a LocalDateTime array.
     *
     * @param eventTimesString String form of event start time outlined in file.
     * @return LocalDateTime array of event start time and end time outlined in file.
     * @throws EventDateTimeParseException If any event times are encoded in an incorrect format.
     * @throws DatePassedException If event has already concluded.
     */
    @Override
    public LocalDateTime[] parseEventTimes(String eventTimesString)
            throws EventDateTimeParseException, DatePassedException {
        String startTimeString = eventTimesString.split("/")[0];
        String endTimeString = eventTimesString.split("/")[1];
        try {
            LocalDateTime startTime = LocalDateTime.parse(startTimeString, FORMATTER_EVENT_TIME);
            LocalDateTime endTime = LocalDateTime.parse(endTimeString, FORMATTER_EVENT_TIME);
            checkEventTimesChronological(startTime, endTime);
            checkEventPassedBy(startTime, endTime);
            return new LocalDateTime[] {startTime, endTime};
        } catch (DateTimeParseException e) {
            throw new EventDateTimeParseException(e.getParsedString(), e.getErrorIndex());
        }
    }
}
