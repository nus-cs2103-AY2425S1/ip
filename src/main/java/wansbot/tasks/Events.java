package wansbot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class which gives WansBot functionality to add tasks with start and end date.
 */
public class Events extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Initialises the event object.
     *
     * @param name Name of the event.
     * @param start Start date of the event.
     * @param end End date of the event.
     */
    public Events(String name, LocalDate start, LocalDate end) {
        super(name);
        this.startDate = start;
        this.endDate = end;
    }

    /**
     * Tells the user if Event occurs on the date.
     *
     * @param date User inputs the date to filter task.
     * @return Boolean which tells the user if date lies between Event's start and end date.
     */
    public boolean isBetweenDate(LocalDate date) {
        if (this.startDate.isBefore(date) && this.endDate.isAfter(date)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String myEvent = "[ E ] ";

        if (this.isDone()) {
            myEvent += "[ X ]";
        } else {
            myEvent += "[   ]";
        }

        return myEvent + super.toString() + " (from: " + this.startDate.format(DateTimeFormatter
                .ofPattern("MMM d yyyy")) + " to: " + this.endDate.format(DateTimeFormatter
                .ofPattern("MMM d yyyy")) + " )";
    }
}
