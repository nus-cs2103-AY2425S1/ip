package duck.tasks;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import duck.exceptions.BeforeEarliestTimeException;

public class DoAfter extends Task {
    private final DateAndTime earliestTime;

    public DoAfter(String description, DateAndTime earliestTime) {
        super(description);
        this.earliestTime = earliestTime;
    }

    /**
     * Marks task as done only if after earliestTime
     */
    @Override
    public void markAsDone() throws BeforeEarliestTimeException {
        LocalDate dateNow = ZonedDateTime.now(ZoneId.systemDefault()).toLocalDate();
        LocalDate earliestDate = earliestTime.getDate();
        if (dateNow.isAfter(earliestDate) || dateNow.equals(earliestDate)) {
            this.isDone = true;
        } else {
            throw new BeforeEarliestTimeException(this, earliestDate, dateNow);
        }
    }

    @Override
    public String getSaveString() {
        return String.format("A,%s,%s,%s", isDone, description, earliestTime.getOriginalString());
    }

    @Override
    public String toString() {
        return "[A]" + super.toString()
                + String.format(" (after: %s)", earliestTime.toString());
    }
}
