package bigdog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The {@code Event} class represents a task with a start and end date/time.
 * It extends the {@code Task} class and includes additional information about the event's schedule.
 */
public class Event extends Task {

    private static final int MIN_LENGTH_FOR_EMPTY_CHECK = 6;
    private static final int MIN_LENGTH_FOR_CORRUPTION_CHECK = 4;
    private static final int MIN_DASH_COUNT = 3;

    /** Indicates whether the event includes specific start and end times or just dates. */
    private static boolean isTimeIncluded;

    /** The start date and time of the event. */
    private LocalDateTime start;

    /** The end date and time of the event. */
    private LocalDateTime end;

    /**
     * Private constructor for creating an Event instance.
     *
     * @param str the description of the event.
     * @param start the start date and time of the event.
     * @param end the end date and time of the event.
     * @param marked whether the event is marked as done.
     */
    private Event(String str, LocalDateTime start, LocalDateTime end, boolean marked) {
        super(str, marked);
        this.start = start;
        this.end = end;
    }

    /**
     * Factory method that creates an Event object from a string input.
     * The input string should contain the event description followed by the start and end date/time (optional).
     *
     * @param s the input string containing the event description, start, and end times.
     * @return a new Event instance.
     * @throws BigdogException if the input string is empty or does not contain valid dates.
     */
    public static Event of(String s) throws BigdogException {

        assert s.length() > MIN_LENGTH_FOR_EMPTY_CHECK : "event can't be empty! If theres no event then go and sleep!";

        String[] eventParts = s.split("/from");
        if (eventParts.length < 2) {
            throw new BigdogException("Event has to have a start and end!");
        }

        String[] startAndEndDate = eventParts[1].split("/to");
        if (startAndEndDate.length < 2 || startAndEndDate[0].isEmpty() || startAndEndDate[1].isEmpty()) {
            throw new BigdogException("Event has to have a start and end!");
        }

        return new Event(eventParts[0].trim(), stringToDate(startAndEndDate[0].trim()),
                stringToDate(startAndEndDate[1].trim()), false);
    }

    /**
     * Factory method that creates an Event object from a string input and a marked status.
     * This method is typically used when loading events from a file.
     *
     * @param s the input string containing the event description, start, and end times.
     * @param marked whether the event is marked as done.
     * @return a new Event instance.
     * @throws BigdogException if the input string is corrupted or invalid.
     */
    public static Event of(String s, boolean marked) throws BigdogException {

        assert s.length() > MIN_LENGTH_FOR_CORRUPTION_CHECK : "data file corrupted! Cause: " + s;

        for (int j = s.length() - 1; j > 3; j--) {
            if (s.charAt(j) != '|') {
                continue;
            }
            LocalDateTime end = LocalDateTime.parse(s.substring(j + 2));
            for (int i = 5; i < s.length(); i++) {
                if (s.charAt(i) == '|') {
                    return new Event(s.substring(4, i - 1), LocalDateTime.parse(s.substring(i + 2, j - 1)),
                            end, marked);
                }
            }

        }
        throw new BigdogException("data file corrupted! Cause: " + s);
    }

    /**
     * Converts a date string into a LocalDateTime object.
     * The input string should follow the format "dd/MM/yyyy HH:mm" or "dd/MM/yyyy".
     *
     * @param str the input string representing the date and time.
     * @return the corresponding LocalDateTime object.
     * @throws BigdogException if the input string has an invalid format.
     */
    private static LocalDateTime stringToDate(String str) throws BigdogException {

        assert str.chars().filter(x -> x == '/').count() == 2 : "Invalid date format: " + str;

        String[] parts = str.split(" ");
        String[] dateParts = parts[0].split("/");
        String year = dateParts[2];
        String month = dateParts[1];
        String day = dateParts[0];
        String time = (parts.length == 2) ? parts[1] : "00:00";

        try {
            isTimeIncluded = !time.equals("00:00");
            return LocalDateTime.parse(String.format("%s-%s-%sT%s", year, month, day, time));
        } catch (DateTimeParseException e) {
            throw new BigdogException("Invalid date format: " + str
                    + "\nExample correct format: event meeting with John /from 02/07/2019 18:00 /to 02/07/2019 20:00");
        }

    }

    /**
     * Returns a string representing the event description along with its start and end times.
     *
     * @return the description of the event with the start and end times included.
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " | " + this.start + " | " + this.end;
    }

    /**
     * Checks if the task occurs within a specified date range and is unmarked.
     * This method determines whether the task is unmarked and the given date falls
     * within the task's start and end dates, inclusive. It returns true if the task
     * is not marked as done, the date is on or after the start date, and the date
     * is on or before the end date.
     *
     * @param date The LocalDateTime object representing the date to check.
     * @return true if the task is unmarked and the date is within the start and
     *         end date range, inclusive. Returns false otherwise.
     */
    @Override
    public boolean isOnDay(LocalDateTime date) {
        boolean isEqualStart = date.toLocalDate().isEqual(start.toLocalDate());
        boolean isEqualEnd = date.toLocalDate().isEqual(end.toLocalDate());
        boolean isBetweenStartAndEnd = date.isAfter(start) && date.isBefore(end);
        return !this.isMarked() && isEqualStart || isEqualEnd || isBetweenStartAndEnd;
    }

    /**
     * Returns a string representation of the Event task.
     * The format includes the task type, status, description, and start and end times.
     *
     * @return a formatted string representing the event task.
     */
    @Override
    public String toString() {
        if (isTimeIncluded) {
            return "[E]" + super.toString() + " (from: "
                    + this.start.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + " to: "
                    + this.end.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
        } else {
            return "[E]" + super.toString() + " (from: "
                    + this.start.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " to: "
                    + this.end.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
        }
    }

}
