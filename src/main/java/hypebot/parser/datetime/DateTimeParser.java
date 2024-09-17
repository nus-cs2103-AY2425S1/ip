package hypebot.parser.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import hypebot.exception.datetime.DueDateParseException;
import hypebot.exception.datetime.EventDateTimeParseException;
import hypebot.exception.illegal.IllegalEventTimesException;
import hypebot.exception.missing.MissingDueDateException;
import hypebot.exception.missing.MissingEventTimeException;
import hypebot.exception.illegal.DatePassedException;

public abstract class DateTimeParser {
    protected final DateTimeFormatter FORMATTER_DUE_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected final DateTimeFormatter FORMATTER_EVENT_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected LocalDate currentDate = LocalDate.now();
    protected LocalDateTime currentTime = LocalDateTime.now();
    public enum DateType {
        DUE_DATE,
        EVENT_TIME,
        SEARCH_DATE
    }

    protected abstract void checkDueDatePassedBy(LocalDate dueDate) throws DatePassedException;
    protected abstract void checkEventTimesChronological(LocalDateTime startTime, LocalDateTime endTime)
            throws IllegalEventTimesException;
    protected abstract void checkEventPassedBy(LocalDateTime startTime, LocalDateTime endTime)
            throws DatePassedException;
    public abstract LocalDate parseDueDate(String text)
            throws MissingDueDateException, DueDateParseException, DatePassedException;
    public abstract LocalDateTime[] parseEventTimes(String text)
            throws MissingEventTimeException, EventDateTimeParseException, DatePassedException;
}
