package ava.task.tasks;

import ava.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event
 */
public class Event extends Task {


    LocalDateTime startTime;
    LocalDateTime endTime;

    /**
     * Creates a new event with the input title,
     * start time and end time.
     *
     * @param title the title.
     * @param startTime the start time.
     * @param endTime the end time.
     */
    public Event(String title, String startTime, String endTime) {
        super(title);
        this.startTime = LocalDateTime.parse(startTime);
        this.endTime = LocalDateTime.parse(endTime);
    }

    /**
     * Creates a new event with the input title,done status,
     * start time and end time.
     *
     * @param title the title.
     * @param isDone the done status.
     * @param startTime the start time.
     * @param endTime the end time.
     */
    public Event(String title,boolean isDone, String startTime, String endTime) {
        super(title,isDone);
        this.startTime = LocalDateTime.parse(startTime);
        this.endTime = LocalDateTime.parse(endTime);
    }

    /**
     * Returns the string representation of the event
     *
     * @return the string representation of the event.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Event: ");
        sb.append(super.toString());
        sb.append("[From :");
        sb.append(startTime.format(DateTimeFormatter.RFC_1123_DATE_TIME));
        sb.append(" To : ");
        sb.append(endTime.format(DateTimeFormatter.RFC_1123_DATE_TIME));
        sb.append("]");
        return sb.toString();
    }

    /**
     * Serializes the event to a string
     * <br>
     * Optimizes the task's string representation for storage
     *
     * @return the serialized string.
     */
    public String serialize() {
        return String.format("E,%s,%s,%s", super.serialize(),startTime,endTime);
    }

}
