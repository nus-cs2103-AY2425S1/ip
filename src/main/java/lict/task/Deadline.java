package lict.task;

import lict.DateTime;

import lict.LictException;

import java.time.DateTimeException;

public class Deadline extends Task {
    protected DateTime by;

    public Deadline(String description, String by) throws LictException {
        super(description);
        try {
            this.by = new DateTime(by);
        } catch (DateTimeException e) {
            throw new LictException("Invalid format for deadline. Please ensure that deadline is in the form 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.getString() + ")";
    }

    @Override
    public String toData() {
        String status = this.isDone ? "1" : "0";
        return String.format("DEADLINE | %s | %s | %s\n", status, this.description, by.getData());
    }

    public static Deadline loadTask(String dataMessage) throws LictException {
        String[] messageParts = dataMessage.split("\\|", 2);
        String description = messageParts[0].trim();
        if (description.isEmpty() || messageParts.length != 2) {
            throw new LictException();
        }
        return new Deadline(description, messageParts[1].trim());

    }
}
