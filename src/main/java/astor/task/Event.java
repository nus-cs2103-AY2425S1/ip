package astor.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import astor.exception.TimeFormatException;

/**
 * Represents a task with a start and end time.
 *
 * This class extends the Task class and includes both a start and an end time for the event.
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs an Event object with a task description, start time, and end time represented as strings.
     *
     * @param taskInfo a description of the task
     * @param start a string representing the start time in ISO-8601 format
     * @param end a string representing the end time in ISO-8601 format
     */
    public Event(String taskInfo, String start, String end) throws TimeFormatException {
        super(taskInfo);
        try {
            this.start = LocalDateTime.parse(generateParse(start));
            this.end = LocalDateTime.parse(generateParse(end));
        } catch (DateTimeParseException e) {
            throw new TimeFormatException();
        }
    }

    /**
     * Constructs an Event object with a task description, start time,
     * and end time represented as LocalDateTime objects.
     *
     * @param taskInfo a description of the task
     * @param start a LocalDateTime object representing the start time
     * @param end a LocalDateTime object representing the end time
     */
    public Event(String taskInfo, LocalDateTime start, LocalDateTime end) {
        super(taskInfo);
        assert start != null && end != null : "start and end should not be null";
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the event task.
     *
     * The string representation includes the task type, completion status, task description,
     * and the start and end times formatted as "MMM dd yyyy".
     *
     * @return a string representation of the event task
     */
    @Override
    public String toString() {
        String s = "[E] ";
        if (this.isDone()) {
            s += "[X] ";
        } else {
            s += "[ ] ";
        }
        s += this.getTaskInfo() + " (from: "
                + this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: "
                + this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        return s;
    }

    /**
     * Returns a string description of the task data for storing task data.
     *
     * The string includes the task type, completion status, task description, start time, and end time.
     *
     * @return a string description of the task data
     */
    @Override
    public String dataDescription() {
        int i = isDone() ? 1 : 0;
        return "E | " + i + " | " + this.getTaskInfo() + " | " + start + " | " + end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o instanceof Event) {
            Event e = (Event) o;
            return this.start.equals(e.start) && this.end.equals(e.end) && this.getTaskInfo().equals(e.getTaskInfo());
        }
        return false;
    }
}
