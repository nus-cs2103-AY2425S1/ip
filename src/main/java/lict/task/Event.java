package lict.task;

import lict.DateTime;
import lict.LictException;

import java.time.DateTimeException;

/**
 * The {@code lict.task.Event} class represents a task that has a start time and an end time.
 * It extends the {@code lict.task.Task} class and provides specific implementations for the {@code toString} and {@code toData} methods.
 */
public class Event extends Task {
    protected DateTime from;
    protected DateTime to;

    /**
     * Constructs an {@code Event} task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event, in the format 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.
     * @param to The end time of the event, in the format 'yyyy-MM-dd', 'yyyy-MM-dd HHmm', or 'HHmm'.
     * @throws LictException If the date or time format is invalid.
     */
    public Event(String description, String from, String to) throws LictException {
        super(description);
        try {
            this.from = new DateTime(from);
            //If 'to' only contains time, assume that it has the same date as from
            if (to.matches("\\d{4}")) {
                String date = this.from.getData().split(" ")[0];
                this.to = new DateTime(date + " " + to);
            } else {
                this.to = new DateTime(to);
            }
        } catch (DateTimeException e) {
            throw new LictException("Invalid format for event start date or event end date. Please ensure that Event date and time information is in the form 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.");
        }
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.getString() + " to: " + this.to.getString() + ")";
    }

    @Override
    public String toData() {
        String status = this.isDone ? "1" : "0";
        return String.format("EVENT | %s | %s | %s | %s\n", status, this.description, this.from.getData(), this.to.getData());
    }

    /**
     * Loads an {@code Event} task from a data string.
     *
     * @param dataMessage The string containing the task data.
     * @return The {@code Event} object created from the data string.
     * @throws LictException If the data string is invalid or the date/time format is incorrect.
     */
    public static Event loadTask(String dataMessage) throws LictException {
        String[] messageParts = dataMessage.split("\\|", 3);
        String description = messageParts[0].trim();
        if (description.isEmpty() || messageParts.length != 3) {
            throw new LictException("Data is faulty. Discarding...");
        }
        return new Event(messageParts[0].trim(), messageParts[1].trim(), messageParts[2].trim());
    }
}
