package tasks;

import exceptions.InvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate byDate;
    public Deadline(String name, String byDate) {
        super(name);
        try {
            this.byDate = LocalDate.parse(byDate);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(byDate);
        }
    }

    @Override
    public String getSaveFormat() {
        return "D | " + super.getSaveFormat() + " | " + this.byDate;
    }

    @Override
    public boolean isDuring(String date) {
        return this.byDate.isEqual(LocalDate.parse(date));
    }

    @Override
    public String toString() {
        String byDateAsString = this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString()
                + String.format(" (by: %s)", byDateAsString);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline temp) {
            return super.equals(temp) && this.byDate == temp.byDate;
        }
        return false;
    }
}
