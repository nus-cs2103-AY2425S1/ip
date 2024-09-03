package phenex.task;

import java.time.LocalDate;

/**
 * Abstract TaskWithDate class encapsulating more specific Tasks, which contain a date.
 */
public abstract class TaskWithDate extends Task {
    public TaskWithDate(String name, String symbol) {
        super(name, symbol);
    }

    /**
     * Returns a string which is a formatted representation of the date.
     *
     * @param localDate the date to format.
     * @return string which is a formatted representation of the date.
     */
    public abstract String formatDate(LocalDate localDate);

    /**
     * Returns a boolean indicating whether a specific date overlaps with deadline.
     *
     * @param localDate the date to check whether deadline overlaps with.
     * @return boolean indicating whether the specified date overlaps with deadline.
     */
    public abstract boolean overlapsWith(LocalDate localDate);
}
