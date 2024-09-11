package snowy;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Represents a task that have a deadline to be completed by.
 * This class holds the deadline as a LocalDate.
 */
public class Deadline extends Task {
    private final LocalDate date;

    /**
     * Creates a new Deadline with the specific name and date.
     * @param name the name of the task.
     * @param date the due date of the task, in the format of yyyy-mm-dd.
     * @throws SnowyException if the date has the wrong format.
     */
    public Deadline(String name, String date) throws SnowyException {
        super(name);
        assert !name.isEmpty() : "Deadline name should not be empty";
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeException e) {
            throw new SnowyException("Wrong date format");
        }
    }

    @Override
    public String toString() {
        String temp = super.toString();
        return String.format("[D]%s (by %s)", temp, date.format(FORMATTER));
    }

    @Override
    public String toFileStorage() {
        String temp = super.toFileStorage();
        return String.format("D|%s|%s", temp, date.toString());
    }
}
