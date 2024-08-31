package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    public Events(String name,  LocalDate start, LocalDate end) {
        super(name);
        this.startDate = start;
        this.endDate = end;
    }

    @Override
    public String toString() {
        String myEvent = "[ E ] ";

        if (this.isDone()) {
            myEvent += "[ X ]";
        } else {
            myEvent += "[   ]";
        }

        return myEvent + super.toString() + " (from: " + this.startDate.format(DateTimeFormatter.
                ofPattern("MMM d yyyy")) + " to: " + this.endDate.format(DateTimeFormatter.
                ofPattern("MMM d yyyy")) + " )";
    }
}
