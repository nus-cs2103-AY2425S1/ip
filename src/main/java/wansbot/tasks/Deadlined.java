package wansbot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class which provides deadline functions to WansBot.
 */
public class Deadlined extends Task {
    private LocalDate deadline;

    /**
     * Constructor that takes the name and date and assigns it to the Deadlined.
     */
    public Deadlined(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * If Deadlined's date is equal to date, then returns true. Returns false otherwise.
     *
     * @param date is a LocalDate input by user as part of filtering tasks.
     * @return Boolean which tells user if the Deadline is by date.
     */
    public boolean isOnDate(LocalDate date) {
        if (this.deadline.isEqual(date)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String myDeadline = "[ D ] ";

        if (this.isDone()) {
            myDeadline += "[ X ]";
        } else {
            myDeadline += "[   ]";
        }

        return myDeadline + super.toString() + " (by: " + this.deadline
                        .format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
