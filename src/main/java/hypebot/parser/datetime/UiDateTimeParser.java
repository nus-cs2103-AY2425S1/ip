package hypebot.parser.datetime;

import static hypebot.common.Messages.ERROR_DATE_PASSED;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

import hypebot.exception.datetime.DueDateParseException;
import hypebot.exception.datetime.HappeningSearchDateParseException;
import hypebot.exception.illegal.IllegalEventTimesException;
import hypebot.exception.missing.MissingEventTimeException;
import hypebot.exception.missing.MissingSearchDateException;
import hypebot.exception.illegal.NoMatchingShortcutException;
import hypebot.exception.illegal.DatePassedException;
import hypebot.exception.datetime.EventDateTimeParseException;
import hypebot.exception.missing.MissingDueDateException;

/**
 * Represents the UiDateTimeParser that parses all dates and times
 * from user input or saved task files.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class UiDateTimeParser extends DateTimeParser {
    private enum DAY_SHORTCUT {
        TODAY,
        TOMORROW,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    public UiDateTimeParser() {
        super();
    }

    private DAY_SHORTCUT extractDayShortcut(String word) throws NoMatchingShortcutException {
        return switch (word) {
            case "tdy", "today" -> DAY_SHORTCUT.TODAY;
            case "tmw", "tmrw", "tomorrow" -> DAY_SHORTCUT.TOMORROW;
            case "mon", "monday" -> DAY_SHORTCUT.MONDAY;
            case "tue", "tues", "tuesday" -> DAY_SHORTCUT.TUESDAY;
            case "wed", "wednesday" -> DAY_SHORTCUT.WEDNESDAY;
            case "thu", "thur", "thurs", "thursday" -> DAY_SHORTCUT.THURSDAY;
            case "fri", "friday" -> DAY_SHORTCUT.FRIDAY;
            case "sat", "saturday" -> DAY_SHORTCUT.SATURDAY;
            case "sun", "sunday" -> DAY_SHORTCUT.SUNDAY;
            default -> throw new NoMatchingShortcutException();
        };
    }

    private LocalDate getNextOccurrence(DayOfWeek dayOfWeek) {
        return currentDate.with(TemporalAdjusters.next(dayOfWeek));
    }

    private LocalDate convertDayShortcutToLocalDate(DAY_SHORTCUT dayShortcut) {
        return switch (dayShortcut) {
            case TODAY -> currentDate;
            case TOMORROW -> currentDate.plusDays(1);
            case MONDAY -> getNextOccurrence(DayOfWeek.MONDAY);
            case TUESDAY -> getNextOccurrence(DayOfWeek.TUESDAY);
            case WEDNESDAY -> getNextOccurrence(DayOfWeek.WEDNESDAY);
            case THURSDAY -> getNextOccurrence(DayOfWeek.THURSDAY);
            case FRIDAY -> getNextOccurrence(DayOfWeek.FRIDAY);
            case SATURDAY -> getNextOccurrence(DayOfWeek.SATURDAY);
            case SUNDAY -> getNextOccurrence(DayOfWeek.SUNDAY);
        };
    }

    /**
     * Takes in the full line user enters and returns the String form of the dates entered.
     *
     * @param line Full String command entered by user.
     * @return String array containing dates entered by user.
     */
    private String[] extractDates(String line) {
        String[] splitBySlash = line.split(" /");
        String[] dates = new String[splitBySlash.length - 1];
        System.arraycopy(splitBySlash, 1, dates, 0, splitBySlash.length - 1);
        return dates;
    }

    /**
     * Takes in the full line user enters and checks whether the due date has been entered.
     *
     * @param line Full String command entered by user.
     * @throws MissingDueDateException If due date is missing in deadline entry.
     */
    private void checkDueDateExists(String line) throws MissingDueDateException {
        String[] dates = extractDates(line);
        if (dates.length < 1) {
            throw new MissingDueDateException();
        }
    }

    private LocalDate convertStringToDueDate(String date) throws DateTimeParseException {
        try {
            return convertDayShortcutToLocalDate(extractDayShortcut(date.toLowerCase()));
        } catch (NoMatchingShortcutException e) {
            return LocalDate.parse(date, FORMATTER_DUE_DATE);
        }
    }

    /**
     * Takes in a fully parsed due date entered by user and checks if due date has passed.
     *
     * @param dueDate Proposed due date entered by user.
     * @throws IllegalArgumentException If due date has passed current date.
     */
    @Override
    protected void checkDueDatePassedBy(LocalDate dueDate) throws DatePassedException {
        if (dueDate.isBefore(currentDate)) {
            throw new DatePassedException(ERROR_DATE_PASSED);
        }
    }

    /**
     * Takes in the full line user enters and returns the due date specified by the user in
     * LocalDate form.
     *
     * @param fullCommand Full String command entered by user.
     * @return Due date specified by user in LocalDate form.
     * @throws MissingDueDateException If due date is missing in deadline entry.
     * @throws DueDateParseException If due date is not entered in yyyy-mm-dd format
     *                               or aligned with alias for a date.
     * @throws IllegalArgumentException If due date has passed current date.
     */
    @Override
    public LocalDate parseDueDate(String fullCommand)
            throws MissingDueDateException, DueDateParseException, DatePassedException {
        try {
            checkDueDateExists(fullCommand);
            String dueDateString = extractDates(fullCommand)[0];
            LocalDate dueDate = convertStringToDueDate(dueDateString);
            checkDueDatePassedBy(dueDate);
            return dueDate;
        } catch (DateTimeParseException e) {
            throw new DueDateParseException(e.getParsedString(), e.getErrorIndex());
        }
    }

    /**
     * Takes in the full line user enters and checks whether the event
     * start time and end time has been entered.
     *
     * @param line Full String command entered by user.
     * @throws MissingEventTimeException If start time or end time is missing in event entry.
     */
    private void checkEventTimesExist(String line) throws MissingEventTimeException {
        String[] dates = extractDates(line);
        if (dates.length < 2) {
            throw new MissingEventTimeException();
        }
    }

    private String extractDateFromDateTime(String dateTime) {
        return dateTime.split(" ")[0];
    }

    private String extractTimeFromDateTime(String dateTime) {
        return dateTime.split(" ")[1];
    }

    /**
     * Checks whether entered startTime is before entered endTime.
     *
     * @param startTime LocalDateTime object representing the start of an event.
     * @param endTime LocalDateTime object representing the end of an event.
     * @throws IllegalArgumentException If end time comes before start time.
     */
    @Override
    protected void checkEventTimesChronological(LocalDateTime startTime, LocalDateTime endTime)
            throws IllegalEventTimesException {
        if (startTime.isAfter(endTime)) {
            throw new IllegalEventTimesException();
        }
    }

    /**
     * Verifies whether an event has passed based on startTime and endTime.
     *
     * @param startTime LocalDateTime object representing the start of an event.
     * @param endTime LocalDateTime object representing the end of an event.
     * @throws DatePassedException If the event has already concluded.
     */
    protected void checkEventPassedBy(LocalDateTime startTime, LocalDateTime endTime)
            throws DatePassedException {
        if (startTime.isBefore(currentTime) && endTime.isBefore(currentTime)) {
            throw new DatePassedException(ERROR_DATE_PASSED);
        }
    }

    private LocalDateTime convertStringToEventTime(String dateTime) throws DateTimeParseException {
        String date = extractDateFromDateTime(dateTime);
        String time = extractTimeFromDateTime(dateTime);
        try {
            return LocalDateTime.of(convertDayShortcutToLocalDate(extractDayShortcut(date)),
                    LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmm")));
        } catch (NoMatchingShortcutException e) {
            return LocalDateTime.parse(dateTime, FORMATTER_EVENT_TIME);
        }
    }

    /**
     * Takes in the full line user enters and returns an event's start time and end time
     * specified by the user in an LocalDateTime array {startTime, endTime}.
     *
     * @param fullCommand Full String command entered by user.
     * @return Event start time and end time specified by user in a LocalDateTime array.
     * @throws MissingEventTimeException If event start time or end time is missing in deadline entry.
     * @throws EventDateTimeParseException If event time is not entered in accepted formats.
     * @throws DatePassedException If event has concluded by the current date.
     */
    @Override
    public LocalDateTime[] parseEventTimes(String fullCommand)
            throws MissingEventTimeException, EventDateTimeParseException, DatePassedException {
        try {
            checkEventTimesExist(fullCommand);

            String[] dateTimes = extractDates(fullCommand);

            String startDateTimeString = dateTimes[0];
            String endDateTimeString = dateTimes[1];

            LocalDateTime startDateTime = convertStringToEventTime(startDateTimeString);
            LocalDateTime endDateTime = convertStringToEventTime(endDateTimeString);

            checkEventTimesChronological(startDateTime, endDateTime);
            checkEventPassedBy(startDateTime, endDateTime);
            return new LocalDateTime[]{startDateTime, endDateTime};
        } catch (DateTimeParseException e) {
            throw new EventDateTimeParseException(e.getParsedString(), e.getErrorIndex());
        }
    }

    /**
     * Takes in the full line user enters and checks whether the search date has been entered.
     *
     * @param line Full String command entered by user.
     * @throws MissingSearchDateException If search date is missing in happening search.
     */
    private void checkHappeningDateExists(String line) throws MissingSearchDateException {
        String[] dates = extractDates(line);
        if (dates.length < 1) {
            throw new MissingSearchDateException();
        }
    }

    private LocalDate parseHappeningDateWithAlias(String[] dates) throws DateTimeParseException {
        try {
            return convertDayShortcutToLocalDate(extractDayShortcut(dates[0].toLowerCase()));
        } catch (NoMatchingShortcutException e) {
            return LocalDate.parse(dates[0], FORMATTER_DUE_DATE);
        }
    }

    /**
     * Takes in the full line user enters and returns the search date user is looking up tasks for
     * in LocalDate form.
     *
     * @param fullCommand Full String command entered by user.
     * @return LocalDate representation of user-entered search date for tasks.
     * @throws MissingSearchDateException If search date not entered by user.
     * @throws DateTimeParseException If search date not in 'yyyy-mm-dd' format.
     */
    public LocalDate parseHappeningDate(String fullCommand)
            throws MissingSearchDateException, HappeningSearchDateParseException {
        try {
            checkHappeningDateExists(fullCommand);

            String[] dates = extractDates(fullCommand);

            return parseHappeningDateWithAlias(dates);
        } catch (DateTimeParseException e) {
            throw new HappeningSearchDateParseException(e.getParsedString(), e.getErrorIndex());
        }
    }
}
