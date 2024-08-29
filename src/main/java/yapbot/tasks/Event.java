package yapbot.tasks;

import yapbot.exceptions.YapBotException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Child class of Task that has a start and end dates/times.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Returns an Event instance with isDone set to false by default.
     *
     * @param description Details of the Task.
     * @param from Date/time when this task should start.
     * @param to Date/time when this task should end.
     */
    public Event(String description, String fromStr, String toStr) throws YapBotException {
        super(description);

        if (toStr.isEmpty() && fromStr.isEmpty()) {
            throw new YapBotException("Error, start and end times not detected.\nUse command \"todo\" for tasks " +
                    "without deadlines.");
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

        if (fromStr.contains("AM") | fromStr.contains("PM")) {
            if (fromStr.contains("/")) {
                from = LocalDateTime.parse(fromStr, DateTimeFormatter.ofPattern("ha yyyy/MM/dd"));
            } else {
                from = LocalTime.parse(fromStr, DateTimeFormatter.ofPattern("ha")).atDate(LocalDate.now());
            }

        } else {
            from = LocalDate.parse(fromStr, DateTimeFormatter.ofPattern("yyyy/MM/dd")).atTime(8,0);
        }

        if (toStr.contains("AM") | toStr.contains("PM")) {
            if (toStr.contains("/")) {
                to = LocalDateTime.parse(toStr, DateTimeFormatter.ofPattern("ha yyyy/MM/dd"));
            } else {
                to= LocalTime.parse(toStr, DateTimeFormatter.ofPattern("ha")).atDate(LocalDate.now());
            }

        } else {
            to = LocalDate.parse(toStr, DateTimeFormatter.ofPattern("yyyy/MM/dd")).atTime(8, 0);
        }

        this.from = from;
        this.to = to;
    }

    /**
     * Creates an Event instance and allows isDone to be set to any boolean value.
     *
     * @param description Details of the Task.
     * @param from Date/time when this task should start.
     * @param to Date/time when this task should end.
     * @param isDone Set to true for task to be completed by default.
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ha dd MMM yyyy");
        return "[E]" + super.toString() + " (From: " + this.from.format(formatter) + " To: " + this.to.format(formatter) + ")";
    }
}
