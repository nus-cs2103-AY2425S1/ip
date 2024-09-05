package yapbot.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import yapbot.exceptions.YapBotException;

/**
 * Child class of Task that has start and end dates/times.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Returns an Event instance which is set as incomplete by default.
     *
     * @param description Details of the task to be created.
     * @param fromStr Start time of the task.
     * @param toStr End time of the task.
     * @throws YapBotException If task description, start and/or end times are empty.
     */
    public Event(String description, String fromStr, String toStr) throws YapBotException {
        super(description);

        if (toStr.isEmpty() && fromStr.isEmpty()) {
            throw new YapBotException("Error, start and end times not detected.\nUse command \"todo\" for tasks "
                    + "without deadlines.");
        }

        if (toStr.isEmpty()) {
            throw new YapBotException("Error, end time not detected.\nManual input of end time required.");
        }

        if (fromStr.isEmpty()) {
            throw new YapBotException("Error, start time not detected.\nUse command \"deadline\" for tasks "
                    + "without start times.");
        }

        LocalDateTime from;
        LocalDateTime to;

        // converts string to LocalDateTime for "from"
        if (fromStr.contains("AM") | fromStr.contains("PM")) {
            if (fromStr.contains("/")) {
                // Date and Time
                from = LocalDateTime.parse(fromStr, DATETIME_FORMATTER);
            } else {
                //Time only, sets date to the day's date
                from = LocalTime.parse(fromStr, TIME_FORMATTER).atDate(LocalDate.now());
            }
        } else {
            // Date only, set time to default to 8am
            from = LocalDate.parse(fromStr, DATE_FORMATTER).atTime(8, 0);
        }

        // same as above but for "to" field instead
        if (toStr.contains("AM") | toStr.contains("PM")) {
            if (toStr.contains("/")) {
                to = LocalDateTime.parse(toStr, DATETIME_FORMATTER);
            } else {
                to = LocalTime.parse(toStr, TIME_FORMATTER).atDate(LocalDate.now());
            }
        } else {
            to = LocalDate.parse(toStr, DATE_FORMATTER).atTime(8, 0);
        }

        this.from = from;
        this.to = to;
    }

    /**
     * Creates an Event instance and allows completion status to be initialised.
     * This is mostly used when creating the task from a saved format.
     *
     * @param description Details of the Task.
     * @param from Date/time when this task should start.
     * @param to Date/time when this task should end.
     * @param isDone Completion status the task will be initialized with.
     * @throws YapBotException If task description is empty.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) throws YapBotException {
        super(description);
        this.setDone(isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String saveTask() {
        return "E/" + super.saveTask() + "/" + this.from.toString() + "/" + this.to.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (From: " + this.from.format(OUTPUT_FORMATTER)
                + " To: " + this.to.format(OUTPUT_FORMATTER)
                + ")";
    }
}
