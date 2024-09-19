package hypebot.parser.datetime;

import static hypebot.common.Messages.ERROR_DATE_PASSED;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

import hypebot.command.HappeningCommand;
import hypebot.exception.datetime.DueDateParseException;
import hypebot.exception.datetime.EventDateTimeParseException;
import hypebot.exception.datetime.HappeningSearchDateParseException;
import hypebot.exception.illegal.DatePassedException;
import hypebot.exception.illegal.IllegalEventTimesException;
import hypebot.exception.illegal.NoMatchingShortcutException;
import hypebot.exception.missing.MissingDueDateException;
import hypebot.exception.missing.MissingEventTimeException;
import hypebot.exception.missing.MissingSearchDateException;
import hypebot.task.Deadline;
import hypebot.task.Event;
import hypebot.task.Task;
import hypebot.ui.gui.UiGuiMainWindow;

/**
 * Represents the {@code UiDateTimeParser} that parses all {@link LocalDate}
 * or {@link LocalDateTime} from user input taken in by {@link UiGuiMainWindow}.
 * <p>A child of {@link DateTimeParser}</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 */
public class UiDateTimeParser extends DateTimeParser {
    /** Types of shortcuts that may be used by user. */
    private enum DayShortcut {
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

    /**
     * Creates a new {@code UiDateTimeParser}.
     */
    public UiDateTimeParser() {
        super();
    }

    /**
     * Takes in possible date shortcut used by user when entering a date or time,
     * and extracts the corresponding {@code DayShortcut}.
     *
     * @param alias Word entered by user representing a date shortcut.
     * @return The corresponding {@code DayShortcut}.
     * @throws NoMatchingShortcutException If alias for date does not match any {@code DayShortcut}.
     */
    private DayShortcut extractDayShortcut(String alias) throws NoMatchingShortcutException {
        return switch (alias.toLowerCase()) {
        case "tdy", "today" -> DayShortcut.TODAY;
        case "tmw", "tmrw", "tomorrow" -> DayShortcut.TOMORROW;
        case "mon", "monday" -> DayShortcut.MONDAY;
        case "tue", "tues", "tuesday" -> DayShortcut.TUESDAY;
        case "wed", "wednesday" -> DayShortcut.WEDNESDAY;
        case "thu", "thur", "thurs", "thursday" -> DayShortcut.THURSDAY;
        case "fri", "friday" -> DayShortcut.FRIDAY;
        case "sat", "saturday" -> DayShortcut.SATURDAY;
        case "sun", "sunday" -> DayShortcut.SUNDAY;
        default -> throw new NoMatchingShortcutException();
        };
    }

    private LocalDate getNextOccurrence(DayOfWeek dayOfWeek) {
        return currentDate.with(TemporalAdjusters.next(dayOfWeek));
    }

    /**
     * Takes in a {@code DayShortcut} extracted from date entry fields and
     * returns the corresponding {@link LocalDate}.
     *
     * @param dayShortcut {@code DayShortcut} extracted from date entry fields.
     * @return Actual {@link LocalDate} prescribed by the {@code DayShortcut}.
     */
    private LocalDate convertDayShortcutToLocalDate(DayShortcut dayShortcut) {
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
     * Takes in the full line user enters and returns the {@link String} array of the
     * dates (or times) entered.
     *
     * @param line Full {@link String} line entered by user.
     * @return {@link String} array containing dates or times entered by user.
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
     * @param line Full {@link String} line entered by user.
     * @throws MissingDueDateException If due date is missing in {@link Deadline} entry.
     */
    private void checkDueDateExists(String line) throws MissingDueDateException {
        String[] dates = extractDates(line);
        if (dates.length < 1) {
            throw new MissingDueDateException();
        }
    }

    /**
     * Takes in the {@link String} form of a due date associated with a {@link Deadline}
     * and tries to extract and parse a {@link LocalDate} using {@code DayShortcuts},
     * then tries to format according to {@code FORMATTER_DUE_DATE} in {@link DateTimeParser}.
     *
     * @param date {@link String} form of due date.
     * @return {@link LocalDate} form of due date.
     * @throws DateTimeParseException If due date entered is not in any expected format.
     */
    private LocalDate convertStringToDueDate(String date) throws DateTimeParseException {
        try {
            return convertDayShortcutToLocalDate(extractDayShortcut(date));
        } catch (NoMatchingShortcutException e) {
            return LocalDate.parse(date, formatterDueDate);
        }
    }

    /**
     * Takes in a {@link LocalDate} representing a due date of a {@link Deadline} object
     * and checks that {@link LocalDate} has not passed the {@code currentDate}.
     *
     * @param dueDate {@link LocalDate} form of due date.
     * @throws DatePassedException If due date has passed current date.
     */
    @Override
    protected void checkDueDatePassedBy(LocalDate dueDate) throws DatePassedException {
        if (dueDate.isBefore(currentDate)) {
            throw new DatePassedException(ERROR_DATE_PASSED);
        }
    }

    /**
     * Takes in the full line user enters and returns the due date specified by the user in
     * {@link LocalDate} form.
     *
     * @param fullCommand Full {@link String} line entered by user.
     * @return {@link LocalDate} form of due date specified by user.
     * @throws MissingDueDateException If due date is missing in deadline entry.
     * @throws DueDateParseException   If due date is not entered in an expected format.
     * @throws DatePassedException     If due date has passed current date.
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
     * Takes in two {@link LocalDateTime} objects representing a start time and end time
     * of an {@link Event} object and checks that the {@link LocalDateTime}s are in
     * chronological order.
     *
     * @param startTime {@link LocalDateTime} representing the start time of a {@link Event}.
     * @param endTime   {@link LocalDateTime} representing the end time of a {@link Event}.
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
     * Verifies whether an {@link Event}.
     *
     * @param startTime {@link LocalDateTime} object representing the start of an {@link Event}.
     * @param endTime {@link LocalDateTime} object representing the end of an {@link Event}.
     * @throws DatePassedException If the {@link Event} has already concluded.
     */
    @Override
    protected void checkEventPassedBy(LocalDateTime startTime, LocalDateTime endTime)
            throws DatePassedException {
        if (startTime.isBefore(currentTime) && endTime.isBefore(currentTime)) {
            throw new DatePassedException(ERROR_DATE_PASSED);
        }
    }

    /**
     * Takes in an event time in {@link String} form and returns the {@link LocalDateTime}
     * form of the event time.
     *
     * @param dateTime An event time in {@link String} form.
     * @return {@link LocalDateTime} form of event time.
     * @throws DateTimeParseException If event time is not in an expected format.
     */
    private LocalDateTime convertStringToEventTime(String dateTime) throws DateTimeParseException {
        String date = extractDateFromDateTime(dateTime);
        String time = extractTimeFromDateTime(dateTime);
        try {
            return LocalDateTime.of(convertDayShortcutToLocalDate(extractDayShortcut(date)),
                    LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmm")));
        } catch (NoMatchingShortcutException e) {
            return LocalDateTime.parse(dateTime, formatterEventTime);
        }
    }

    /**
     * Takes in the full line user enters and returns an {@link Event}'s start time and end time
     * specified by the user in an {@link LocalDateTime} array {startTime, endTime}.
     *
     * @param fullCommand Full {@link String} line entered by user.
     * @return {@link LocalDateTime} array of an {@link Event}'s start time and end time
     *         specified by user.
     * @throws MissingEventTimeException   If missing either start time or end time in
     *                                     {@link Event} entry.
     * @throws EventDateTimeParseException If event time is not entered in accepted formats.
     * @throws DatePassedException         If event has concluded by the current date.
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
     * @param line Full {@link String} line entered by user.
     * @throws MissingSearchDateException If search date is missing in happening search.
     */
    private void checkHappeningDateExists(String line) throws MissingSearchDateException {
        String[] dates = extractDates(line);
        if (dates.length < 1) {
            throw new MissingSearchDateException();
        }
    }

    /**
     * Takes in a String array of dates given by user, and attempts to parse a
     * {@link LocalDate} search date with DayShortcuts, and then tries to parse
     * the entry without.
     *
     * @param dates {@link String} array of dates given by user.
     * @return {@link LocalDate} representation of user-entered search date.
     * @throws DateTimeParseException If search date not in an expected format.
     */
    private LocalDate parseHappeningDateWithShortcut(String[] dates) throws DateTimeParseException {
        try {
            return convertDayShortcutToLocalDate(extractDayShortcut(dates[0]));
        } catch (NoMatchingShortcutException e) {
            return LocalDate.parse(dates[0], formatterDueDate);
        }
    }

    /**
     * Takes in the full line user enters and returns the {@link LocalDate} search date
     * a {@link HappeningCommand} uses to look up {@link Task}s occurring on this search date.
     *
     * @param fullCommand Full {@link String} command entered by user.
     * @return {@link LocalDate} representation of user-entered search date for tasks.
     * @throws MissingSearchDateException If search date not entered by user.
     * @throws DateTimeParseException     If search date not in an expected format.
     */
    public LocalDate parseHappeningDate(String fullCommand)
            throws MissingSearchDateException, HappeningSearchDateParseException {
        try {
            checkHappeningDateExists(fullCommand);

            String[] dates = extractDates(fullCommand);

            return parseHappeningDateWithShortcut(dates);
        } catch (DateTimeParseException e) {
            throw new HappeningSearchDateParseException(e.getParsedString(), e.getErrorIndex());
        }
    }
}
