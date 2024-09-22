package duck.exceptions;

import java.time.LocalDate;

import duck.tasks.DoAfter;

/**
 * Class representing errors that occur when the user attempts to mark a
 * "doafter" task as done before the earliest date.
 */
public class BeforeEarliestTimeException extends Exception {
    private DoAfter task;
    private LocalDate earliestDate;
    private LocalDate dateNow;

    /**
     * Constructor for BeforeEarliestTimeException.
     *
     * @param task         The task that the exception concerns.
     * @param earliestDate The earliest date the task can be marked as done.
     * @param dateNow      The current date.
     */
    public BeforeEarliestTimeException(DoAfter task, LocalDate earliestDate, LocalDate dateNow) {
        this.task = task;
        this.earliestDate = earliestDate;
        this.dateNow = dateNow;
    }

    @Override
    public String toString() {
        return String.format("error: do after task %s cannot be done must be done after earliest time.\n"
                + "Earliest time is: %s\n"
                + "Time now is: %s",
                task.toString(), earliestDate.toString(), dateNow.toString());
    }
}
