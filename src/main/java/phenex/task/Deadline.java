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
    public Deadline(String name, LocalDate localDate) {
        super(name, "D");
        this.localDate = localDate;
    }

    public LocalDate getDeadlineDate() {
        return this.localDate;
    }

    @Override
    public boolean overlapsWith(LocalDate localDate) {
        return localDate.equals(this.localDate);
    }

    @Override
    public String formatDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[" + this.symbol + "]"
                + super.toString()
                + " (by: "
                + formatDate(this.localDate) + ")";
    }
}
