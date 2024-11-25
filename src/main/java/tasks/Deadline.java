package tasks;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import exceptions.EchoException;

/**
 * Represents a task with a deadline that needs to be completed before a specific date and time.
 * This class extends the Task class and implements Serializable for object serialization.
 */
public class Deadline extends Task implements Serializable {
    private LocalDateTime deadline;

    /**
     * Creates task that need to be done before a specific date/time.
     *
     * @param description task information of the deadline task.
     * @param deadline the time by which the task needs to be completed,
     *                 in the format "yyyy/MM/dd HH:mm".
     * @throws DateTimeParseException if the deadline string is not in the correct format.
     */
    public Deadline(String description, String deadline) throws EchoException {
        this.description = description;

        // Parse the time strings into LocalDateTime objects
        try {
            this.deadline = LocalDateTime.from(LocalDateTime.parse(deadline,
                    DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm", Locale.US)));
        } catch (DateTimeParseException e) {
            throw new EchoException("Oops! Your input time is in invalid format.");
        }
    }

    /**
     * Returns the information of a deadline task.
     *
     * @return information the task in "[D][-] Task (by: 'deadline')" format.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(
                "dd MMM yyyy HH:mm", Locale.US);
        return "[D]" + super.toString() + " (by "
                + this.deadline.format(outputFormat) + ")";
    }

    /** Sends help information of command 'deadline' to user */
    public static String getHelpMessage() {
        return "Deadline is a task that needs to be done before a specific date/time.\n"
                + "e.g. submit report by 11/10/2019 5pm\n\n"
                + "Format:\n"
                + "\t deadline<whitespace>[description]<whitespace>/by<whitespace>[time] \n"
                + "- [description] is the details of this task.\n"
                + "- [time] is the end time of this task in 'yyyy/MM/dd<whitespace>HH:mm' format.\n\n"
                + "Remark: Please enter time in correct format and do not enter extra whitespace.";
    }
}
