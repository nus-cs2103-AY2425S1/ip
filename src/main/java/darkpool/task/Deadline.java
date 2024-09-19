package darkpool.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import darkpool.DarkpoolException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    protected LocalDateTime byTime;

    public Deadline(String description, String byTime, boolean isDone) throws DarkpoolException {
        super(description, isDone);

        try {
            this.byTime = LocalDateTime.parse(byTime, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DarkpoolException("know what a date is? (dd-mm-yyyy hh:mm)");
        }
    }


    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + this.description + " (by:" + this.byTime.format(FORMATTER) + ")";
    }


    @Override
    public String toFileString() {
        return ("D | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.byTime.format(FORMATTER) + "\n");
    }

}
