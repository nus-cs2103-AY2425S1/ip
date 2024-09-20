package alisa.task;

import alisa.exception.AlisaException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs an instance of Event.
     *
     * @param taskDescription Description of the event.
     * @param from Start date and time of the event.
     * @param to End date and time of the event.
     * @throws AlisaException If the from or to input is not in the right format.
     */
    public Event(String taskDescription, String from, String to) throws AlisaException {
        super(taskDescription);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm");
            this.startTime = LocalDateTime.parse(from, formatter);
            this.endTime = LocalDateTime.parse(to, formatter);
        } catch (DateTimeParseException e) {
            throw new AlisaException("Please write the deadline in the following format: yyyy-mm-dd hh:mm");
        }
    }

    /**
     * {@inheritDoc}
     *
     * Returns type of task (event task).
     *
     * @return String indicating it is an event task and its start and end time.
     */
    @Override
    public String toString() {
        String task = super.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy HH:mm");
        return "[E] " + task + " (from: " + startTime.format(formatter)
                + " to: " + endTime.format(formatter) + ")";
    }

    /**
     * Returns details of the event task.
     *
     * @return String of task type, task status, and task description, and task start and end time.
     */
    @Override
    public String convertToFileString() {
        return "E | " + this.getFileStatus() + " | "
                + this.getTask() + " | " + startTime + "-" + endTime + "\n";
    }

    public void changeStartTime(String time) throws AlisaException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            startTime = LocalDateTime.parse(time, formatter);
        } catch (DateTimeParseException e) {
            throw new AlisaException("Please write the deadline in the following format: yyyy-mm-dd hh:mm");
        }
    }

    public void changeEndTime(String time) throws AlisaException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            endTime = LocalDateTime.parse(time, formatter);
        } catch (DateTimeParseException e) {
            throw new AlisaException("Please write the deadline in the following format: yyyy-mm-dd hh:mm");
        }
    }
}
