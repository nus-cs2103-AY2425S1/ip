package tasks;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import exceptions.EchoException;

/**
 * Represents a task with a start time and an end time that needs to be completed.
 * This class extends the Task class and implements Serializable for object serialization.
 */
public class Event extends Task implements Serializable {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Creates an event task object.
     * An event will start at a specific date/time
     * and ends at a specific date/time.
     *
     * @param description task information of the event.
     * @param startTime start time of the event.
     * @param endTime end time of the event.
     */
    public Event(String description, String startTime, String endTime) throws EchoException {
        this.description = description;

        // Create a DateTimeFormatter and parse the time strings into LocalDateTime objects
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm", Locale.US);
        try {
            this.startTime = LocalDateTime.from(LocalDateTime.parse(startTime, format));
            this.endTime = LocalDateTime.from(LocalDateTime.parse(endTime, format));
        } catch (DateTimeParseException e) {
            throw new EchoException("Oops! Your input time is in invalid format.");
        }

        // If start time is later than end time throw an exception
        if (!this.startTime.isBefore(this.endTime)) {
            throw new EchoException("Oops! Your start time is later than end time.");
        }
    }

    /**
     * Returns the information of the task.
     *
     * @return information the task in "[E][-] Task (from: 'start time' to: 'end time')" format.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(
                "dd MMM yyyy HH:mm", Locale.US);
        return "[E]" + super.toString() + " (from "
                + this.startTime.format(outputFormat) + " to "
                + this.endTime.format(outputFormat) + ")";
    }

    /** Sends help information of command 'deadline' to user */
    public static String getHelpMessage() {
        return "Event is a task that starts at a specific date/time and ends at a specific date/time.\n"
                + "e.g.\n\t(a) team project meeting 2/10/2019 2-4pm\n"
                + "\t(b) orientation week 4/10/2019 to 11/10/2019\n\n"
                + "Format:\n"
                + "\tevent<whitespace>[description]<whitespace>/from<whitespace>[start time]"
                + "<whitespace>/to<whitespace>[end time]\n"
                + "- [description] is the details of this task.\n"
                + "- [start time] is the start time of this task in 'yyyy/MM/dd<whitespace>HH:mm' format.\n"
                + "- [end time] is the end time of this task in 'yyyy/MM/dd<whitespace>HH:mm' format.\n\n"
                + "Remark: Please enter time in correct format and do not enter extra whitespace.";
    }
}
