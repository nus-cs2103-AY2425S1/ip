package bigdog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The {@code Event} class represents a task with a start and end date/time.
 * It extends the {@code Task} class and includes additional information about the event's schedule.
 */
public class Event extends Task {

    /** Indicates whether the event includes specific start and end times or just dates. */
    private static boolean withTime;

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
        if (s.length() <= 6) {
            throw new BigdogException("event can't be empty! If theres no event then go and sleep!");
        }

        int dashCounter = 0;
        for (int j = s.length() - 1; j > 5; j--) {
            if (s.charAt(j) == '/') {
                dashCounter++;
            }
            if (s.charAt(j) == '/' && dashCounter == 3) {
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '/') {
                        return new Event(s.substring(0, i - 1), stringToDate(s.substring(i + 6, j - 1)),
                                stringToDate(s.substring(j + 4)), false);
                    }
                }
            }
        }
        throw new BigdogException("Event has to have a start and end!");
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


        if (s.length() <= 4) {
            throw new BigdogException("data file corrupted! Cause: " + s);
        }

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
        long dashes = str.chars().filter(x -> x == '/').count();
        if (dashes != 2) {
            throw new BigdogException("Invalid date format: " + str);
        } else {
            String[] getTime = str.split(" ");
            String[] getDate = getTime[0].split("/");
            String year = getDate[2];
            String month = getDate[1];
            String day = getDate[0];

            try {
                if (getTime.length == 2) {
                    withTime = true;
                    return LocalDateTime.parse(String.format("%s-%s-%sT%s", year, month, day, getTime[1]));
                } else if (getTime.length == 1) {
                    withTime = false;
                    return LocalDateTime.parse(String.format("%s-%s-%sT%s", year, month, day, "23:59"));
                } else {
                    throw new BigdogException("Invalid date format: " + str
                            + "\nExample correct format: event meeting with John "
                            + "/from 02/07/2019 18:00 /to 02/07/2019 20:00");
                }
            } catch (DateTimeParseException e) {
                throw new BigdogException("Invalid date format: " + str
                        + "\nExample correct format: event meeting with John /from 02/07/2019 18:00 "
                        + "/to 02/07/2019 20:00");
            }
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
     * Returns a string representation of the Event task.
     * The format includes the task type, status, description, and start and end times.
     *
     * @return a formatted string representing the event task.
     */
    @Override
    public String toString() {
        if (withTime) {
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
