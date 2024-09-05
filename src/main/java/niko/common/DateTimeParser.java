package niko.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import niko.main.Ui;
import niko.task.Deadline;
import niko.task.Event;
import niko.task.Task;
import niko.task.TaskList;


/**
 * A utility class that provides methods for parsing date and time strings,
 * and for searching tasks based on dates.
 */
public class DateTimeParser {

    private static final List<DateTimeFormatter> dateTimeFormatters = new ArrayList<>();
    private static final List<DateTimeFormatter> dateOnlyFormatters = new ArrayList<>();
    private static final Ui ui = new Ui();

    static {
        // Initialize various date and time formatters.
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a", Locale.ENGLISH));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("dd.MM.yyyy HHmm"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("d/M/yyyy h:mm a"));
        dateTimeFormatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a"));

        dateOnlyFormatters.add(DateTimeFormatter.ofPattern("d/M/yyyy"));
        dateOnlyFormatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dateOnlyFormatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dateOnlyFormatters.add(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        dateOnlyFormatters.add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        dateOnlyFormatters.add(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    /**
     * Constructs a DateTimeParser object.
     */
    public DateTimeParser() {}

    /**
     * Searches for tasks in the given TaskList that match the specified date.
     * The date can be a full date, a year and month, or just a year.
     *
     * @param date The date string to search for.
     * @param taskList The list of tasks to search through.
     */
    public String searchTasks(String date, TaskList taskList) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        DateTimeFormatter fullDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter yearMonthFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
        String response;

        LocalDateTime fullDate = null;
        YearMonth yearMonth = null;
        Year year = null;
        boolean isFullDate = false;
        boolean isYearMonth = false;
        boolean isYear = false;

        try {
            fullDate = LocalDateTime.parse(date, fullDateFormatter);
            isFullDate = true;
        } catch (DateTimeParseException ignored) {
            // Ignored - Continue to try parsing as yearMonth or year
        }

        if (!isFullDate) {
            try {
                yearMonth = YearMonth.parse(date, yearMonthFormatter);
                isYearMonth = true;
            } catch (DateTimeParseException ignored) {
                // Ignored - Continue to try parsing as year
            }
        }

        if (!isFullDate && !isYearMonth) {
            try {
                year = Year.parse(date, yearFormatter);
                isYear = true;
            } catch (DateTimeParseException ignored) {
                // Ignored - No further parsing possible
            }
        }

        for (Task task : taskList.getTasks()) {
            if (task instanceof Deadline) {
                LocalDateTime taskDateTime = ((Deadline) task).getBy();
                compareTime(matchingTasks, fullDate, yearMonth, year,
                        isFullDate, isYearMonth, isYear, task, taskDateTime);
            } else if (task instanceof Event) {
                LocalDateTime taskStartDate = ((Event) task).getStart();
                compareTime(matchingTasks, fullDate, yearMonth, year,
                        isFullDate, isYearMonth, isYear, task, taskStartDate);
            }
        }

        if (!matchingTasks.isEmpty()) {
            response = ui.showTaskList(matchingTasks);
        } else {
            response = ui.showNoMatchingTasksMessage();
        }
        return response;
    }

    /**
     * Compares the given task date and time against the specified date/time parts,
     * and adds the task to the matchingTasks list if there is a match.
     *
     * @param matchingTasks The list to add matching tasks to.
     * @param fullDate The full date to compare against.
     * @param yearMonth The year and month to compare against.
     * @param year The year to compare against.
     * @param isFullDate Whether the input date was parsed as a full date.
     * @param isYearMonth Whether the input date was parsed as a year and month.
     * @param isYear Whether the input date was parsed as a year.
     * @param task The task to check.
     * @param taskDateTime The date and time of the task.
     */
    private void compareTime(ArrayList<Task> matchingTasks, LocalDateTime fullDate,
                             YearMonth yearMonth, Year year, boolean isFullDate, boolean isYearMonth,
                             boolean isYear, Task task, LocalDateTime taskDateTime) {
        if (isFullDate && taskDateTime.toLocalDate().equals(fullDate.toLocalDate())) {
            matchingTasks.add(task);
        } else if (isYearMonth && YearMonth.from(taskDateTime).equals(yearMonth)) {
            matchingTasks.add(task);
        } else if (isYear && Year.from(taskDateTime).equals(year)) {
            matchingTasks.add(task);
        }
    }

    /**
     * Parses the given date and time string into a LocalDateTime object.
     *
     * @param dateTimeString The date and time string to parse.
     * @return The parsed LocalDateTime object.
     * @throws DateTimeParseException If the date and time string cannot be parsed.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        for (DateTimeFormatter formatter : dateTimeFormatters) {
            try {
                return LocalDateTime.parse(dateTimeString, formatter);
            } catch (DateTimeParseException ignored) {
                // Ignored - Continue to the next formatter
            }
        }

        for (DateTimeFormatter formatter : dateOnlyFormatters) {
            try {
                LocalDate date = LocalDate.parse(dateTimeString, formatter);
                return date.atTime(LocalTime.of(23, 59, 59));
            } catch (DateTimeParseException ignored) {
                // Ignored - Continue to the next formatter
            }
        }

        throw new DateTimeParseException("Unable to parse date: " + dateTimeString, dateTimeString, 0);
    }

    /**
     * Converts a given date string into a LocalDateTime object using specific formats.
     *
     * @param inputDate The date string to convert.
     * @return The converted LocalDateTime object.
     * @throws DateTimeParseException If the date string cannot be parsed.
     */
    public static LocalDateTime convertDate(String inputDate) {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a", Locale.ENGLISH);
            return LocalDateTime.parse(inputDate, dateTimeFormatter);
        } catch (DateTimeParseException ignored) {
            // Ignored - Try parsing date only
        }

        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(inputDate, dateFormatter);
            return date.atStartOfDay();
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Unable to parse date: " + inputDate, inputDate, 0);
        }
    }
}
