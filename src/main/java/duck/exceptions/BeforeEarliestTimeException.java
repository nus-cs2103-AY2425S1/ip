package duck.exceptions;

import java.time.LocalDate;

import duck.tasks.DoAfter;

public class BeforeEarliestTimeException extends Exception {
    private DoAfter task;
    private LocalDate earliestTime;
    private LocalDate timeNow;

    public BeforeEarliestTimeException(DoAfter task, LocalDate earliestTime, LocalDate timeNow) {
        this.task = task;
        this.earliestTime = earliestTime;
        this.timeNow = timeNow;
    }

    @Override
    public String toString() {
        return String.format("error: do after task %s cannot be done must be done after earliest time.\n"
                + "Earliest time is: %s\n"
                + "Time now is: %s",
                task.toString(), earliestTime.toString(), timeNow.toString());
    }
}
