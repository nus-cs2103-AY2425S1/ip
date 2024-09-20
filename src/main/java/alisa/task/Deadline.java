package alisa.task;

import alisa.exception.AlisaException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private LocalDateTime dueDate;

    /**
     * Constructs an instance of Deadline.
     *
     * @param taskDescription Description of the deadline task.
     * @param dueDate Deadline date.
     * @throws AlisaException If the dueDate input is not in the right format.
     */
    public Deadline(String taskDescription, String dueDate) throws AlisaException {
        super(taskDescription);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.dueDate = LocalDateTime.parse(dueDate, formatter);
        } catch (DateTimeParseException e) {
            throw new AlisaException("Please write the deadline in the following format: yyyy-mm-dd hh:mm");
        }
    }

    /**
     * {@inheritDoc}
     *
     * Returns type of task (deadline task).
     *
     * @return String indicating it is a deadline task and its deadline.
     */
    @Override
    public String toString() {
        String task = super.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D] " + task + "(by: " + dueDate.format(formatter) + ")";
    }

    /**
     * Returns details of the deadline task.
     *
     * @return String of task type, task status, task description, and due date.
     */
    @Override
    public String convertToFileString() {
        return "D | " + this.getFileStatus() + " | "
                + this.getTask() + " | " + dueDate + "\n";
    }

    public void changeDueDate(String date) throws AlisaException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm");
            dueDate = LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new AlisaException("Please write the deadline in the following format: yyyy-mm-dd hh:mm");
        }
    }
}
