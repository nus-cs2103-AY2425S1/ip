package parser;

import command.Command;
import task.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a parser that parses the user input and converts it into a task object.
 * The parser also converts the date and time strings into LocalDate and LocalTime objects.
 */
public class Parser {
    /**
     * Parses the user input and returns a task object.
     *
     * @param taskType The type of task to be created.
     * @param command The command object containing the user input.
     * @return The task object created from the user input.
     * @throws InvalidTaskException If the user input is invalid.
     */
    public static Task parseTask(Task.TYPE taskType, Command command) throws InvalidTaskException {
        try {
            switch (taskType) {
                case TODO: {
                    try {
                        String description = command.getDescription();
                        return new ToDoTask(description, false);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new InvalidTaskException("OOPS!!! The description of a todo cannot be empty.");
                    }
                }
                case DEADLINE: {
                    try {
                        String description = command.getDescription();
                        String date = command.getDate();

                        return new DeadlineTask(description, false, date);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new InvalidTaskException("OOPS!!! The description or by of a deadline cannot be empty.");
                    }
                }
                case EVENT: {
                    try {
                        String description = command.getDescription();
                        String date = command.getDate();
                        String startTime = command.getStartTime();
                        String endTime = command.getEndTime();

                        return new EventTask(description, false, date, startTime, endTime);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new InvalidTaskException("OOPS!!! The description, date, start time or "
                                + "end time of an event cannot be empty.");
                    }
                }
                default:
                    throw new InvalidTaskException("OOPS!!! I'm sorry, but I don't what task is this. :-(");
            }
        } catch (InvalidDateException e) {
            throw new InvalidTaskException("OOPS!!! Please enter a valid "
                    + "date and time in the format dd-mm-yyyy.");
        }
        catch (InvalidTimeException e) {
            throw new InvalidTaskException("OOPS!!! Please enter a valid "
                    + "time in the format HHmm.");
        }
    }

    /**
     * Parse the user input and returns a LocalDate object.
     * @param date The date string to be parsed.
     * @return The LocalDate object created from the date string.
     * @throws InvalidDateException If the date string is invalid.
     */
    public static LocalDate parseDate(String date) throws InvalidDateException {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (Exception e) {
            throw new InvalidDateException("Invalid date format. Please use the format dd-MM-yyyy.");
        }
    }

    /**
     * Parse the LocalDate object to a nice format string.
     * @param date The LocalDate object to be parsed.
     * @return The string representation of the LocalDate object.
     */
    public static String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Parse the LocalDate object to a storage format string.
     * @param date The LocalDate object to be parsed.
     * @return The string representation of the LocalDate object.
     */
    public static String dateToStorageString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Parse the LocalTime object to a storage format string.
     * @param time The LocalTime object to be parsed.
     * @return The string representation of the LocalTime object.
     */
    public static String timeToStorageString(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    /**
     * Parse the LocalTime object to a nice format string.
     * @param time The LocalTime object to be parsed.
     * @return The string representation of the LocalTime object.
     */
    public static String timeToString(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    /**
     * Parse the user input and returns a LocalTime object.
     * @param time The time string to be parsed.
     * @return The LocalTime object created from the time string.
     * @throws InvalidTimeException If the time string is invalid.
     */
    public static LocalTime parseTime(String time) throws InvalidTimeException {
        try {
            return LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmm"));
        } catch (Exception e) {
            throw new InvalidTimeException("Invalid time format. Please use the format HHmm.");
        }
    }
}
