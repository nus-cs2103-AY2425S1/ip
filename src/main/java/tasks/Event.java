package tasks;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

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
     * @param task task information of the event.
     * @param startTime start time of the event.
     * @param endTime end time of the event.
     */
    public Event(String task, String startTime, String endTime) throws DateTimeParseException {
        this.task = task;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm", Locale.US);
        this.startTime = LocalDateTime.from(LocalDateTime.parse(startTime, format));
        this.endTime = LocalDateTime.from(LocalDateTime.parse(endTime, format));
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
                + "e.g. (a) team project meeting 2/10/2019 2-4pm (b) orientation week 4/10/2019 to 11/10/2019\n\n"
                + "Format:\n"
                + "\t event<whitespace>[description]<whitespace>\\from<whitespace>[start time]"
                + "<whitespace>\\to<whitespace>[end time]\n"
                + "- [description] is the details of this task.\n"
                + "- [start time] is the start time of this task in 'yyyy/MM/dd<whitespace>HH:mm' format.\n"
                + "- [end time] is the end time of this task in 'yyyy/MM/dd<whitespace>HH:mm' format.\n\n"
                + "Remark: Please enter time in correct format and do not enter extra whitespace.";
    }
}
