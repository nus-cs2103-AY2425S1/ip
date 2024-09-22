package donk.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Representation of even with start and end date
 *          extends from Task
 */
public class Event extends Task {
    protected LocalDateTime startDt;
    protected LocalDateTime endDt;

    /**
     * Constructs an donk.task.Event object with the specified description, start date-time, and end date-time.
     *
     * @param description The description of the event.
     * @param startDt The start date-time of the event in the specified format.
     * @param endDt The end date-time of the event in the specified format.
     */
    public Event(String description, String startDt, String endDt) {
        super(description, "E");

        this.startDt = parseDatetimeString(startDt);
        this.endDt = parseDatetimeString(endDt);
    }


    /**
     * Parse datetime string using various strategies
     */
    private LocalDateTime parseDatetimeString(String dtString) throws IllegalArgumentException {
        if (dtString.split(" ").length == 1) {
            dtString = dtString + " 2359";
        }
        List<DateTimeFormatter> formatters = List.of(
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        );
        LocalDateTime dateTime = null;

        // Try each formatter until one succeeds
        for (DateTimeFormatter formatter : formatters) {
            try {
                System.out.println("testing " + formatter.toString());
                dateTime = LocalDateTime.parse(dtString, formatter);
                break; // Exit the loop once parsing is successful
            } catch (DateTimeParseException e) {
                System.out.println("error " + e.getMessage());
                // Ignore the exception and try the next formatter
            }
        }

        // Check if parsing was successful
        if (dateTime == null) {
            throw new IllegalArgumentException("invalid datetime input");
        } else {
            return dateTime;
        }
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return A string in the format "[E] description (from: startDt to endDt)", where "description"
     *         is the task description, "startDt" is the start date and time, and "endDt" is the end date and time.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.startDt.toString() + " to " + this.endDt.toString() + ")";
    }

    /**
     * get start date in ISO format
     * @return ISO format start date string
     */
    @Override
    public String getIsoDate() {
        return this.startDt.toString();
    }

    /**
     * compare task iso dates
     * @param task
     * @return int result of comparison
     */
    @Override
    public int compareTo(Task task) {
        if (task.getTaskType() == "T") {
            return 1;
        } else {
            return this.getIsoDate().compareTo(task.getIsoDate());
        }
    }


    /**
     * Returns a string representation of the Event object suitable for saving to a file.
     *
     * @return A string in the format "taskType|isDone|description|startDt|endDt", where "taskType"
     *         is the type of task, "isDone" is 1 if the task is completed, otherwise 0,
     *         "description" is the task description, "startDt" is the start date and time,
     *         and "endDt" is the end date and time.
     */
    @Override
    public String toFileSaveString() {
        return this.taskType + "|" + (this.isDone ? "1" : "0") + "|"
                + this.description + "|" + this.startDt + "|" + this.endDt;
    }

}
