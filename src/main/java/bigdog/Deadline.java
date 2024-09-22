package bigdog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The {@code Deadline} class represents a task with a specific deadline.
 * It extends the {@code Task} class and includes methods for managing the deadline date and time.
 */
public class Deadline extends Task {

    /** Indicates whether the deadline includes a specific end time or just a date. */
    private static boolean withTime;

    /** The end date and time */
    private LocalDateTime end;

    private static final int TASK_DESC_START_INDEX = 5;
    private static final int MIN_LENGTH_FOR_CORRUPTION_CHECK = 4;

    /**
     * Private constructor for creating a {@code Deadline} instance.
     *
     * @param str the description of the task.
     * @param end the deadline date and time.
     * @param marked whether the task is marked as done.
     */
    private Deadline(String str, LocalDateTime end, boolean marked) {
        super(str, marked);
        this.end = end;
    }

    /**
     * Factory method that creates a code Deadline object from a string input.
     * The input string should contain the task description followed by the deadline date/time (optional).
     *
     * @param s the input string containing the task description and deadline.
     * @return a new Deadline instance.
     * @throws BigdogException if the input string is empty or does not contain a valid deadline.
     */
    public static Deadline of(String s) throws BigdogException {

        assert (!s.isEmpty() && s.charAt(0) != '/') : "deadline can't be empty! Theres nothing to do!\n";

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '/') {
                return new Deadline(s.substring(0, i - 1), stringToDate(s.substring(i + 4)), false);
            }
        }
        throw new BigdogException("Come on! Set a due by date and get to work!\n");
    }

    /**
     * Factory method that creates a Deadline object from a string input and a marked status.
     * This method is typically used when loading tasks from the storage file.
     *
     * @param s the input string containing the task description and deadline.
     * @param marked whether the task is marked as done.
     * @return a new Deadline instance.
     * @throws BigdogException if the input string is corrupted or invalid.
     */
    public static Deadline of(String s, boolean marked) throws BigdogException {

        assert s.length() > MIN_LENGTH_FOR_CORRUPTION_CHECK : "data file corrupted! Cause: " + s + "\n";
        String[] deadlineParts = s.split("\\|");
        if (deadlineParts.length != 3) {
            throw new BigdogException("Data file corrupted! Cause: " + s);
        }
        LocalDateTime end = LocalDateTime.parse(deadlineParts[2].trim());
        String description = deadlineParts[1].trim();
        return new Deadline(description, end, marked);


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

        assert str.chars().filter(x -> x == '/').count() == 2 : "Invalid date format :" + str + "\n";

        String[] parts = str.split(" ");
        String[] dateParts = parts[0].split("/");
        String year = dateParts[2];
        String month = dateParts[1];
        String day = dateParts[0];

        String time = (parts.length == 2) ? parts[1] : "00:00";

        try {
            withTime = !time.equals("00:00");
            return LocalDateTime.parse(String.format("%s-%s-%sT%s", year, month, day, time));
        } catch (DateTimeParseException e) {
            throw new BigdogException("Invalid date format: " + str +
                    "\nExample correct format: deadline return book /by 02/07/2019 18:00");
        }

    }

    /**
     * Returns a string representing the task description and deadline.
     *
     * @return the description of the task with the deadline included.
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " | " + this.end;
    }

    /**
     * Checks if the task is unmarked and occurs on or before the specified date.
     * This method determines whether the task is unmarked (i.e., not completed)
     * and if the given date is on or before the task's end date. It returns true
     * if the task is unmarked and the date is either equal to or earlier than the
     * end date.
     *
     * @param date The LocalDateTime object representing the date to check.
     * @return true if the task is unmarked and the date is on or before the end
     *         date, false otherwise.
     */
    @Override
    public boolean isOnDay(LocalDateTime date) {
        return !this.isMarked() && date.toLocalDate().isEqual(end.toLocalDate());
    }

    /**
     * Returns a string representation of the Deadline task.
     * The format includes the task type, status, description, and deadline.
     *
     * @return a formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        if (withTime) {
            return "[D]" + super.toString() + " (by: "
                    + this.end.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + this.end.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
        }
    }

}
