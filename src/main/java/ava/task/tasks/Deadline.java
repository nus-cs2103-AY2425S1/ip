package ava.task.tasks;

import ava.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline
 */
public class Deadline extends Task {

    private LocalDateTime time;

    /**
     * Creates a new deadline with the input title and time
     *
     * @param title the title
     * @param time the time
     */
    public Deadline(String title,String time) {
        super(title);
        this.time = LocalDateTime.parse(time);
    }

    /**
     * Creates a new deadline with the input title, completion status and time
     *
     * @param title the title
     * @param done the completion status
     * @param time the time
     */
    public Deadline(String title, boolean done, String time) {
        super(title, done);
        this.time = LocalDateTime.parse(time);
    }

    /**
     * Returns the string representation of the deadline
     *
     * @return the string representation of the deadline
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Deadline: ");
        sb.append(super.toString());
        sb.append("[By :");
        sb.append(time.format(DateTimeFormatter.RFC_1123_DATE_TIME));
        sb.append("]");
        return sb.toString();
    }

    /**
     * Serializes the deadline to a string
     *
     * <br>
     * Optimizes the task's string representation for storage
     * @return the serialized string
     */
    public String serialize() {
        return String.format("D,%s,%s", super.serialize(),time);
    }
}
