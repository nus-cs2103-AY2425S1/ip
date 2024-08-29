package alisa.task;

import alisa.AlisaException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private LocalDateTime dueDate;

    public Deadline(String taskDescription, String dueDate) throws AlisaException {
        super(taskDescription);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm");
            this.dueDate = LocalDateTime.parse(dueDate, formatter);
        } catch (DateTimeParseException e) {
            throw new AlisaException("Please write the deadline in the following format: yyyy-mm-dd hh:mm");
        }
    }

    @Override
    public String toString() {
        String task = super.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm");
        return "[D] " + task + "(by: " + dueDate.format(formatter) + ")";
    }
    @Override
    public String toFileString() {
        return "D | " + this.getFileStatus() + " | " + this.getTask() + " | " + dueDate + "\n";
    }
}
