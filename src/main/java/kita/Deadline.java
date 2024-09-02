package kita;

import java.time.LocalDate;

/**
 * A "Deadline" tasks with a "byTime"
 */
public class Deadline extends Task {
    private final LocalDate byTime;

    /**
     * Initialises a "Deadline" task
     *
     * @param name The name of the task
     * @param byTime The time this task needs to be completed in the form of yyyy-mm-dd
     */
    public Deadline(String name, String byTime) {
        super(name);
        this.byTime = DateFormatters.getDateFromStr(byTime);
    }

    public LocalDate getByTime() {
        return this.byTime;
    }

    @Override
    public String type() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateFormatters.getStrFromDate(this.byTime) + ")";
    }
}
