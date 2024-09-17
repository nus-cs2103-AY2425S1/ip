package hypebot.parser;

import static hypebot.common.Messages.ERROR_DEADLINE_DATE_MISSING;
import static hypebot.common.Messages.ERROR_DEADLINE_DATE_PASSED;
import static hypebot.common.Messages.ERROR_DEADLINE_DATE_WRONG_FORMAT;
import static hypebot.common.Messages.ERROR_EVENT_TIMES_INORDERED;
import static hypebot.common.Messages.ERROR_EVENT_TIME_MISSING;
import static hypebot.common.Messages.ERROR_EVENT_TIME_PASSED;
import static hypebot.common.Messages.ERROR_EVENT_TIME_WRONG_FORMAT;
import static hypebot.common.Messages.ERROR_HAPPENING_DATE_MISSING;
import static hypebot.common.Messages.ERROR_HAPPENING_DATE_WRONG_FORMAT;
import static hypebot.common.Messages.MESSAGE_DELETING_PAST_DEADLINE;
import static hypebot.common.Messages.MESSAGE_DELETING_PAST_EVENT;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

/**
 * Represents the DateTimeParser that parses all dates and times
 * from user input or saved task files.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class DateTimeParser {
    private static final DateTimeFormatter FORMATTER_DUE_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FORMATTER_EVENT_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private enum Alias {
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

    private static Alias alias(String word) {
        return switch (word) {
        case "tdy", "today" -> Alias.TODAY;
        case "tmw", "tmrw", "tomorrow" -> Alias.TOMORROW;
        case "mon", "monday" -> Alias.MONDAY;
        case "tue", "tues", "tuesday" -> Alias.TUESDAY;
        case "wed", "wednesday" -> Alias.WEDNESDAY;
        case "thu", "thur", "thurs", "thursday" -> Alias.THURSDAY;
        case "fri", "friday" -> Alias.FRIDAY;
        case "sat", "saturday" -> Alias.SATURDAY;
        case "sun", "sunday" -> Alias.SUNDAY;
        default -> null;
        };
    }

    private static LocalDate parseAlias(Alias alias) {
        return switch (alias) {
        case TODAY -> LocalDate.now();
        case TOMORROW -> LocalDate.now().plusDays(1);
        case MONDAY -> LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        case TUESDAY -> LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        case WEDNESDAY -> LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
        case THURSDAY -> LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        case FRIDAY -> LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        case SATURDAY -> LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        case SUNDAY -> LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        };
    }

    /**
     * Takes in the full line user enters and returns the String form of the dates entered.
     *
     * @param line Full String command entered by user.
     * @return String array containing dates entered by user.
     */
    private static String[] getDatesUi(String line) {
        String[] splitBySlash = line.split(" /");
        String[] dates = new String[splitBySlash.length - 1];
        System.arraycopy(splitBySlash, 1, dates, 0, splitBySlash.length - 1);
        return dates;
    }

    /**
     * Takes in a fully parsed due date entered by user and checks if due date has passed.
     *
     * @param dueDate Proposed due date entered by user.
     * @throws IllegalArgumentException If due date has passed current date.
     */
    private static void checkDueDatePassedUi(LocalDate dueDate) throws IllegalArgumentException {
        if (dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(ERROR_DEADLINE_DATE_PASSED);
        }
    }

    /**
     * Takes in the full line user enters and checks whether the due date has been entered.
     *
     * @param line Full String command entered by user.
     * @throws ParseException If due date is missing in deadline entry.
     */
    private static void checkDueDateExistsUi(String line) throws ParseException {
        String[] dates = getDatesUi(line);
        if (dates.length < 1) {
            throw new ParseException(ERROR_DEADLINE_DATE_MISSING, 0);
        }
    }

    private static LocalDate parseDueDateWithAlias(String[] dates) throws DateTimeParseException {
        if (alias(dates[0].toLowerCase()) == null) {
            return LocalDate.parse(dates[0], FORMATTER_DUE_DATE);
        }
        assert alias(dates[0].toLowerCase()) != null : "Code should not reach here if no aliasing resulted.";
        return parseAlias(alias(dates[0].toLowerCase()));
    }

    /**
     * Takes in the full line user enters and returns the due date specified by the user in
     * LocalDate form.
     *
     * @param fullCommand Full String command entered by user.
     * @return Due date specified by user in LocalDate form.
     * @throws ParseException If due date is missing in deadline entry.
     * @throws DueDateParseException If due date is not entered in yyyy-mm-dd format
     *                               or aligned with alias for a date.
     * @throws IllegalArgumentException If due date has passed current date.
     */
    public static LocalDate parseDueDateUi(String fullCommand)
            throws ParseException, DueDateParseException, IllegalArgumentException {
        try {
            checkDueDateExistsUi(fullCommand);
            String[] dates = getDatesUi(fullCommand);
            LocalDate dueDate = parseDueDateWithAlias(dates);
            checkDueDatePassedUi(dueDate);
            return dueDate;
        } catch (DateTimeParseException e) {
            throw new DueDateParseException(ERROR_DEADLINE_DATE_WRONG_FORMAT, e.getParsedString(), e.getErrorIndex());
        }
    }

    /**
     * Takes in a fully parsed due date from tasks saved on a file
     * and checks if due date has passed.
     *
     * @param dueDate Due date of deadline outlined in file.
     * @throws IllegalArgumentException If due date has passed current date.
     */
    private static void checkDueDatePassedFile(LocalDate dueDate) throws IllegalArgumentException {
        if (dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(MESSAGE_DELETING_PAST_DEADLINE);
        }
    }

    /**
     * Takes in a String representing a due date for a deadline saved on a file,
     * and returns the due date in LocalDate form.
     *
     * @param dueDateString String form of due date of deadline outlined in file.
     * @return LocalDate form of Due date of deadline outlined in file.
     * @throws DueDateParseException If due date is encoded in an incorrect format.
     * @throws IllegalArgumentException If due date has passed current date.
     */
    public static LocalDate parseDueDateFile(String dueDateString)
            throws DueDateParseException, IllegalArgumentException {
        try {
            LocalDate dueDate = LocalDate.parse(dueDateString, FORMATTER_DUE_DATE);
            checkDueDatePassedFile(dueDate);
            return dueDate;
        } catch (DateTimeParseException e) {
            throw new DueDateParseException(ERROR_DEADLINE_DATE_WRONG_FORMAT, e.getParsedString(), e.getErrorIndex());
        }
    }

    /**
     * Checks whether entered startTime is before entered endTime.
     *
     * @param startTime LocalDateTime object representing the start of an event.
     * @param endTime LocalDateTime object representing the end of an event.
     * @throws IllegalArgumentException If end time comes before start time.
     */
    private static void checkEventTimesChronological(LocalDateTime startTime, LocalDateTime endTime)
            throws IllegalArgumentException {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException(ERROR_EVENT_TIMES_INORDERED);
        }
    }

    /**
     * Verifies whether an event has passed based on startTime and endTime.
     *
     * @param startTime LocalDateTime object representing the start of an event.
     * @param endTime LocalDateTime object representing the end of an event.
     * @throws IllegalArgumentException If the event has already concluded.
     */
    private static void checkEventPassedByUi(LocalDateTime startTime, LocalDateTime endTime)
            throws IllegalArgumentException {
        if (startTime.isBefore(LocalDateTime.now()) && endTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(ERROR_EVENT_TIME_PASSED);
        }
    }

    /**
     * Takes in the full line user enters and checks whether the event
     * start time and end time has been entered.
     *
     * @param line Full String command entered by user.
     * @throws ParseException If start time or end time is missing in event entry.
     */
    private static void checkEventTimesExistUi(String line) throws ParseException {
        String[] dates = getDatesUi(line);
        if (dates.length < 2) {
            throw new ParseException(ERROR_EVENT_TIME_MISSING, 0);
        }
    }

    private static LocalDateTime[] parseEventTimesWithAlias(String[] dates) throws DateTimeParseException {
        String startDateString = dates[0].split(" ")[0];
        LocalDateTime startTime;
        if (alias(startDateString) == null) {
            startTime = LocalDateTime.parse(dates[0], FORMATTER_EVENT_TIME);
        } else {
            assert alias(startDateString) != null : "Code should not reach here if no aliasing resulted.";
            startTime = LocalDateTime.of(parseAlias(alias(startDateString)),
                    LocalTime.parse(dates[0].split(" ")[1], DateTimeFormatter.ofPattern("HHmm")));
        }

        String endDateString = dates[1].split(" ")[0];
        LocalDateTime endTime;
        if (alias(endDateString) == null) {
            endTime = LocalDateTime.parse(dates[1], FORMATTER_EVENT_TIME);
        } else {
            assert alias(endDateString) != null : "Code should not reach here if no aliasing resulted.";
            endTime = LocalDateTime.of(parseAlias(alias(endDateString)),
                    LocalTime.parse(dates[1].split(" ")[1], DateTimeFormatter.ofPattern("HHmm")));
        }

        return new LocalDateTime[] {startTime, endTime};
    }

    /**
     * Takes in the full line user enters and returns an event's start time and end time
     * specified by the user in an LocalDateTime array {startTime, endTime}.
     *
     * @param fullCommand Full String command entered by user.
     * @return Event start time and end time specified by user in a LocalDateTime array.
     * @throws ParseException If event start time or end time is missing in deadline entry.
     * @throws EventDateTimeParseException If event time is not entered in accepted formats.
     * @throws IllegalArgumentException If event has concluded by the current date.
     */
    public static LocalDateTime[] parseEventTimesUi(String fullCommand)
            throws ParseException, EventDateTimeParseException, IllegalArgumentException {
        try {
            checkEventTimesExistUi(fullCommand);
            String[] dates = getDatesUi(fullCommand);
            LocalDateTime[] eventTimes = parseEventTimesWithAlias(dates);
            checkEventTimesChronological(eventTimes[0], eventTimes[1]);
            checkEventPassedByUi(eventTimes[0], eventTimes[1]);
            return eventTimes;
        } catch (DateTimeParseException e) {
            throw new EventDateTimeParseException(ERROR_EVENT_TIME_WRONG_FORMAT,
                    e.getParsedString(), e.getErrorIndex());
        }
    }

    /**
     * Takes in fully parsed event start time and end time from tasks saved on a file
     * and checks if event has concluded.
     *
     * @param startTime Start time of event outlined in file.
     * @param endTime End time of event outlined in file.
     * @throws IllegalArgumentException If event has already concluded.
     */
    private static void checkEventPassedByFile(LocalDateTime startTime, LocalDateTime endTime)
            throws IllegalArgumentException {
        if (startTime.isBefore(LocalDateTime.now()) && endTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(MESSAGE_DELETING_PAST_EVENT);
        }
    }

    /**
     * Takes in Strings representing a start time and end time for a deadline saved on a file,
     * and returns them in a LocalDateTime array.
     *
     * @param startTimeString String form of event start time outlined in file.
     * @param endTimeString String form of event end time outlined in file.
     * @return LocalDateTime array of event start time and end time outlined in file.
     * @throws DueDateParseException If any event times are encoded in an incorrect format.
     * @throws IllegalArgumentException If event has already concluded.
     */
    public static LocalDateTime[] parseEventTimesFile(String startTimeString, String endTimeString)
            throws EventDateTimeParseException, IllegalArgumentException {
        try {
            LocalDateTime startTime = LocalDateTime.parse(startTimeString, FORMATTER_EVENT_TIME);
            LocalDateTime endTime = LocalDateTime.parse(endTimeString, FORMATTER_EVENT_TIME);
            checkEventTimesChronological(startTime, endTime);
            checkEventPassedByFile(startTime, endTime);
            return new LocalDateTime[] {startTime, endTime};
        } catch (DateTimeParseException e) {
            throw new EventDateTimeParseException(ERROR_EVENT_TIME_WRONG_FORMAT,
                    e.getParsedString(), e.getErrorIndex());
        }
    }

    /**
     * Takes in the full line user enters and checks whether the search date has been entered.
     *
     * @param line Full String command entered by user.
     * @throws ParseException If search date is missing in happening search.
     */
    private static void checkHappeningDateExists(String line) throws ParseException {
        String[] dates = getDatesUi(line);
        if (dates.length < 1) {
            throw new ParseException(ERROR_HAPPENING_DATE_MISSING, 0);
        }
    }

    private static LocalDate parseHappeningDateWithAlias(String[] dates) throws DateTimeParseException {
        if (alias(dates[0].toLowerCase()) == null) {
            return LocalDate.parse(dates[0], FORMATTER_DUE_DATE);
        }
        assert alias(dates[0].toLowerCase()) != null : "Code should not reach here if no aliasing resulted.";
        return parseAlias(alias(dates[0].toLowerCase()));
    }

    /**
     * Takes in the full line user enters and returns the search date user is looking up tasks for
     * in LocalDate form.
     *
     * @param fullCommand Full String command entered by user.
     * @return LocalDate representation of user-entered search date for tasks.
     * @throws ParseException If search date not entered by user.
     * @throws DateTimeParseException If search date not in 'yyyy-mm-dd' format.
     */
    public static LocalDate parseHappeningDate(String fullCommand) throws ParseException, DateTimeParseException {
        String[] dates = getDatesUi(fullCommand);
        try {
            checkHappeningDateExists(fullCommand);
            return parseHappeningDateWithAlias(dates);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException(ERROR_HAPPENING_DATE_WRONG_FORMAT,
                    e.getParsedString(), e.getErrorIndex());
        }
    }
}
