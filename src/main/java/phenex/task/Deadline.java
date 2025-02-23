package phenex.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class which encapsulates a Deadline task.
 */
public class Deadline extends TaskWithDate {
    /** Date of the Deadline */
    protected LocalDate localDate;

    /**
     * Constructs a new Deadline object.
     *
     * @param name the name of the Deadline task.
     * @param localDate the date of the Deadline task.
     */
    public Deadline(String name, LocalDate localDate, TaskType taskType) {
        super(name, "D", taskType);
        this.localDate = localDate;
    }

    public LocalDate getDeadlineDate() {
        return this.localDate;
    }

    @Override
    public boolean overlapsWith(LocalDate localDate) {
        return this.localDate.equals(localDate);
    }

    @Override
    public boolean occursBetween(LocalDate fromDate, LocalDate toDate) {
        return (fromDate.equals(this.localDate) || fromDate.isBefore(this.localDate))
                && (toDate.equals(this.localDate) || toDate.isAfter(this.localDate));
    }

    @Override
    public String formatDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Deadline) {
            Deadline compared = (Deadline) object;
            return super.equals(compared) && this.localDate.equals(compared.localDate);
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + this.taskSymbol + "]"
                + super.toString()
                + " (by: "
                + formatDate(this.localDate) + ")";
    }
}
