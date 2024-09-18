package bruno;

import bruno.command.*;
import bruno.exceptions.BrunoException;
import bruno.exceptions.UnknownCommandException;
import bruno.task.TaskList;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

/**
 * The Parser class is responsible for interpreting user input and
 * converting it into commands for execution.
 * It takes in raw input from the user, identifies the type of command,
 * and generates a Command object based on that input.
 */
public class Parser {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Parses the given user command string and returns the corresponding Command object.
     *
     * @param command The raw input string from the user.
     * @param tasks The TaskList containing the user's tasks, used to apply the command.
     * @return A Command object corresponding to the parsed command, or null if an exception occurs.
     */
    public static Command parse(String command, TaskList tasks) throws BrunoException {
        assert command != null : "Command is null";

        command = command.trim();

        String[] parts = command.split(" ", 2);
        String firstWord = parts[0];
        String restOfString = parts.length > 1 ? parts[1] : "";

        if (command.equalsIgnoreCase("bye")) {
            return new ExitCommand(tasks);
        } else if (command.equalsIgnoreCase("list")) {
            return new ListCommand(tasks);
        } else if (firstWord.equalsIgnoreCase("mark")) {
            String[] taskNums = restOfString.split(" ");
            return new MarkCommand(tasks, taskNums);
        } else if (firstWord.equalsIgnoreCase("unmark")) {
            String[] taskNums = restOfString.split(" ");
            return new UnmarkCommand(tasks, taskNums);
        } else if (firstWord.equalsIgnoreCase("delete")) {
            String[] taskNums = restOfString.split(" ");
            return new DeleteCommand(tasks, taskNums);
        } else if (firstWord.equalsIgnoreCase("todo")) {
            return new AddCommand(tasks, restOfString, Bruno.TaskType.TODO);
        } else if (firstWord.equalsIgnoreCase("deadline")) {
            return new AddCommand(tasks, restOfString, Bruno.TaskType.DEADLINE);
        } else if (firstWord.equalsIgnoreCase("event")) {
            return new AddCommand(tasks, restOfString, Bruno.TaskType.EVENT);
        } else if (firstWord.equalsIgnoreCase("find")) {
            return new FindCommand(tasks, restOfString);
        } else {
            return new UnknownCommand(tasks);
        }
    }

    public static LocalDateTime parseNaturalDateTime(String input) throws BrunoException {
        LocalDate date;
        LocalTime time = LocalTime.of(9, 0); // Default time if none provided (9:00 AM)
        String[] parts = input.split(" ");

        try {
            date = parseManualDate(parts[0]);
        } catch (DateTimeParseException e) {
            date = parseNaturalDate(parts[0]);
        }

        if (parts.length > 1) {
            time = parseTime(parts[1]);
        }

        return LocalDateTime.of(date, time);
    }

    private static LocalDate parseManualDate(String input) {
        return LocalDate.parse(input, formatter);
    }

    private static LocalTime parseTime(String timeInput) {
        timeInput = timeInput.toLowerCase().replace(" ", "");
        switch (timeInput) {
        case "noon":
            return LocalTime.NOON;
        case "midnight":
            return LocalTime.MIDNIGHT;
        default:
            if (timeInput.endsWith("am") || timeInput.endsWith("pm")) {
                return parse12HourTime(timeInput);
            } else {
                return parse24HourTime(timeInput);
            }
        }
    }

    private static LocalTime parse12HourTime(String timeInput) {
        timeInput = timeInput.toLowerCase();

        boolean isPm = timeInput.contains("pm");
        boolean isAm = timeInput.contains("am");

        timeInput = timeInput.replace("am", "").replace("pm", "").trim();
        String[] parts = timeInput.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = (parts.length > 1) ? Integer.parseInt(parts[1]) : 0;
        if (isPm && hour != 12) {
            hour += 12;
        }
        if (isAm && hour == 12) {
            hour = 0;
        }
        return LocalTime.of(hour, minute);
    }

    private static LocalTime parse24HourTime(String timeInput) {
        String[] parts = timeInput.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = (parts.length > 1) ? Integer.parseInt(parts[1]) : 0;
        return LocalTime.of(hour, minute);
    }

    public static LocalDate parseNaturalDate(String input) throws BrunoException{
        LocalDate today = LocalDate.now();

        switch (input.toLowerCase()) {
        case "mon":
        case "monday":
            return getNextDay(today, DayOfWeek.MONDAY);
        case "tue":
        case "tuesday":
            return getNextDay(today, DayOfWeek.TUESDAY);
        case "wed":
        case "wednesday":
            return getNextDay(today, DayOfWeek.WEDNESDAY);
        case "thu":
        case "thursday":
            return getNextDay(today, DayOfWeek.THURSDAY);
        case "fri":
        case "friday":
            return getNextDay(today, DayOfWeek.FRIDAY);
        case "sat":
        case "saturday":
            return getNextDay(today, DayOfWeek.SATURDAY);
        case "sun":
        case "sunday":
            return getNextDay(today, DayOfWeek.SUNDAY);
        case "tomorrow":
            return today.plusDays(1);
        case "nextweek":
            return today.plusWeeks(1);
        case "today":
            return today;
        default:
            throw new BrunoException("Invalid date format.");
        }
    }

    private static LocalDate getNextDay(LocalDate today, DayOfWeek dayOfWeek) {
        return today.with(TemporalAdjusters.nextOrSame(dayOfWeek));
    }
}
