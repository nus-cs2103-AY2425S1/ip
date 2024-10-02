package snowy;

import java.time.DateTimeException;
import java.time.LocalDate;


/**
 * Represents a task that is an event with start and end date.
 * This class holds the fromDate and toDate as a LocalDate.
 */
public class Event extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Creates a new Event with the specific name and dates provided.
     * @param name the name of the Event.
     * @param fromDate the start date of the event, in the format of yyyy-mm-dd.
     * @param toDate the end date of the event, in the format of yyyy-mm-dd.
     * @throws SnowyException if the fromDate or toDate provided is in the wrong format.
     */
    public Event(String name, String fromDate, String toDate) throws SnowyException {
        super(name);
        assert !name.isEmpty() : "Deadline name should not be empty";
        try {
            this.fromDate = LocalDate.parse(fromDate);
            this.toDate = LocalDate.parse(toDate);
        } catch (DateTimeException e) {
            throw new SnowyException("Wrong date format");
        }

    }

    @Override
    public String toString() {
        String temp = super.toString();
        return String.format("[E]%s (from %s to %s)",
                temp, fromDate.format(FORMATTER), toDate.format(FORMATTER));
    }

    @Override
    public String toFileStorage() {
        String temp = super.toFileStorage();
        return String.format("E|%s|%s|%s", temp, fromDate, toDate);
    }

    /**
     * Changes the fromDate and toDate to the new date that is given.
     * @param fromDate the new start date of the event, in the format of yyyy-mm-dd.
     * @param toDate the new end date of the event, in the format of yyyy-mm-dd.
     * @throws SnowyException if the date has the wrong format.
     */
    public void changeDate(String fromDate, String toDate) throws SnowyException {
        try {
            this.fromDate = LocalDate.parse(fromDate);
            this.toDate = LocalDate.parse(toDate);
        } catch (DateTimeException e) {
            throw new SnowyException("Wrong date format");
        }

    }
}
