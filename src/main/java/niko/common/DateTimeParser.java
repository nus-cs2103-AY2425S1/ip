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
import java.util.stream.Collectors;

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
        initializeFormatters();
    }

    /**
     * Initializes the date and time formatters used for parsing.
     */
    private static void initializeFormatters() {
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

        dateOnlyFormatters.add(DateTimeFormatter.ofPattern("d/M/yyyy"));
        dateOnlyFormatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dateOnlyFormatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dateOnlyFormatters.add(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        dateOnlyFormatters.add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        dateOnlyFormatters.add(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    /**
     * Searches for tasks in the given TaskList that match the specified date.
     *
     * @param date     The date string to search for.
     * @param taskList The list of tasks to search through.
     * @return The search result message.
     */
    public String searchTasks(String date, TaskList taskList) {
        ParsedDate parsedDate = parseDate(date);

        ArrayList<Task> matchingTasks = taskList.getTasks().stream()
                .filter(task -> task instanceof Deadline || task instanceof Event)
                .filter(task -> {
                    LocalDateTime taskDateTime = getTaskDateTime(task);
                    return compareTime(parsedDate, taskDateTime);
                })
                .collect(Collectors.toCollection(ArrayList::new));

        return matchingTasks.isEmpty() ? ui.showNoMatchingTasksMessage() : ui.showTaskList(matchingTasks);
    }

    /**
     * Parses the given date string into a ParsedDate object.
     *
     * @param date The date string to parse.
     * @return The parsed date as a ParsedDate object.
     */
    private ParsedDate parseDate(String date) {
        DateTimeFormatter fullDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter yearMonthFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");

        ParsedDate parsedDate = new ParsedDate();

        try {
            parsedDate.fullDate = LocalDateTime.parse(date, fullDateFormatter);
            parsedDate.isFullDate = true;
        } catch (DateTimeParseException ignored) {
        }

        if (!parsedDate.isFullDate) {
            try {
                parsedDate.yearMonth = YearMonth.parse(date, yearMonthFormatter);
                parsedDate.isYearMonth = true;
            } catch (DateTimeParseException ignored) {
            }
        }

        if (!parsedDate.isFullDate && !parsedDate.isYearMonth) {
            try {
                parsedDate.year = Year.parse(date, yearFormatter);
                parsedDate.isYear = true;
            } catch (DateTimeParseException ignored) {
            }
        }

        return parsedDate;
    }

    /**
     * Retrieves the LocalDateTime of the given task.
     *
     * @param task The task to retrieve the date and time from.
     * @return The LocalDateTime of the task, or null if the task is not a Deadline or Event.
     */
    private LocalDateTime getTaskDateTime(Task task) {
        if (task instanceof Deadline) {
            return ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            return ((Event) task).getStart();
        }
        return null;
    }

    /**
     * Compares the parsed date with the task date time.
     *
     * @param parsedDate    The parsed date to compare.
     * @param taskDateTime The task date time to compare.
     * @return True if the dates match based on the parsed date type, false otherwise.
     */
    private boolean compareTime(ParsedDate parsedDate, LocalDateTime taskDateTime) {
        if (parsedDate.isFullDate) {
            return taskDateTime.toLocalDate().isEqual(parsedDate.fullDate.toLocalDate());
        } else if (parsedDate.isYearMonth) {
            return YearMonth.from(taskDateTime).equals(parsedDate.yearMonth);
        } else if (parsedDate.isYear) {
            return Year.from(taskDateTime).equals(parsedDate.year);
        }
        return false;
    }

    /**
     * Parses the given date time string into a LocalDateTime object.
     *
     * @param dateTimeString The date time string to parse.
     * @return The parsed LocalDateTime object.
     * @throws DateTimeParseException If the string cannot be parsed into a valid date time.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        for (DateTimeFormatter formatter : dateTimeFormatters) {
            try {
                return LocalDateTime.parse(dateTimeString, formatter);
            } catch (DateTimeParseException ignored) {
            }
        }

        for (DateTimeFormatter formatter : dateOnlyFormatters) {
            try {
                LocalDate date = LocalDate.parse(dateTimeString, formatter);
                return date.atTime(LocalTime.of(23, 59, 59));
            } catch (DateTimeParseException ignored) {
            }
        }

        throw new DateTimeParseException("Unable to parse date: " + dateTimeString, dateTimeString, 0);
    }

    /**
     * A private inner class representing a parsed date.
     */
    private static class ParsedDate {
        LocalDateTime fullDate;
        YearMonth yearMonth;
        Year year;
        boolean isFullDate = false;
        boolean isYearMonth = false;
        boolean isYear = false;
    }
}