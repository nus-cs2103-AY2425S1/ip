package lict.task;

import java.time.DateTimeException;

import lict.DateTime;
import lict.LictException;
import lict.Ui;


/**
 * The {@code lict.task.Event} class represents a task that has a start time and an end time.
 * It extends the {@code lict.task.Task} class and provides specific implementations for
 * the {@code toString} and {@code toData} methods.
 */
public class Event extends Task {
    private static final String TIME_ONLY_REGEX = "\\d{4}";
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
            if (to.matches(TIME_ONLY_REGEX)) {
                String date = this.from.getData().split(WHITESPACE_DELIMITER)[0];
                this.to = new DateTime(date + " " + to);
            } else {
                this.to = new DateTime(to);
            }
        } catch (DateTimeException e) {
            throw new LictException(
                    "Invalid format for event start date or event end date. Please ensure that Event date and "
                            + "time information is in the form 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'."
            );
        }
    }
    @Override
    public boolean isScheduledTask() {
        return true;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.getString() + " to: " + this.to.getString() + ")";
    }

    @Override
    public void snoozeTask(Ui ui, String info) throws LictException {
        if (!info.startsWith("/from")) {
            throw new LictException("Please include the new event details you wish to set in the following format:\n"
                    + "snooze {task number} /from {new event start} /to {new event end}");
        }
        String[] newEventInfo = info.substring(5).split("/to", 2);
        if (newEventInfo.length != 2 || newEventInfo[0].isEmpty() || newEventInfo[1].isEmpty()) {
            throw new LictException("Please include the new event details you wish to set in the following format:\n"
                    + "snooze {task number} /from {new event start} /to {new event end}");
        }
        String newFrom = newEventInfo[0].trim();
        String newTo = newEventInfo[1].trim();
        try {
            this.from = new DateTime(newFrom);
            //If 'newTo' only contains time, assume that it has the same date as from
            if (newTo.matches(TIME_ONLY_REGEX)) {
                String date = this.from.getData().split(WHITESPACE_DELIMITER)[0];
                this.to = new DateTime(date + " " + newTo);
            } else {
                this.to = new DateTime(newTo);
            }
        } catch (DateTimeException e) {
            throw new LictException(
                    "Invalid format for event start date or event end date. Please ensure that Event date and "
                            + "time information is in the form 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'."
            );
        }
    }

    @Override
    public String toData() {
        String status = this.isDone ? "1" : "0";
        return String.format(
                "EVENT | %s | %s | %s | %s\n", status, this.description, this.from.getData(), this.to.getData()
        );
    }

    /**
     * Loads an {@code Event} task from a data string.
     *
     * @param dataMessage The string containing the task data.
     * @return The {@code Event} object created from the data string.
     * @throws LictException If the data string is invalid or the date/time format is incorrect.
     */
    public static Event loadTask(String dataMessage) throws LictException {
        String[] messageParts = dataMessage.split(PIPE_DELIMITER, 3);
        String description = messageParts[0].trim();
        if (description.isEmpty() || messageParts.length != 3) {
            throw new LictException("Data is faulty. Discarding...");
        }
        return new Event(messageParts[0].trim(), messageParts[1].trim(), messageParts[2].trim());
    }
}
