package tasks;

import exceptions.InvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;

    public Event(String name, String fromDate, String toDate) {
        super(name);

        try {
            this.fromDate = LocalDate.parse(fromDate);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(fromDate);
        }

        try {
            this.toDate = LocalDate.parse(toDate);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(toDate);
        }
    }

    @Override
    public String getSaveFormat() {
        return "E | " + super.getSaveFormat() + " | " + this.fromDate + " | " + this.toDate;
    }

    @Override
    public boolean isDuring(String date) {
        LocalDate localdate = LocalDate.parse(date);
        return !(localdate.isBefore(this.fromDate) || localdate.isAfter(this.toDate));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
            + String.format(" (from: %s to: %s)",
                this.fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event temp) {
            return super.equals(temp)
                && this.fromDate == temp.fromDate
                && this.toDate == temp.toDate;
        }
        return false;
    }
}
