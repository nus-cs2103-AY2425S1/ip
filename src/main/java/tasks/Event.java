package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    private static final String DATE_TIME_FORMATTER_PATTERN = "dd/MM/yyyy HHmm";
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Initialisation of an Event task with the required attributes.
     * @param name Name of the task.
     * @param start Start date/ time of the task.
     * @param end End date/ time of the task
     */
    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.startTime = start;
        this.endTime = end;
    }

    /**
     * Returns a string on how an Event should be stored in a save file.
     * @return Save format for the task.
     */
    @Override
    public String storeTask() {
        StringBuilder saveTaskInfo = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_PATTERN);
        String startDate = this.startTime.format(formatter);
        String endDate = this.endTime.format(formatter);
        if (this.getCompleted()) {
            saveTaskInfo.append("done, ");
        } else {
            saveTaskInfo.append("undone, ");
        }
        saveTaskInfo.append("event ");
        saveTaskInfo.append(this.getName());
        saveTaskInfo.append(" /from ");
        saveTaskInfo.append(startDate);
        saveTaskInfo.append(" /to ");
        saveTaskInfo.append(endDate);
        saveTaskInfo.append("\n");
        return saveTaskInfo.toString();
    }

    /**
     * Returns the string representation of an Event.
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_PATTERN);
        String startDate = this.startTime.format(formatter);
        String endDate = this.endTime.format(formatter);
        return "[E] " + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
