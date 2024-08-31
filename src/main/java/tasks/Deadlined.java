package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlined extends Task {
    private LocalDate deadline;

    public Deadlined(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String myDeadline = "[ D ] ";

        if (this.isDone()) {
            myDeadline += "[ X ]";
        } else {
            myDeadline += "[   ]";
        }

        return myDeadline + super.toString() + " (by: " + this.deadline.
                format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
